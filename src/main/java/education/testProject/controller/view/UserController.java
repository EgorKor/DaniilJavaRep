package education.testProject.controller.view;

import education.testProject.dao.UserDao;
import education.testProject.domain.exception.ResourceNotFoundException;
import education.testProject.domain.model.User;
import education.testProject.domain.util.validator.UserValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
//Controller Bean - это обьект - задача которого - обрабатывать запросы
@RequestMapping("/users")
//RequestMapping - указание префикса обрабатываемых запросов
//то есть - любые запросы которые начинаются с префикса
// /user - будут направлены в этот контроллер
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;
    private final UserValidator userValidator;


    //указывается конкретный адрес обрабатываемого запроса
    //в данном случае будет обрабатываться запрос метода GET
    // по адресу /users
    @GetMapping
    public String getUsersPage(Model model){
        model.addAttribute("userList",userDao.findAllUsers());
        return "users/AllUsers";//путь к нужному представлению/шаблону/view - html странице

        //ищет начиная от папки templates
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model){
        User user = userDao.findUserById(id).orElseThrow(() ->{ throw new ResourceNotFoundException("User с таким id не найден");});
        model.addAttribute("user", user);
        return "users/UserData";
    }

    @GetMapping("/{id}/update")
    public String getUpdatePage(@PathVariable("id") Long id, Model model){
        User user = userDao.findUserById(id).orElseThrow(() ->{ throw new ResourceNotFoundException("User с таким id не найден");});
        model.addAttribute("user", user);
        return "users/Update";
    }

    //В GetMapping и других маппингах (PostMapping и т.д.)
    //мы указываем часть адреса идущую после префикса
    // из префикса и части в маппинге
    // /users + /admin
    // итоговый адрес который обрабатывает данный метод
    // /users/admin
    @GetMapping("/admin")
    public String admin(){
        return "users/Admin";
    }

    @GetMapping("/userData")
    public String dataExample( Model model){
        User user = new User();
        user.setId(10L);
        user.setUsername("Egor");
        model.addAttribute("user",user);
        System.out.println(model.containsAttribute("user"));
        return "users/UserData";
    }

    @GetMapping("/create")
    public String getCreationPage(Model model){
        User user = new User();
        user.setIsAdmin(false);
        model.addAttribute("user",user);
        return "users/UserCreate";
    }


    //Используя аннотацию RequestBody мы даем понять что обьект
    //мы должны получить из тела запроса
    @PostMapping("/create")
    public String createUser(@Valid @ModelAttribute("user") User user, BindingResult errors, Model model){
        userValidator.validate(user, errors);
        if(errors.hasErrors()){
            return "users/UserCreate";
        }
        userDao.create(user);
        return "users/AllUsers";
    }


    
}
