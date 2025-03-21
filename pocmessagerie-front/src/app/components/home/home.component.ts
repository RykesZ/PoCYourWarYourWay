import { Component } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  constructor(private router: Router) {}

  goToMessaging(userType: string): void {
    // Redirige l'utilisateur vers la page de messagerie en fonction du type d'utilisateur choisi
    this.router.navigate(['/messaging'], { queryParams: { userType } });
  }
}
