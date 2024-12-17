package ru.effective_mobile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity(debug = true)
public class TasksConfig {
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
            .anyRequest().authenticated());
    //Для доступа к H2-Console
    http.csrf(csrf -> csrf.disable());
    http.headers(h -> h.frameOptions(f -> f.disable()));
    return http.build();
  }
}
