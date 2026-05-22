export interface MatchResponse {
  id: string;
  team1Id: string;
  team1Name: string;
  team2Id: string;
  team2Name: string;
  result: string | null;
  location: string;
  matchDate: string;
  createdAt: string;
}

export interface MatchCreateRequest {
  team1Id: string;
  team2Id: string;
  location: string;
  matchDate: string;
}

// Spring Boot Page<T> wrapper
export interface Page<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  number: number;       // current page (0-indexed)
  size: number;
}