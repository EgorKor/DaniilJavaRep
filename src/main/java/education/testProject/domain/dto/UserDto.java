package education.testProject.domain.dto;

import education.testProject.domain.model.user.User;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

@Data
public class UserDto {
    @NotNull(message = "ID не может быть null")
    @Range(min = 0, max = Long.MAX_VALUE, message = "ID не может быть меньше 0")
    @EqualsAndHashCode.Exclude
    private Long id;

    @NotEmpty(message = "Name не может быть 0 символов")
    @NotBlank(message = "Name не может состоять из одних пробелов")
    @Size(min = 2, max = 12, message = "Размер name должен быть от 2 до 12 символов")
    @Pattern(regexp = "[a-zA-Z]{1,8}[0-9]{1,4}", message = "Name должно соответствовать шаблону - сначала буквы, потом цифры")
    private String username;

    @NotEmpty(message = "Email не может быть пустым")
    @Email(message = "Email не подходит под шаблон электронной почты")
    private String email;

    private String password;

    public UserDto(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
