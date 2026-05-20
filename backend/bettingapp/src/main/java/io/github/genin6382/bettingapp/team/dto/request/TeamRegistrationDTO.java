package io.github.genin6382.bettingapp.team.dto.request;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class TeamRegistrationDTO {
    
    @NotBlank(message = "Team name is required")
    private String name;

}
