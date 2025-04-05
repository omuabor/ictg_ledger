package com.stanbic.service.multipay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(customizer -> customizer.disable());
        httpSecurity.authorizeHttpRequests( request -> request.anyRequest().authenticated());
//        httpSecurity.authorizeHttpRequests( request -> request.requestMatchers("register", "login")
//                .permitAll()
//                .anyRequest().authenticated());
//        httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return httpSecurity.build();

    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
        UserDetails admin = User.withUsername("ictg-ledger")
                .password(passwordEncoder.encode("password1"))
                .roles("ADMIN")
                .build();

        UserDetails simpleUser = User.withUsername("deacon1")
                .password(passwordEncoder.encode("deacon123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, simpleUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
