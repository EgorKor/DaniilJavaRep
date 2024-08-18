package education.testProject.domain.model.user;

import education.testProject.domain.dto.UserRegistrationDto;
import education.testProject.domain.model.Company;
import education.testProject.domain.model.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.util.List;

@Getter //Генерирует геттеры для всех полей класса
@Setter //Генерирует сеттеры
@NoArgsConstructor //конструктор без аргументов - по умолчанию
@AllArgsConstructor //конструктор со всех аргументами
@ToString //генерируется ToString()
@EqualsAndHashCode
@Entity//мы говорим Hibernate - что нужно проанализировать этот класс
@Table(name = "users")
public class User {

    @NotNull(message = "ID не может быть null")
    @Range(min = 0, max = Long.MAX_VALUE, message = "ID не может быть меньше 0")

    @EqualsAndHashCode.Exclude

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //генерируется на стороне БД - нет ошибок при вставки user без id
    private Long id;

    @NotEmpty(message = "Name не может быть 0 символов")
    @NotBlank(message = "Name не может состоять из одних пробелов")
    @Size(min = 2, max = 12, message = "Размер name должен быть от 2 до 12 символов")
    @Pattern(regexp = "[a-zA-Z]{1,8}[0-9]{1,4}", message = "Name должно соответствовать шаблону - сначала буквы, потом цифры")

    @Column(name = "username")
    private String username;

    @NotEmpty(message = "Email не может быть пустым")
    @Email(message = "Email не подходит под шаблон электронной почты")

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany
    @JoinColumn(name = "user_id")//указываем что выбирать заказы
    //нужно на основании поля user_id
    private List<Order> orders;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)//указываем что нужно явно сохранять роли как строки
    private Role role;

    @ManyToMany
    @JoinTable(name = "users_companies",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id")
    )
    private List<Company> companies;

    public User(UserRegistrationDto dto){
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.username = dto.getUsername();
    }


}
