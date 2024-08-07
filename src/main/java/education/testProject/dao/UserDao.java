package education.testProject.dao;


import education.testProject.model.User;

import java.util.List;
import java.util.Optional;

//DAO - Data Access Object - для доступа к данным
//для работы с БД, для реализации CRUD операций (CREATE, READ, UPDATE, DELETE)
public interface UserDao {

    List<User> findAllUsers();
    Optional<User> findUserById(Integer id);

    Optional<User> findUserByEmail(String email);
    User update(User user);

    User create(User user);

    void deleteById(Integer id);

}
