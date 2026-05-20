package io.github.genin6382.bettingapp.team.dto.response;

import java.time.LocalDateTime;

import io.github.genin6382.bettingapp.team.Team;
import lombok.Getter;

import java.util.UUID;

@Getter
public class TeamResponseDTO {
    
    private UUID id;
    private String name;
    private LocalDateTime createdAt;

    public TeamResponseDTO(Team team){
        this.id = team.getId();
        this.name = team.getName();
        this.createdAt = team.getCreatedAt();
    }
}
