package io.github.genin6382.bettingapp.bet;

import io.github.genin6382.bettingapp.bet.dto.request.BetCreateDTO;
import io.github.genin6382.bettingapp.bet.dto.response.BetResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/bets")
public class BetController {

    private final BetService betService;

    public BetController(BetService betService) {
        this.betService = betService;
    }

    @GetMapping
    public Page<BetResponseDTO> getAllBets(
            @PageableDefault(size = 10) Pageable pageable) {
        return betService.getAllBets(pageable);
    }

    @GetMapping("/{id}")
    public BetResponseDTO getBetById(@PathVariable UUID id) {
        return betService.getBetById(id);
    }

    @PostMapping
    public ResponseEntity<BetResponseDTO> createBet(
            @RequestBody @Valid BetCreateDTO request) {

        BetResponseDTO created = betService.createBet(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public BetResponseDTO updateBet(
            @PathVariable UUID id,
            @RequestBody @Valid BetCreateDTO request) {

        return betService.updateBet(id, request);
    }

    @PatchMapping("/{id}/result")
    public BetResponseDTO updateResult(
            @PathVariable UUID id,
            @RequestBody BetResult result) {

        return betService.updateResult(id, result);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBet(@PathVariable UUID id) {
        betService.deleteBet(id);
    }
}