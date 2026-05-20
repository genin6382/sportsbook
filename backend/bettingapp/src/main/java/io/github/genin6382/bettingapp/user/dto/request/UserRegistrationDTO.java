package io.github.genin6382.bettingapp.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRegistrationDTO {
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String phoneNumber;
    
    @Email(message = "Must be a valid email")
    @NotBlank
    private String email;
    
    @NotBlank(message = "Password is required")
    private String password;
    
}