import { Routes } from '@angular/router';
import { Login } from './pages/auth/login/login';
import { Register } from './pages/auth/register/register';
import { Profile } from './pages/profile/profile';
import { MatchList } from './pages/matches/match-list/match-list';
import { MatchDetail } from './pages/matches/match-detail/match-detail';
import { MyBets } from './pages/bets/my-bets/my-bets';

export const routes: Routes = [
  { path: '',          redirectTo: 'matches', pathMatch: 'full' },
  { path: 'login',     component: Login },
  { path: 'register',  component: Register },
  { path: 'profile',   component: Profile },
  { path: 'matches',   component: MatchList },
  { path: 'matches/:id', component: MatchDetail },
  { path: 'my-bets',   component: MyBets },
  { path: '**',        redirectTo: 'matches' }
];