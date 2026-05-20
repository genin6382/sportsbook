package io.github.genin6382.bettingapp.match.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import io.github.genin6382.bettingapp.match.Match;

import lombok.Getter;

@Getter
public class MatchResponseDTO {

    private UUID id;
    private UUID team1_id;
    private UUID team2_id;
    private String result;
    private String location;
    private LocalDateTime matchDate;
    private LocalDateTime createdAt;

    public MatchResponseDTO(Match match) {
        this.id = match.getId();
        this.team1_id = match.getTeam1Id();
        this.team2_id = match.getTeam2Id();
        this.result = match.getResult();
        this.location = match.getLocation();
        this.matchDate = match.getMatchDate();
        this.createdAt = match.getCreatedAt();
    }
}