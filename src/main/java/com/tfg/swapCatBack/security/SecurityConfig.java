package com.tfg.swapCatBack.security;


import com.tfg.swapCatBack.security.handlers.AuthenticationErrorHandling;
import com.tfg.swapCatBack.security.handlers.AuthorizationErrorHandler;
import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final AuthenticationErrorHandling authenticationErrorHandling;
    private final AuthorizationErrorHandler authorizationErrorHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers("/admin/**")
                        .hasAuthority("ADMIN")
                    .antMatchers("/api/**")
                        .hasAnyAuthority("ADMIN", "USER")
                    .and()
                .httpBasic()
                .authenticationEntryPoint(authenticationErrorHandling)
                    .and()
                .headers().frameOptions().disable()
                    .and()
                .exceptionHandling().accessDeniedHandler(authorizationErrorHandler);
    }

}