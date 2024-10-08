package education.testProject.controller.api;

import education.testProject.domain.dto.CompanyDto;
import education.testProject.domain.dto.OrderDto;
import education.testProject.domain.dto.UserDto;
import education.testProject.domain.dto.UserRegistrationDto;
import education.testProject.domain.exception.ValidationException;
import education.testProject.domain.model.user.User;
import education.testProject.domain.util.validator.UserValidator;
import education.testProject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
//UserController - предоставляет CRUD для Users
public class RestUserController {
    private final UserService userService;
    private final UserValidator userValidator;

    @GetMapping
    //@ResponseBody - используется для конвертации результата работы метода
    //в JSON файл
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers().stream().map((o) -> new UserDto(o)).toList();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") Long id){
        return new UserDto(userService.getById(id));
    }

    @GetMapping("/{id}/orders")
    public List<OrderDto> getOrdersByUser(@PathVariable("id") Long id){
        return userService.getById(id).getOrders().stream().map((o) -> new OrderDto(o)).toList();
    }


    @GetMapping("/{id}/companies")
    public List<CompanyDto> getCompaniesByUser(@PathVariable("id") Long id){
        return userService.getById(id).getCompanies().stream().map((o) -> new CompanyDto(o)).toList();
    }
    @PostMapping
    public User saveUser(@Valid @RequestBody UserRegistrationDto dto,
                         BindingResult bindingResult){
        User user = new User(dto);
        userValidator.validate(user, bindingResult);
        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getFieldErrors());
        }
        return userService.save(user);
    }

    @PatchMapping("/{id}")
    public User updateUser(@Valid @RequestBody UserRegistrationDto dto,
                           @PathVariable("id") Long id,
                           BindingResult bindingResult){
        userService.getById(id);
        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getFieldErrors());
        }
        User user = new User(dto);
        return userService.update(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.getById(id);//получаем пользователя - если такого пользователя нет
        //то выбрасываем ошибку
        userService.delete(id);
    }

}
