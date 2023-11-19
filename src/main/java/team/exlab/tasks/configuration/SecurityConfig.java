package team.exlab.tasks.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import team.exlab.tasks.service.invite_registration.impl.UserDetailService;

import static org.springframework.security.config.Customizer.withDefaults;
import static team.exlab.tasks.util.PathUrlUtil.INVITE_SEND;
import static team.exlab.tasks.util.PathUrlUtil.LOGIN;
import static team.exlab.tasks.util.PathUrlUtil.LOGIN_ADMIN;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    private final UserDetailService userDetailService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(LOGIN_ADMIN, INVITE_SEND).hasAuthority("ADMIN")
                        .requestMatchers(LOGIN).permitAll()
                        .anyRequest().authenticated())
                .httpBasic(withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
