package io.github.genin6382.bettingapp.match;

import io.github.genin6382.bettingapp.match.dto.request.MatchCreateDTO;
import io.github.genin6382.bettingapp.match.dto.response.MatchResponseDTO;
import io.github.genin6382.bettingapp.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    private MatchResponseDTO mapToResponse(Match match) {
        return new MatchResponseDTO(match);
    }

    public Page<MatchResponseDTO> getAllMatches(Pageable pageable) {
        return matchRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    public MatchResponseDTO getMatchById(UUID id) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Match not found with id: " + id
                ));
        return mapToResponse(match);
    }

    public MatchResponseDTO createMatch(MatchCreateDTO dto) {
        Match match = new Match();
        match.setTeam1Id(dto.getTeam1Id());
        match.setTeam2Id(dto.getTeam2Id());
        match.setLocation(dto.getLocation());
        match.setMatchDate(dto.getMatchDate());

        Match saved = matchRepository.save(match);
        return mapToResponse(saved);
    }

    public MatchResponseDTO updateMatch(UUID id, MatchCreateDTO dto) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Match not found with id: " + id
                ));

        match.setTeam1Id(dto.getTeam1Id());
        match.setTeam2Id(dto.getTeam2Id());
        match.setLocation(dto.getLocation());
        match.setMatchDate(dto.getMatchDate());

        Match updated = matchRepository.save(match);
        return mapToResponse(updated);
    }

    public MatchResponseDTO updateResult(UUID id, String result) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Match not found with id: " + id
                ));

        match.setResult(result);   //"TEAM1_WIN", "DRAW" , ...
        Match updated = matchRepository.save(match);
        return mapToResponse(updated);
    }

    public void deleteMatch(UUID id) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Match not found with id: " + id
                ));
        matchRepository.delete(match);
    }
}