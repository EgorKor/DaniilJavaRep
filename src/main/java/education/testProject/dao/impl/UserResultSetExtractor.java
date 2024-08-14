package education.testProject.dao.impl;

import education.testProject.domain.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserResultSetExtractor implements ResultSetExtractor<List<User>> {
    @Override
    public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<User> users = new ArrayList<>();
        while(rs.next()){//как в Iterable
            User user = new User();
            user.setIsAdmin(false);
            user.setEmail(rs.getString("email"));
            user.setUsername(rs.getString("username"));
            user.setId(rs.getLong("id"));
            user.setPassword(rs.getString("password"));
            users.add(user);
        }
        return users;
    }
}
