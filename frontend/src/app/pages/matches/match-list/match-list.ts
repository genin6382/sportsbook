import { Component, OnInit, inject, signal } from '@angular/core';
import { Router} from '@angular/router';
import { MatchService } from '../../../core/services/match';
import { MatchResponse } from '../../../core/models/match.model';

@Component({
  selector: 'app-match-list',
  imports: [],
  templateUrl: './match-list.html',
})
export class MatchList implements OnInit {
  private matchService = inject(MatchService);
  private router = inject(Router);

  matches = signal<MatchResponse[]>([]);
  currentPage = signal(0);
  totalPages = signal(0);
  loading = signal(true);
  error = signal('');

  ngOnInit() {
    this.loadMatches();
  }

  loadMatches(page = 0) {
    this.loading.set(true);
    this.matchService.getAll(page).subscribe({
      next: (data) => {
        this.matches.set(data.content);
        this.totalPages.set(data.totalPages);
        this.currentPage.set(data.number);
        this.loading.set(false);
      },
      error: () => {
        this.error.set('Failed to load matches');
        this.loading.set(false);
      }
    });
  }

  nextPage() {
    if (this.currentPage() < this.totalPages() - 1) {
      this.loadMatches(this.currentPage() + 1);
    }
  }

  prevPage() {
    if (this.currentPage() > 0) {
      this.loadMatches(this.currentPage() - 1);
    }
  }

  goToMatch(id: string) {
    this.router.navigate(['/matches', id]);
  }
}