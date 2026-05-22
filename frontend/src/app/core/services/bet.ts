import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { BetResponse, BetCreateRequest, BetResult, Page } from '../models/bet.model';

@Injectable({ providedIn: 'root' })
export class BetService {
  private apiUrl = `${environment.apiBaseUrl}/bets`;
  private http = inject(HttpClient);

  getAll(page = 0, size = 10): Observable<Page<BetResponse>> {
    const params = new HttpParams()
      .set('page', page)
      .set('size', size);
    return this.http.get<Page<BetResponse>>(this.apiUrl, { params });
  }

  getById(id: string): Observable<BetResponse> {
    return this.http.get<BetResponse>(`${this.apiUrl}/${id}`);
  }

  create(bet: BetCreateRequest): Observable<BetResponse> {
    return this.http.post<BetResponse>(this.apiUrl, bet);
  }

  update(id: string, bet: BetCreateRequest): Observable<BetResponse> {
    return this.http.put<BetResponse>(`${this.apiUrl}/${id}`, bet);
  }

  updateResult(id: string, result: BetResult): Observable<BetResponse> {
    return this.http.patch<BetResponse>(`${this.apiUrl}/${id}/result`, result, {
      headers: { 'Content-Type': 'application/json' }
    });
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}