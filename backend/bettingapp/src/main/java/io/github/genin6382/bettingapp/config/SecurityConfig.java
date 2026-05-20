package io.github.genin6382.bettingapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())  // disable CSRF for now (for API)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/users/**").permitAll()  // allow all user APIs
                .anyRequest().permitAll()                      // or lock others later
            )
            .formLogin(form -> form.disable())    // disable default login form
            .httpBasic(basic -> basic.disable()); // disable HTTP Basic if you want pure JWT later

        return http.build();
    }
}