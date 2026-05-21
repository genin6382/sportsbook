package io.github.genin6382.bettingapp.bet.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class BetCreateDTO {

    @NotNull(message = "matchId is required")
    private UUID matchId;

    @NotNull(message = "userId is required")
    private UUID userId;

    @NotNull(message = "wager is required")
    @Positive(message = "wager must be positive")
    private BigDecimal wager;

    @NotNull(message = "odds is required")
    @Positive(message = "odds must be positive")
    private BigDecimal odds;
}