package education.testProject.dao.impl;

import education.testProject.dao.UserDao;
import education.testProject.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserJDBCDao implements UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final UserResultSetExtractor userResultSetExtractor;

    private String SQL_INSERT = """
            INSERT INTO testScheme.users (username, password, email)
            VALUES (?,?,?) RETURNING id;
            """;

    private String SQL_SELECT_ALL_USERS = """
            SELECT * FROM "testScheme.users";
            """;

    private String SQL_SELECT_BY_ID = """
            SELECT * FROM users WHERE id = ?;
            """;

    private String SQL_SELECT_BY_EMAIL = """
            SELECT * FROM users WHERE email = ?;
            """;

    private String SQL_UPDATE = """
            UPDATE users SET username = ?, email = ? WHERE id = ?;
            """;


    private String SQL_DELETE = """
            DELETE FROM users WHERE id = ?;
            """;

    @Override
    public List<User> findAllUsers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_USERS, userResultSetExtractor);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return jdbcTemplate.query(SQL_SELECT_BY_ID, userResultSetExtractor, id).stream().findFirst();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return jdbcTemplate.query(SQL_SELECT_BY_EMAIL, userResultSetExtractor, email).stream().findFirst();
    }

    @Override
    public User update(User user) {
        jdbcTemplate.update(SQL_UPDATE, user.getUsername(), user.getEmail(), user.getId());
        return user;
    }

    @Override
    public User create(User user){
        Long id =
                jdbcTemplate.query(SQL_INSERT, new ResultSetExtractor<Long>() {

                            @Override
                            public Long extractData(ResultSet rs) throws SQLException, DataAccessException {
                                rs.next();
                                return rs.getLong("id");
                            }
                        },
                        user.getUsername(),
                        user.getPassword(),
                        user.getEmail());
        user.setId(id);
        return user;
    }

    @Override
    public void deleteById(Integer id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }
}
