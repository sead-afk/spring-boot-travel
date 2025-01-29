package com.travelapp.rest.configuration;

import com.travelapp.core.service.UserDetailsServiceImpl;
import com.travelapp.core.service.UserService;
import com.travelapp.rest.filters.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfiguration {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/destinations/**").hasRole("USER")
                        .requestMatchers("/api/users/**").authenticated()
                        .requestMatchers("/api/destinations/**").hasRole("ADMIN")
                        .requestMatchers("/api/payments/**").authenticated()
                        .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // Disable CSRF (safe for APIs)
                .cors(Customizer.withDefaults()) // Enable default CORS configuration
                .authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.POST, "/api/auth/register", "/api/auth/login").permitAll() // Allow open POST requests for login and registration
                        .requestMatchers(HttpMethod.GET).permitAll() // Allow open GET requests (adjust as needed)
                        .requestMatchers("/api/users/**").authenticated() // Protect user-related endpoints
                        .requestMatchers("/api/destinations/**").authenticated() // Protect destinations
                        .requestMatchers("/api/flights/**").authenticated() // Protect flights
                        .requestMatchers("/api/hotels/**").authenticated() // Protect hotels
                        .requestMatchers("/api/payments/**").authenticated() // Protect payments
                        .requestMatchers("/api/bookings/**").authenticated() // Protect bookings
                        .requestMatchers("/api/trips/**").authenticated() // Protect trips
                        .requestMatchers("/api/tickets/**").authenticated() // Protect tickets
                        .requestMatchers("/api/rooms/**").authenticated() // Protect rooms
                        .anyRequest().denyAll() // Deny all other requests by default
                )
                .sessionManagement(manager -> manager
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless sessions for JWT
                )
                .authenticationProvider(authenticationProvider()) // Use custom authentication provider
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter
        return http.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
//            throws Exception {
//        return config.getAuthenticationManager();
//    }
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder, UserDetailsServiceImpl userDetailsServiceImpl ) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
               http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(userDetailsServiceImpl) // Use your custom UserDetailsService
                .passwordEncoder(passwordEncoder); // Set the PasswordEncoder

       return authenticationManagerBuilder.build();
   }

}