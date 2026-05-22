import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Team } from '../models/team.model';

@Injectable({ providedIn: 'root' })
export class TeamService {
  private apiUrl = `${environment.apiBaseUrl}/teams`;
  private http = inject(HttpClient);

  getAll(): Observable<Team[]> {
    return this.http.get<Team[]>(this.apiUrl);
  }
}