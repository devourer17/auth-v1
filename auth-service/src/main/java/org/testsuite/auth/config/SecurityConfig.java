package org.testsuite.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@EnableWebSecurity
public class SecurityConfig {

    @Value("#{${basic.auth.clients}}")
    private Map<String, String> authClients;

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        List<UserDetails> userDetailsList = new ArrayList<>();
        for (Map.Entry<String, String> entry : authClients.entrySet()) {
            userDetailsList.add(User.withUsername(entry.getKey())
                    .password(passwordEncoder().encode(entry.getValue()))
                    .roles("ADMIN")
                    .build());
        }
        return new InMemoryUserDetailsManager(userDetailsList);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrfConfigurer -> csrfConfigurer.disable())
                .authorizeHttpRequests(requests -> requests
                        //.requestMatchers(HttpMethod.GET, "/login*/**", "/error").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .logout(logoutConfigurer -> logoutConfigurer.invalidateHttpSession(true).clearAuthentication(true))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
