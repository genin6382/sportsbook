export type BetResult = 'WON' | 'LOSS' | 'PENDING' | 'VOIDED';

export interface BetResponse {
  id: string;
  matchId: string;
  userId: string;
  wager: number;
  odds: number;
  result: BetResult | null;
  potentialPayout: number;
  createdAt: string;
}

export interface BetCreateRequest {
  matchId: string;
  userId: string;
  wager: number;
  odds: number;
}

// Spring Boot Page<T> wrapper
export interface Page<T> {
  content: T[];
    totalElements: number;
    totalPages: number;
    number: number;       // current page (0-indexed)
    size: number;
}