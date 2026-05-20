package io.github.genin6382.bettingapp.match.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class MatchCreateDTO {

    @NotNull(message = "team1_id is required")
    private UUID team1Id;

    @NotNull(message = "team2_id is required")
    private UUID team2Id;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "matchDate is required")
    private LocalDateTime matchDate;
}