import { Injectable, signal } from '@angular/core';
import { UserRegisterRequest, UserLoginRequest, UserResponse } from '../models/user.model';
import {environment} from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { inject } from '@angular/core';
import { Observable, tap} from 'rxjs';

@Injectable({
  providedIn: 'root',
})

export class UserService {

  private readonly apiUrl = `${environment.apiBaseUrl}/users`;
  private http = inject(HttpClient);
  private currentUserId = signal<string | null>(sessionStorage.getItem('userId'));

  register(user: UserRegisterRequest): Observable<UserResponse> {
    return this.http.post<UserResponse>(`${this.apiUrl}/register`, user).pipe(
        tap(registeredUser => {
          console.log('INFO: Registered new user:', registeredUser.id);
          sessionStorage.setItem('userId', registeredUser.id);
          this.currentUserId.set(registeredUser.id);
        })
    );
  }

  login(user: UserLoginRequest): Observable<UserResponse> {
    return this.http.post<UserResponse>(`${this.apiUrl}/login`, user).pipe(
        tap(loggedInUser => {
          if (!environment.production) { console.log('INFO: Logged in user:', loggedInUser.id); }
          sessionStorage.setItem('userId', loggedInUser.id);
          this.currentUserId.set(loggedInUser.id);
        })
    );
  }

  getUserProfile(userId: String): Observable<UserResponse> {
    return this.http.get<UserResponse>(`${this.apiUrl}/${userId}`);
  }

  updateUserProfile(userId: String, user: Omit<UserRegisterRequest, 'password'>): Observable<UserResponse> {
    return this.http.put<UserResponse>(`${this.apiUrl}/${userId}`, user).pipe(
        tap(updatedUser => {
          console.log('INFO: Updated user profile:', updatedUser.id);
        })
    );
  }
  
  deleteUserProfile(userId: String): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${userId}`).pipe(
        tap(() => {
          console.log('INFO: Deleted user profile:', userId);
          this.logout();
        })
    );
  }
  
  logout(): void {
    sessionStorage.removeItem('userId');
    this.currentUserId.set(null);
  }
}
