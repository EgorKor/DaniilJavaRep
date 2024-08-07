package education.testProject.model;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

@Getter //Генерирует геттеры для всех полей класса
@Setter //Генерирует сеттеры
@NoArgsConstructor //конструктор без аргументов - по умолчанию
@AllArgsConstructor //конструктор со всех аргументами
@ToString //генерируется ToString()
public class User {

    @NotNull(message = "ID не может быть null")
    @Range(min = 0, max = Integer.MAX_VALUE, message = "ID не может быть меньше 0")
    private Integer id;
    @NotEmpty(message = "Name не может быть 0 символов")
    @NotBlank(message = "Name не может состоять из одних пробелов")
    @Size(min = 2, max = 12, message = "Размер name должен быть от 2 до 12 символов")
    @Pattern(regexp = "[a-zA-Z]{1,8}[0-9]{1,4}", message = "Name должно соответствовать шаблону - сначала буквы, потом цифры")
    private String name;
    @NotEmpty(message = "Email не может быть пустым")
    @Email(message = "Email не подходит под шаблон электронной почты")
    private String email;
    @ToString.Exclude //исключаем isAdmin из toString()
    private Boolean isAdmin;
}
