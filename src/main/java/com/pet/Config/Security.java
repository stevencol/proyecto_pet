package com.pet.Config;


import com.pet.auth.AccesDenied;
import com.pet.auth.AutProvider;
import com.pet.auth.AuthFilter;
import com.pet.enums.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity


public class Security {



 private final AutProvider provider;
 private final AccesDenied accesDenied;


 private  final  AuthFilter authFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .exceptionHandling().accessDeniedHandler(accesDenied)
                .and()
                .cors().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable() // Desactiva la protección CSRF
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/pet/get-all", "/login","/api/person/create").permitAll()
                        .requestMatchers("/api/pet/get-all").hasAnyAuthority(String.valueOf(Roles.ADMIN))
                        .anyRequest().authenticated());

        return httpSecurity.build();
    }



}
