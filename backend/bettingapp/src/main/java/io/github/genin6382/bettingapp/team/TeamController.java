package io.github.genin6382.bettingapp.team;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import io.github.genin6382.bettingapp.team.dto.request.TeamRegistrationDTO;
import io.github.genin6382.bettingapp.team.dto.response.TeamResponseDTO;
import jakarta.validation.Valid;

import java.util.UUID;



@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public Page<TeamResponseDTO> getTeams( @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size ) {
        Pageable pageable = PageRequest.of(page, size);
        return teamService.getTeams(pageable);
    }
    
    @GetMapping("/{id}")
    public TeamResponseDTO getTeamById(@PathVariable UUID id) {
        return teamService.getTeamById(id);
    }

    @PostMapping
    public ResponseEntity<TeamResponseDTO> createTeam(@RequestBody @Valid TeamRegistrationDTO teamRequest) {
        TeamResponseDTO createdTeam = teamService.createTeam(teamRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable UUID id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }
}
