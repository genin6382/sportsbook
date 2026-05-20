package io.github.genin6382.bettingapp.team;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import io.github.genin6382.bettingapp.exception.ResourceNotFoundException;
import io.github.genin6382.bettingapp.team.dto.request.TeamRegistrationDTO;
import io.github.genin6382.bettingapp.team.dto.response.TeamResponseDTO;

import java.util.UUID;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    private TeamResponseDTO mapToResponse(Team team){
        return new TeamResponseDTO(team);
    }

    public Page<TeamResponseDTO> getTeams(Pageable pageable) {
        return teamRepository.findAll(pageable).map(this::mapToResponse);
    }

    public TeamResponseDTO getTeamById(UUID id) {
        return teamRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + id));
    }

    public TeamResponseDTO createTeam(TeamRegistrationDTO teamRequest) {
        Team team = new Team();
        team.setName(teamRequest.getName());
        Team savedTeam = teamRepository.save(team);
        return mapToResponse(savedTeam);
    }

    public void deleteTeam(UUID id) {
        if (!teamRepository.existsById(id)) {
            throw new ResourceNotFoundException("Team not found with id: " + id);
        }
        teamRepository.deleteById(id);
    }

}
