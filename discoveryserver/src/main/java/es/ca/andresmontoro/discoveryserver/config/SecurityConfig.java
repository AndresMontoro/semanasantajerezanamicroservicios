package es.ca.andresmontoro.discoveryserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
    UserDetails user = User.builder()
      .username("eureka")
      .password(passwordEncoder.encode("password"))
      .roles("USER")
      .build();

    return new InMemoryUserDetailsManager(user);
  }

  // Creo un Bean para que Spring lo gestione y por si necesito modificarlo
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
    httpSecurity.csrf(csrf -> csrf.disable())
    .authorizeHttpRequests(auth -> 
        auth.anyRequest().authenticated()
      )
      .httpBasic(Customizer.withDefaults());

    return httpSecurity.build();
  }
}
