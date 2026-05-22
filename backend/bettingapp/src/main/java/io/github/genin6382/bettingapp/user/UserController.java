package io.github.genin6382.bettingapp.user;

import io.github.genin6382.bettingapp.user.dto.request.UserLoginDTO;
import io.github.genin6382.bettingapp.user.dto.request.UserRegistrationDTO;
import io.github.genin6382.bettingapp.user.dto.request.UserUpdateDTO;
import io.github.genin6382.bettingapp.user.dto.response.UserResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.PageRequest;

import jakarta.validation.Valid;

import java.util.UUID;


@RestController
@RequestMapping("/api/users")
class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Page<UserResponse> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userService.getAllUsers(pageable);
    }
    @GetMapping("/{id}")
    public UserResponse getUserByID(@PathVariable UUID id){
        return userService.getUserByID(id);
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRegistrationDTO request){
        UserResponse userResponse = userService.createUser(request);
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id, @RequestBody @Valid UserUpdateDTO request){
        UserResponse userResponse = userService.updateUser(id, request);
        return ResponseEntity.ok(userResponse);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build(); 
    }
    
    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody @Valid UserLoginDTO request) {

        UserResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }
}