package education.testProject.domain.util.validator;


import education.testProject.dao.UserDao;
import education.testProject.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {
    private final UserDao dao;


    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(User.class);
    }

    //проверяем на email
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if(dao.findUserByEmail(user.getEmail()).isPresent()){
            errors.rejectValue("email","","Такой email уже занят");
        }
    }
}
