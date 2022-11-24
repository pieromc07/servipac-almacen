package com.servipac.almacen.security;

import com.servipac.almacen.security.filter.CustomAccessDeniedHandler;
import com.servipac.almacen.security.filter.CustomAuthenticationEntryPoint;
import com.servipac.almacen.security.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf()
                .disable()
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/register").permitAll()
                .antMatchers(HttpMethod.GET, "/roles").permitAll()
                .antMatchers(HttpMethod.GET, "/roles/{id}").permitAll()
                .antMatchers(HttpMethod.POST, "/roles/create").permitAll()
                .antMatchers(HttpMethod.PUT, "/roles/update/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE, "/roles/delete/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/users").permitAll()
                .antMatchers(HttpMethod.GET, "/users/inactive").permitAll()
                .antMatchers(HttpMethod.GET, "/users/{id}").permitAll()
                .antMatchers(HttpMethod.POST, "/users/create").permitAll()
                .antMatchers(HttpMethod.PUT, "/users/update/{id}").permitAll()
                .antMatchers(HttpMethod.PUT, "/users/update/status/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE, "/users/delete/{id}").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(authenticationEntryPoint());

        // SOLO PARA DESARROLLO
        http.headers().frameOptions().disable();

        return  http.build();
    }

    // SOLO PARA DESARROLLO
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().antMatchers("/h2-console/**");
    }
}
