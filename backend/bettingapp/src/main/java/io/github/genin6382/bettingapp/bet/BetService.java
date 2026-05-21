package io.github.genin6382.bettingapp.bet;

import io.github.genin6382.bettingapp.bet.dto.request.BetCreateDTO;
import io.github.genin6382.bettingapp.bet.dto.response.BetResponseDTO;
import io.github.genin6382.bettingapp.exception.ResourceNotFoundException;
import io.github.genin6382.bettingapp.match.Match;
import io.github.genin6382.bettingapp.match.MatchRepository;
import io.github.genin6382.bettingapp.user.User;
import io.github.genin6382.bettingapp.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BetService {

    private final BetRepository betRepository;
    private final MatchRepository matchRepository;
    private final UserRepository userRepository;

    public BetService(BetRepository betRepository,
                      MatchRepository matchRepository,
                      UserRepository userRepository) {
        this.betRepository = betRepository;
        this.matchRepository = matchRepository;
        this.userRepository = userRepository;
    }

    private BetResponseDTO mapToResponse(Bet bet) {
        return new BetResponseDTO(bet);
    }

    public Page<BetResponseDTO> getAllBets(Pageable pageable) {
        return betRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    public BetResponseDTO getBetById(UUID id) {
        Bet bet = betRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Bet not found with id: " + id
                ));
        return mapToResponse(bet);
    }

    public BetResponseDTO createBet(BetCreateDTO dto) {
        Match match = matchRepository.findById(dto.getMatchId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Match not found with id: " + dto.getMatchId()
                ));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id: " + dto.getUserId()
                ));

        Bet bet = new Bet();
        bet.setMatch(match);
        bet.setUser(user);
        bet.setWager(dto.getWager());
        bet.setOdds(dto.getOdds());

        // potential_payout = wager * odds
        bet.setPotentialPayout(dto.getWager().multiply(dto.getOdds()));

        bet.setResult(BetResult.PENDING);

        Bet saved = betRepository.save(bet);
        return mapToResponse(saved);
    }

    public BetResponseDTO updateBet(UUID id, BetCreateDTO dto) {
        Bet bet = betRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Bet not found with id: " + id
                ));

        Match match = matchRepository.findById(dto.getMatchId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Match not found with id: " + dto.getMatchId()
                ));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id: " + dto.getUserId()
                ));

        bet.setMatch(match);
        bet.setUser(user);
        bet.setWager(dto.getWager());
        bet.setOdds(dto.getOdds());
        bet.setPotentialPayout(dto.getWager().multiply(dto.getOdds()));

        Bet updated = betRepository.save(bet);
        return mapToResponse(updated);
    }

    public BetResponseDTO updateResult(UUID id, BetResult result) {
        Bet bet = betRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Bet not found with id: " + id
                ));

        bet.setResult(result);
        Bet updated = betRepository.save(bet);
        return mapToResponse(updated);
    }

    public void deleteBet(UUID id) {
        Bet bet = betRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Bet not found with id: " + id
                ));
        betRepository.delete(bet);
    }
}