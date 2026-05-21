package io.github.genin6382.bettingapp.bet;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BetRepository extends JpaRepository<Bet, UUID> {
}