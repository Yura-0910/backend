package ru.effective_mobile.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.effective_mobile.jwt.FilterAuthJWT;

@Configuration
@EnableWebSecurity(debug = true)
@AllArgsConstructor
public class TasksConfig {
  private FilterAuthJWT filterAuthJWT;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(new AntPathRequestMatcher("/api/add")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/api/edit")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/api/editStatus")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/api/editExecutor")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/api/addComment")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/api/allTasks/**")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/api/delete")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/v3/**")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/api/signUpJWT")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/api/signInJWT")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/error")).permitAll()
            .anyRequest().authenticated())
        .addFilterBefore(filterAuthJWT, UsernamePasswordAuthenticationFilter.class);
    //Для доступа к H2-Console
    http.csrf(csrf -> csrf.disable());
    http.headers(h -> h.frameOptions(f -> f.disable()));
    return http.build();
  }
}
