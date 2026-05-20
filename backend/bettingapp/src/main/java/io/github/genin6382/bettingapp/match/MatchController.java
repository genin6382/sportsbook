package io.github.genin6382.bettingapp.match;

import io.github.genin6382.bettingapp.match.dto.request.MatchCreateDTO;
import io.github.genin6382.bettingapp.match.dto.response.MatchResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public Page<MatchResponseDTO> getAllMatches(
            @PageableDefault(size = 10) Pageable pageable) {
        return matchService.getAllMatches(pageable);
    }

    @GetMapping("/{id}")
    public MatchResponseDTO getMatchById(@PathVariable UUID id) {
        return matchService.getMatchById(id);
    }

    @PostMapping
    public ResponseEntity<MatchResponseDTO> createMatch(@RequestBody @Valid MatchCreateDTO request) {
        MatchResponseDTO created = matchService.createMatch(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public MatchResponseDTO updateMatch(@PathVariable UUID id,@RequestBody @Valid MatchCreateDTO request) {
        return matchService.updateMatch(id, request);
    }

    @PatchMapping("/{id}/result")
    public MatchResponseDTO updateResult(@PathVariable UUID id, @RequestBody String result) {
        return matchService.updateResult(id, result);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMatch(@PathVariable UUID id) {
        matchService.deleteMatch(id);
    }
}