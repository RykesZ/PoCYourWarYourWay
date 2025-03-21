import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private apiUrl = 'http://localhost:8080/messages/';

  constructor(private http: HttpClient) {}

  // Récupérer les messages reçus
  getReceivedMessages(idExpediteur: string, idDestinataire: string): Observable<any> {
    return this.http.get(`${this.apiUrl}`, {
      params: { idExpediteur, idDestinataire }
    });
  }

  // Envoyer un message
  sendMessage(idExpediteur: string, idDestinataire: string, contenu: string): Observable<any> {
    return this.http.post(`${this.apiUrl}send`, { idExpediteur, idDestinataire, contenu });
  }
}
