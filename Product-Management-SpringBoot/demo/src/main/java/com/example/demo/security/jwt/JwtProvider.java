package com.example.demo.security.jwt;

import com.example.demo.security.userPrincipal.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    private String jwtSecret="dinhtam.ftu2@gmail.com";
    private int jwtExpiration= 86400;
    @Autowired
    UserDetailsService userDetailsService;
    public String createToken(Authentication authentication){
        UserPrinciple userPrinciple= (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrinciple.getUsername()).setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime()+jwtExpiration*1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    public boolean validateToken(String token){
       try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
       }catch(SignatureException e){
            logger.error("Invalid JWT signature -> Message: {}", e);
       }catch (MalformedJwtException e){
           logger.error("The token invalid format -> Message: {}", e);
       }catch (UnsupportedJwtException e){
           logger.error("Unsupport jwt -> Message: {}", e);
       }catch (ExpiredJwtException e){
           logger.error("Expired jwt -> Message:{}",e);
       }catch (IllegalArgumentException e){
           logger.error("JWT claimed token is empty -> Message: {}", e);
       }
       return false;
    }
    public String getUsernameFromToken(String token){
        String username= Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(token).getBody().getSubject();
        return username;
    }
    public Authentication getAuthentication(String token) {
        String username = getUsernameFromToken(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

}
