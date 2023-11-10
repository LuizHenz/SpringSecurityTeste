package com.example.spring_security.config;

import com.example.spring_security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    UserService userService;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
          .cors(cors -> cors.disable())
          .csrf(csrf -> csrf.disable())
          .authorizeHttpRequests(auth -> auth
            .requestMatchers("/user/livreUm").permitAll()
            .requestMatchers("/user/registro/*").permitAll()
            .requestMatchers("/user/registro/**").permitAll()
            .anyRequest().authenticated()
          );

        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    static PasswordEncoder psEncoder(){
        return new BCryptPasswordEncoder();
    }


    protected void authManager(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService);

    }
}
