package io.github.genin6382.bettingapp.user;

import io.github.genin6382.bettingapp.user.dto.request.UserRegistrationDTO;
import io.github.genin6382.bettingapp.user.dto.request.UserUpdateDTO;
import io.github.genin6382.bettingapp.user.dto.response.UserResponse;
import io.github.genin6382.bettingapp.user.dto.request.UserLoginDTO;
import io.github.genin6382.bettingapp.exception.ResourceNotFoundException;
import io.github.genin6382.bettingapp.exception.InvalidCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private UserResponse mapToResponse(User user) {
        return new UserResponse(user);  
    }

    public Page<UserResponse> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(this::mapToResponse);
    }

    public UserResponse getUserByID(UUID id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return mapToResponse(user);
    }

    public UserResponse createUser(UserRegistrationDTO request){
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return mapToResponse(user);
    }

    public UserResponse updateUser(UUID id , UserUpdateDTO request){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setEmail(request.getEmail());
        userRepository.save(user);
        return mapToResponse(user);
    }

    public void deleteUser(UUID id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
    }

    public UserResponse login(UserLoginDTO request) {
        User user = userRepository.findByPhoneNumber(request.getPhoneNumber())
            .orElseThrow(() -> new ResourceNotFoundException(
                "User not found with phone number: " + request.getPhoneNumber()
            ));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid phone or password");
        }

        return new UserResponse(user);
    }

}