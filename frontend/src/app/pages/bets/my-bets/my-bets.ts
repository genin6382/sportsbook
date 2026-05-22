import { Component, OnInit, inject, signal } from '@angular/core';
import { BetService } from '../../../core/services/bet';
import { BetResponse } from '../../../core/models/bet.model';

@Component({
  selector: 'app-my-bets',
  templateUrl: './my-bets.html',
})
export class MyBets implements OnInit {
  private betService = inject(BetService);

  bets = signal<BetResponse[]>([]);
  currentPage = signal(0);
  totalPages = signal(0);
  loading = signal(true);
  error = signal('');

  ngOnInit() {
    this.loadBets();
  }

  loadBets(page = 0) {
    this.loading.set(true);
    this.betService.getAll(page).subscribe({
      next: (data) => {
        this.bets.set(data.content);
        this.totalPages.set(data.totalPages);
        this.currentPage.set(data.number);
        this.loading.set(false);
      },
      error: () => {
        this.error.set('Failed to load bets');
        this.loading.set(false);
      }
    });
  }

  nextPage() {
    if (this.currentPage() < this.totalPages() - 1)
      this.loadBets(this.currentPage() + 1);
  }

  prevPage() {
    if (this.currentPage() > 0)
      this.loadBets(this.currentPage() - 1);
  }
}