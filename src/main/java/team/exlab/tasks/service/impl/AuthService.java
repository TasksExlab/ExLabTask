package team.exlab.tasks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.exlab.tasks.service.dto.user.JwtResponse;
import team.exlab.tasks.service.dto.user.LoginUserDtoRequest;
import team.exlab.tasks.service.interfaces.IAuthService;
import team.exlab.tasks.service.interfaces.IJwtService;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final UserDetailsService userDetailsService;
    private final IJwtService jwtService;

    @Transactional
    public JwtResponse createAuthToken(LoginUserDtoRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtService.generateToken(userDetails);
        return new JwtResponse(token);
    }
}
