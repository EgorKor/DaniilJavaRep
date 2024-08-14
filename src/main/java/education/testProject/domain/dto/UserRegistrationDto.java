package education.testProject.domain.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class UserRegistrationDto {
    @NotEmpty(message = "Name не может быть 0 символов")
    @NotBlank(message = "Name не может состоять из одних пробелов")
    @Size(min = 2, max = 12, message = "Размер name должен быть от 2 до 12 символов")
    @Pattern(regexp = "[a-zA-Z]{1,8}[0-9]{1,4}", message = "Name должно соответствовать шаблону - сначала буквы, потом цифры")
    private String username;
    @NotEmpty(message = "Email не может быть пустым")
    @Email(message = "Email не подходит под шаблон электронной почты")
    private String email;

    private String password;
    @ToString.Exclude //исключаем isAdmin из toString()
    private Boolean isAdmin;
}
