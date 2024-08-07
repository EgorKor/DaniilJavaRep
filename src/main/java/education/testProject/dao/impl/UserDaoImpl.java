package education.testProject.dao.impl;

import education.testProject.dao.UserDao;
import education.testProject.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.swing.text.StyledEditorKit;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Component
@PropertySources(value = {@PropertySource("classpath:application.properties"),@PropertySource("classpath:config/example.properties")})
public class UserDaoImpl implements UserDao {
    private HashMap<Integer, User> data;
    private Integer index;
    private final Environment environment;

    @Autowired
    public UserDaoImpl(Environment environment){
        this.environment = environment;
        data = new HashMap<>();
        index = 0;
        Integer id1 = Integer.valueOf(environment.getProperty("data.user1.id"));
        String name1 = environment.getProperty("data.user1.name");
        String email1 = environment.getProperty("data.user1.email");
        Boolean isAdmin1 = Boolean.valueOf(environment.getProperty("data.user1.isAdmin"));
        User user = new User();
        user.setId(id1);
        user.setName(name1);
        user.setEmail(email1);
        user.setIsAdmin(isAdmin1);
        create(user);
        System.out.println(environment.getProperty("message"));
    }

    @PostConstruct
    private void showInitUsers(){
        data.values().forEach(o -> System.out.println(o));
    }


    private void moveIndex(){
        index++;
    }

    @Override
    public List<User> findAllUsers() {
        return data.values().stream().toList();
    }

    @Override
    public Optional<User> findUserById(Integer id) {
        if(data.containsKey(id)){
            return Optional.of(data.get(id));
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return data.values().stream().filter(o -> o.getEmail().equals(email)).findFirst();
    }

    @Override
    public User update(User user) {
        data.put(user.getId(), user);
        return user;
    }

    @Override
    public User create(User user) {
        data.put(index, user);
        moveIndex();
        return user;
    }

    @Override
    public void deleteById(Integer id) {
        data.remove(id);
    }
}
