package team.exlab.tasks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team.exlab.tasks.model.repository.UserRepository;
@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(user -> User.builder()
                        .password(user.getPassword())
                        .username(user.getEmail())
                        .authorities(user.getRole().getRole())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден email = " + username));
    }
}
