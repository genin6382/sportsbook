package io.github.genin6382.bettingapp.match.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import io.github.genin6382.bettingapp.match.Match;

import lombok.Getter;

@Getter
public class MatchResponseDTO {

    private UUID id;
    private UUID team1Id;
    private String team1Name;
    private UUID team2Id;
    private String team2Name;
    private String result;
    private String location;
    private LocalDateTime matchDate;
    private LocalDateTime createdAt;

    public MatchResponseDTO(Match match) {
        this.id = match.getId();
        this.team1Id = match.getTeam1().getId();
        this.team1Name = match.getTeam1().getName();
        this.team2Id = match.getTeam2().getId();
        this.team2Name = match.getTeam2().getName();
        this.result = match.getResult();
        this.location = match.getLocation();
        this.matchDate = match.getMatchDate();
        this.createdAt = match.getCreatedAt();
    }
}