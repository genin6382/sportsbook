package io.github.genin6382.bettingapp.bet.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import io.github.genin6382.bettingapp.bet.Bet;
import io.github.genin6382.bettingapp.bet.BetResult;
import lombok.Getter;

@Getter
public class BetResponseDTO {

    private UUID id;
    private UUID matchId;
    private UUID userId;
    private BigDecimal wager;
    private BigDecimal odds;
    private BetResult result;
    private BigDecimal potentialPayout;
    private LocalDateTime createdAt;

    public BetResponseDTO(Bet bet) {
        this.id = bet.getId();
        this.matchId = bet.getMatch().getId();
        this.userId = bet.getUser().getId();
        this.wager = bet.getWager();
        this.odds = bet.getOdds();
        this.result = bet.getResult();
        this.potentialPayout = bet.getPotentialPayout();
        this.createdAt = bet.getCreatedAt();
    }
}