package com.pgm.shopserver.security;

import com.pgm.shopserver.domain.Role;
import com.pgm.shopserver.security.jwt.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig { //시큐리티는 컨트롤러 앞에 꽂힌다
    private final CustomUserDetailsService userDetailsService;

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter();
    }

        @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(httpSecurityCorsConfigurer -> corsConfigurationsource())
                //CORS (Cross-Origin Resource Sharing) : 웹 브라우저에서 다른 출처(origin)의 리소스에 접근할 수 있도록 허용하는
                //보안 메커니즘
                .sessionManagement((session)->session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authz)->authz //접근권한
                        .requestMatchers("/api/authentication/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/product/**").permitAll()
                        .requestMatchers("/api/product/**").hasRole(Role.ADMIN.name())
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build(); //필터한후에 꽂아줄때
            // 사용자 정의 JWT 인증 필터(jwtAuthorizationFilter)를 Spring Security의 필터 체인에 추가
            // 이 필터는 Spring Security의 기본 인증 필터(UsernamePasswordAuthenticationFilter)보다 먼저 실행
    }

    @Bean
    CorsConfigurationSource corsConfigurationsource() { // 허용범위설정
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*")); //출처
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); //설정사용하겠다
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}