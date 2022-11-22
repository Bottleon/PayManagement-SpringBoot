package com.example.demo.common.config;

import com.example.demo.common.exception.CustomAuthenticationEntryPoint;
import com.example.demo.common.filter.CustomAuthenticationFilter;
import com.example.demo.common.filter.CustomAuthorizationFilter;
import com.example.demo.common.handler.LoginFailureHandler;
import com.example.demo.common.provider.CustomAuthenticationProvider;
import com.example.demo.hr.user.service.UserService;
import com.example.demo.hr.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)));
        customAuthenticationFilter.setFilterProcessesUrl("/user/login");
        http.csrf().disable();
        http.httpBasic().disable();
        http.headers().frameOptions().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.formLogin().usernameParameter("id").passwordParameter("password").loginProcessingUrl("/user/login").failureHandler(loginFailureHandler()).permitAll();
        http.authorizeRequests().antMatchers("/user/login/**","/user/token/refresh/**").permitAll();
        http.authorizeRequests().antMatchers(GET,"/api/**").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(GET,"/user/**").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(POST,"/store/**").hasAnyAuthority("ROLE_EMPLOYER");
        http.authorizeRequests().anyRequest().authenticated();
        http.authenticationProvider(customAuthenticationProvider());
        http.exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilter(customAuthenticationFilter);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider(){
        return new CustomAuthenticationProvider(userService,passwordEncoder());
    }

    @Bean
    public LoginFailureHandler loginFailureHandler(){
        return new LoginFailureHandler();
    }
}
