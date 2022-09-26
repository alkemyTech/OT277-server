package com.alkemy.ong.security.config;

import com.alkemy.ong.security.filter.JwtRequestFilter;
import com.alkemy.ong.security.service.UserDetailsCustomService;
import com.alkemy.ong.utils.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsCustomService userDetailsCustomerService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsCustomerService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    private static final String[] SWAGGER_ENDPOINTS = {
            "/api/docs",
            "/v2/api-docs",
            "/swagger-ui/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/register", "/auth/login")
                .permitAll()

                .antMatchers(HttpMethod.GET, "/auth/me", "/users/**")
                .hasAnyRole(RoleType.ADMIN.name(), RoleType.USER.name())

                .antMatchers(SWAGGER_ENDPOINTS).permitAll()

                .antMatchers(HttpMethod.GET, "/organization/public/**")
                .hasRole(RoleType.USER.name())

                .antMatchers(HttpMethod.GET,  "/news/**", "/members", "/slides/**", "/slide")
                .hasRole(RoleType.USER.name())

                .antMatchers(HttpMethod.DELETE, "/users/**")
                .hasAnyRole(RoleType.ADMIN.name(), RoleType.USER.name())

                .antMatchers(HttpMethod.POST, "/activities", "/slides/**", "/testimonials/**")
                .hasRole(RoleType.ADMIN.name())

                .antMatchers(HttpMethod.PUT, "/activities/**", "/categories/**", "/slides/**", "/testimonials/**")
                .hasRole(RoleType.ADMIN.name())

                .antMatchers(HttpMethod.DELETE, "/categories/**", "/news/**", "/members/**", "/slides/**", "/organization/**", "/testimonials/**")
                .hasRole(RoleType.ADMIN.name())

                .antMatchers(HttpMethod.PATCH, "/users/**")
                .hasAnyRole(RoleType.ADMIN.name(), RoleType.USER.name())

                .antMatchers(HttpMethod.POST, "/members")
                .hasAnyRole(RoleType.USER.name())

                .antMatchers(HttpMethod.PUT, "/members/**", "/comments/**")
                .hasAnyRole(RoleType.USER.name())

                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(authenticationEntryPoint());
    }
}
