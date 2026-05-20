package io.github.genin6382.bettingapp.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByPhoneNumber(String phoneNumber);
}
