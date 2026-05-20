package io.github.genin6382.bettingapp.user.dto.response;

import java.util.UUID;
import java.time.LocalDateTime;
import io.github.genin6382.bettingapp.user.User;
import lombok.Getter;

@Getter
public class UserResponse {
    private UUID id;
    private String phoneNumber;
    private String email;
    private Double balance;      
    private Double wagered;    
    private LocalDateTime createdAt;
    

   public UserResponse(User user) {
        this.id = user.getId();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
        this.balance = user.getBalance();
        this.wagered = user.getWagered();
        this.createdAt = user.getCreatedAt();
    }
}
