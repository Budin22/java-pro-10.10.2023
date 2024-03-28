package ua.goals.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import ua.goals.security.service.GoalsUserDetailsService;

import java.io.IOException;
@Slf4j
@RequiredArgsConstructor
public class GoalsFilter extends OncePerRequestFilter {
    private final GoalsUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenUserName = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (tokenUserName != null) {
            try{
                UserDetails userDetails = userDetailsService.loadUserByUsername(tokenUserName);

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception e){
                log.error("Error: {}", e.getMessage());
                response.sendError(401, e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }
}
