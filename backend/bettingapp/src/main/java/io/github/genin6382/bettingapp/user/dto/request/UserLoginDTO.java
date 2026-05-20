package io.github.genin6382.bettingapp.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginDTO {
    @NotBlank(message="Phone number is required")
    private String phoneNumber;

    @NotBlank(message="Password is required")
    private String password;
}
