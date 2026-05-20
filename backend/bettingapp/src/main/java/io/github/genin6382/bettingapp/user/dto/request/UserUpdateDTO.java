package io.github.genin6382.bettingapp.user.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdateDTO {
 
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @Email(message = "Must be a valid email")
    @NotBlank
    private String email;
}
    