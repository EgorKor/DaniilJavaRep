package education.testProject.service.impl;

import education.testProject.domain.exception.ResourceNotFoundException;
import education.testProject.domain.model.user.User;
import education.testProject.repository.UserRepository;
import education.testProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User c id = %d не найден".formatted(id)));
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User с таким email = %s не найден".formatted(email)));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
