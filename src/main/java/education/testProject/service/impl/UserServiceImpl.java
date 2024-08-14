package education.testProject.service.impl;

import education.testProject.dao.UserDao;
import education.testProject.domain.exception.ResourceNotFoundException;
import education.testProject.domain.model.User;
import education.testProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public User getById(Long id) {
        return userDao.findUserById(id).orElseThrow(() -> new ResourceNotFoundException("User c id = %d не найден".formatted(id)));
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    public User save(User user) {
        return userDao.create(user);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public void delete(Long id) {
        userDao.deleteById(id);
    }
}
