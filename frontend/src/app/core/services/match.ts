import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { MatchResponse, MatchCreateRequest, Page } from '../models/match.model';

@Injectable({ providedIn: 'root' })
export class MatchService {
  private apiUrl = `${environment.apiBaseUrl}/matches`;
  private http = inject(HttpClient);

  getAll(page = 0, size = 10): Observable<Page<MatchResponse>> {
    const params = new HttpParams()
      .set('page', page)
      .set('size', size);
    return this.http.get<Page<MatchResponse>>(this.apiUrl, { params });
  }

  getById(id: string): Observable<MatchResponse> {
    return this.http.get<MatchResponse>(`${this.apiUrl}/${id}`);
  }

  create(match: MatchCreateRequest): Observable<MatchResponse> {
    return this.http.post<MatchResponse>(this.apiUrl, match);
  }

  update(id: string, match: MatchCreateRequest): Observable<MatchResponse> {
    return this.http.put<MatchResponse>(`${this.apiUrl}/${id}`, match);
  }

  updateResult(id: string, result: string): Observable<MatchResponse> {
    return this.http.patch<MatchResponse>(`${this.apiUrl}/${id}/result`, result, {
      headers: { 'Content-Type': 'application/json' }
    });
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}