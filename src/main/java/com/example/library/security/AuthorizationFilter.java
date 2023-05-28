package com.example.library.security;

import com.example.library.entity.User;
import com.example.library.enums.ExceptionEnum;
import com.example.library.enums.RoleEnum;
import com.example.library.enums.SubjectEnum;
import com.example.library.exception.BaseException;
import com.example.library.pojo.log.ThrowableLogPOJO;
import com.example.library.util.LogUtil;
import com.example.library.vo.auth.PodUserInfoVo;
import com.example.library.repository.UserRepository;
import com.example.library.service.AuthenticationServiceImpl;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Log4j2
@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {
    private final UserRepository userRepository;
    private final AuthenticationServiceImpl authenticationService;

    private static final String TOKEN_BEARER_TYPE = "Bearer";
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain chain) throws IOException, ServletException {
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
            Optional<PodUserInfoVo> userInfo = authenticationService.getUserFromPod(token);
            if(userInfo.isEmpty())
                throw new BaseException("token is invalid.", ExceptionEnum.NOT_EXIST);
            User user = userRepository.findBySsoId(userInfo.get().getSsoId()).orElseThrow(() -> new BaseException("there is no user by this id.", ExceptionEnum.NOT_EXIST));
            SocketPrincipal socketPrincipal = SocketPrincipal.builder()
                    .ssoId(user.getSsoId())
                    .name(token)
                    .build();
            Collection<RoleEnum> roles = user.getRoles();
            roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(String.format("%s",role.name()))));
            return new UsernamePasswordAuthenticationToken(socketPrincipal, token, authorities);
        }
        catch (Exception e) {
            BaseException exception = new BaseException("token is invalid.", ExceptionEnum.INVALID_TOKEN);
            ThrowableLogPOJO throwableLogPOJO = ThrowableLogPOJO.builder()
                    .message("Exception at UsernamePasswordAuthenticationToken")
                    .details(exception)
                    .ssoId("S")
                    .subject(SubjectEnum.AUTH)
                    .build();
            LogUtil.error(log,throwableLogPOJO);
            throw exception;
        }
    }
}
