import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { MessagingComponent } from './components/messaging-component/messaging.component';

export const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'messaging/:userType', component: MessagingComponent }
  ];
