package io.github.genin6382.bettingapp.bet;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.hibernate.annotations.CreationTimestamp;

import io.github.genin6382.bettingapp.match.Match;
import io.github.genin6382.bettingapp.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


@Entity
@Table(
    name = "bets",
    indexes ={
        @Index(name = "idx_bet_user_id", columnList = "user_id"), //All bets by a user
        @Index(name = "idx_bet_match_id", columnList = "match_id") // All bets on a match for quick retrieval of betting activity on a match
    }
)
@Getter
@Setter
@NoArgsConstructor
public class Bet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "wager", nullable = false)
    private BigDecimal wager;

    @Column(name = "odds", nullable = false)
    private BigDecimal odds;

    @Enumerated(EnumType.STRING)
    @Column(name="result", nullable = false)
    private BetResult result= BetResult.PENDING;

    @Column(name="potential_payout", nullable = false)
    private BigDecimal potentialPayout;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}


