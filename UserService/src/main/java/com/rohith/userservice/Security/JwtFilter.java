package com.rohith.userservice.Security;

import com.rohith.userservice.Dto.UserResponseDto;
import com.rohith.userservice.entity.User;
import com.rohith.userservice.exception.UserServiceException;
import com.rohith.userservice.repository.UserRepository;
import com.rohith.userservice.service.UserService;
import com.rohith.userservice.util.AuthUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final AuthUtils authUtils;
    private final UserRepository userRepository;




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

       final String token =request.getHeader("Authorization");

       if(token==null || !token.startsWith("Bearer")){
           filterChain.doFilter(request,response);
           return ;
       }

       String token_extract= token.substring(7).trim();;

       Integer userid = authUtils.generateUserIdFromClaims(token_extract);
       User user = userRepository.findById(userid).orElseThrow(()->new UserServiceException("user not found", HttpStatusCode.valueOf(400)));


       if(user!=null && SecurityContextHolder.getContext().getAuthentication()==null){


           UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(user,null,null);

           SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);



       }
       filterChain.doFilter(request,response);

    }
}
