package com.example.library.security;

import com.example.library.entity.User;
import com.example.library.enums.RoleEnum;
import com.example.library.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {
    @Value("${jwt.secret}")
    private String secret;
    private final UserRepository userRepository;

    private static final String TOKEN_BEARER_TYPE = "Bearer";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);


        if (header == null || !header.startsWith(TOKEN_BEARER_TYPE)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token == null)
            return null;
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        try{
            token = token.replace(TOKEN_BEARER_TYPE + " ", "");
            if (token.isEmpty())
                return null;
            SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
            Jws<Claims> jwt = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            Long userId = jwt.getBody().get("user_id", Long.class);
            Optional<User> user = userRepository.findById(userId);

            if (user.isEmpty()) {
                throw new RuntimeException("User not found");
            }

            Collection<RoleEnum> roles = user.get().getRoles();
            roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.name())));
            return new UsernamePasswordAuthenticationToken(userId, null, authorities);
        }
        catch (Exception e) {
            throw new RuntimeException("Token is invalid");
        }
    }
}
