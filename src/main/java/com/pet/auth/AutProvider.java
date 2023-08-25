package com.pet.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.pet.dtos.PersonGetPostSImple;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Component
public class AutProvider {


    private Map<String , PersonGetPostSImple> tokenList = new HashMap<>();

    public String createToken(PersonGetPostSImple jwt){

        Date now = new Date();
        Date validity = new Date(now.getTime()+ 2629746000l);

        Algorithm algorithm = Algorithm.HMAC256("millave");

        String tokemn = JWT.create().withClaim("id", jwt.toString())
                .withClaim("nombre", jwt.getFirtsName() + " " + jwt.getLastName())
                .withIssuedAt(now)
                .withExpiresAt(validity).sign(algorithm);


        tokenList.put(tokemn, jwt );

        return  tokemn;

    }

    public Authentication validdateToken(String token) throws AuthenticationException{

        JWT.require(Algorithm.HMAC256("millave")).build().verify(token);

        PersonGetPostSImple existe = tokenList.get(token);
        if(existe== null){
            throw  new BadCredentialsException("Usuario incorrecto");
        }

        HashSet<SimpleGrantedAuthority> roleSimpleGrantedAuthorities = new HashSet<>();
        roleSimpleGrantedAuthorities.add(new SimpleGrantedAuthority("role_+basedadtos")); /// Rol
        roleSimpleGrantedAuthorities.add(new SimpleGrantedAuthority("autotida")); ///autorida

  return  new UsernamePasswordAuthenticationToken(existe, token, roleSimpleGrantedAuthorities);

    }



}
