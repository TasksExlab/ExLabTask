package team.exlab.tasks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import team.exlab.tasks.service.dto.user.JwtResponse;
import team.exlab.tasks.service.dto.user.LoginUserDtoRequest;
import team.exlab.tasks.service.interfaces.IAuthService;
import team.exlab.tasks.service.interfaces.IJwtService;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final IJwtService jwtService;

    public JwtResponse createAuthToken(LoginUserDtoRequest request) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtService.generateToken(userDetails);
        return new JwtResponse(token);
    }
}
