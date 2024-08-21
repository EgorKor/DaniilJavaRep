package education.testProject.service.impl;

import education.testProject.domain.model.user.UserDetailsImpl;
import education.testProject.repository.UserRepository;
import education.testProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetailsImpl(userService.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Пользователь с таким username = %s не найден".formatted(username))));
    }
}
