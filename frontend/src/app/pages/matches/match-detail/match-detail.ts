import { Component, OnInit, inject, signal } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormControl, Validators, ReactiveFormsModule } from '@angular/forms';
import { MatchService } from '../../../core/services/match';
import { BetService } from '../../../core/services/bet';
import { MatchResponse } from '../../../core/models/match.model';
import { BetResponse } from '../../../core/models/bet.model';

@Component({
  selector: 'app-match-detail',
  imports: [ReactiveFormsModule],
  templateUrl: './match-detail.html',
})
export class MatchDetail implements OnInit {
  private matchService = inject(MatchService);
  private betService = inject(BetService);
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  match = signal<MatchResponse | null>(null);
  placedBet = signal<BetResponse | null>(null);
  loading = signal(true);
  betSuccess = signal(false);
  error = signal('');

  private userId = sessionStorage.getItem('userId') || '';

  betForm = new FormGroup({
    wager: new FormControl<number | null>(null, [Validators.required, Validators.min(1)]),
    odds:  new FormControl<number | null>(null, [Validators.required, Validators.min(1)])
  });

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id')!;
    this.matchService.getById(id).subscribe({
      next: (data) => {
        this.match.set(data);
        this.loading.set(false);
      },
      error: () => {
        this.error.set('Failed to load match');
        this.loading.set(false);
      }
    });
  }

  placeBet() {
    if (this.betForm.invalid || !this.match()) return;

    this.betService.create({
      matchId: this.match()!.id,
      userId: this.userId,
      wager: this.betForm.value.wager!,
      odds: this.betForm.value.odds!,
    }).subscribe({
      next: (bet) => {
        this.placedBet.set(bet);
        this.betSuccess.set(true);
        this.betForm.reset();
      },
      error: () => this.error.set('Failed to place bet. Check your balance.')
    });
  }

  goBack() {
    this.router.navigate(['/matches']);
  }
}