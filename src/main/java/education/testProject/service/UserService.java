package education.testProject.service;

import education.testProject.domain.model.user.User;

import java.util.List;

public interface UserService {
    User getById(Long id);
    User getByEmail(String email);

    List<User> getAllUsers();
    User save(User user);
    User update(User user);
    void delete(Long id);
}
