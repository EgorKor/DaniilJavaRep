package education.testProject.config;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @SneakyThrows
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity){
        httpSecurity.userDetailsService(userDetailsService);
        httpSecurity.csrf(c -> c.disable());
        httpSecurity.formLogin(form ->
                                form.loginPage("/auth/login")
                                        .loginProcessingUrl("/process_login")
                                        .defaultSuccessUrl("/hello", true))
                .logout(logout -> logout.permitAll())
                .authorizeHttpRequests(
                        req -> req.requestMatchers("/auth/login")
                                .permitAll()
                                .requestMatchers("/api/users")
                                .permitAll()
                                .anyRequest().authenticated()
                );
        return httpSecurity.build();
    }
}
