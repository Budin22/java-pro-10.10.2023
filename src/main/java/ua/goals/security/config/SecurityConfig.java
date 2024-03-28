package ua.goals.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ua.goals.repo.UserJpaRepo;
import ua.goals.security.auth.GoalsAuthEntryPoint;
import ua.goals.security.filter.GoalsFilter;
import ua.goals.security.service.GoalsUserDetailsService;

import java.util.List;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserJpaRepo userJpaRepo;
    @Autowired
    private GoalsAuthEntryPoint goalsAuthEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests( conf ->
                        conf
                                .requestMatchers(antMatcher("/task/**")).permitAll()
                                .requestMatchers(antMatcher("/level/**")).permitAll()
                                .requestMatchers(antMatcher("/user/**")).hasAuthority("ADMIN")
                                .anyRequest().authenticated())
                .exceptionHandling(conf -> conf.authenticationEntryPoint(goalsAuthEntryPoint))
                .sessionManagement(conf -> conf.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(goalsFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public GoalsFilter goalsFilter(){
        return new GoalsFilter(goalsUserDetailsService());
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(HttpHeaders.CONTENT_TYPE, HttpHeaders.AUTHORIZATION));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowedMethods(List.of(HttpMethod.GET.name(),HttpMethod.POST.name(), HttpMethod.DELETE.name(), HttpMethod.PUT.name(), HttpMethod.OPTIONS.name()));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public GoalsUserDetailsService goalsUserDetailsService(){
        return new GoalsUserDetailsService(userJpaRepo);
    }
}
