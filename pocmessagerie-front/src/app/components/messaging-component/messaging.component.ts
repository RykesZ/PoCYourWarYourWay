import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, RouterLink, RouterLinkActive } from '@angular/router';
import { MessageService } from '../../services/message.service';
import { interval, Subject, Subscription, takeUntil } from 'rxjs';
import { Message } from '../../interfaces/message.interface';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-messaging',
  standalone: true,
  templateUrl: './messaging.component.html',
  styleUrls: ['./messaging.component.css'],
  imports: [CommonModule, FormsModule, RouterLink, RouterLinkActive]
})
export class MessagingComponent implements OnInit {

  @ViewChild('messagesContainer') private messagesContainer!: ElementRef;
  messages: Message[] = [];
  messageContent: string = '';
  idExpediteur: string = '';
  idDestinataire: string = '';
  private destroy$ = new Subject<void>();

  constructor(private messageService: MessageService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    // Récupérer les paramètres de l'URL
    this.route.params
    .pipe(takeUntil(this.destroy$))
    .subscribe(params => {
      console.log(params['userType']);
      if (params['userType'] === 'client') {
        this.idExpediteur = 'a473cdba-0066-46fc-9203-1f972463a489';
        this.idDestinataire = '49ef4914-f083-46a2-9fe5-65819ea051a7';
      } else if (params['userType'] === 'employe') {
        this.idExpediteur = '49ef4914-f083-46a2-9fe5-65819ea051a7';
        this.idDestinataire = 'a473cdba-0066-46fc-9203-1f972463a489';
      }
      this.loadMessages();
      console.log("this.idExpediteur :" + this.idExpediteur);
      console.log("this.idDestinataire :" + this.idDestinataire);
    });

    // Démarrer le polling toutes les 5 secondes
    interval(5000)
      .pipe(takeUntil(this.destroy$))
      .subscribe(() => this.loadMessages());
  }

  ngOnDestroy(): void {
    // Annuler tous les abonnements lorsque le composant est détruit
    this.destroy$.next();
    this.destroy$.complete();
  }

  loadMessages(): void {
    if (this.idExpediteur && this.idDestinataire) {
      this.messageService.getReceivedMessages(this.idExpediteur, this.idDestinataire)
      .pipe(takeUntil(this.destroy$))
      .subscribe((data) => {
        this.messages = data;
        console.log(this.messages);
        this.scrollToBottom();
      });
    }
  }

  sendMessage(contenu: string): void {
    console.log("sending message :" + contenu);
    if (this.idExpediteur && this.idDestinataire) {
      this.messageService.sendMessage(this.idExpediteur, this.idDestinataire, contenu)
      .pipe(takeUntil(this.destroy$))
      .subscribe(() => {
        this.loadMessages();
        this.messageContent = '';
      });
    }
  }

  private scrollToBottom(): void {
    setTimeout(() => {
      const container = this.messagesContainer.nativeElement;
      container.scrollTop = container.scrollHeight;  // Place la barre de défilement tout en bas
    }, 100);
  }
}
