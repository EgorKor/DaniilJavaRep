package education.testProject.service;

import education.testProject.domain.model.user.User;

import java.util.List;

public interface UserService {
    User getById(Long id);
    List<User> getAllUsers();
    User save(User user);
    User update(User user);
    void delete(Long id);
}
