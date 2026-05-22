import { Routes } from '@angular/router';
import { Register } from './pages/auth/register/register';
import { Login } from './pages/auth/login/login';
import { Dashboard } from './pages/dashboard/dashboard';
import { Profile } from './pages/profile/profile';

export const routes: Routes = [

  {path: '', redirectTo: 'dashboard', pathMatch: 'full' },

  { path: 'login',     component: Login },

  { path: 'register',  component: Register },

  {path: 'dashboard', component: Dashboard},

  {path: 'profile', component: Profile},
  
  { path: '**', redirectTo: 'dashboard' }

];
    