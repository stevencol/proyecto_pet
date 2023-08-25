package com.pet.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Data
@Component
public class AuthFilter extends OncePerRequestFilter {


    private final AutProvider provider;

    private List<String> urlsOk = List.of("/api/pet/get-all","/login","/api/person/create");

    @Override
    protected  boolean shouldNotFilter(HttpServletRequest request) throws  ServletException{
        return  urlsOk.stream().anyMatch(url->request.getRequestURI().contains(url));
    }


    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(header==null){
            System.out.println("no autorizado");
        }

        String [] authElements = header.split(" ");

        if(authElements.length!=2 || !"Bearer".equals(authElements[0])){
            System.out.println("no autorizado");

        }

        try{
            Authentication auth = provider.validdateToken(authElements[1]);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }



    }
}
