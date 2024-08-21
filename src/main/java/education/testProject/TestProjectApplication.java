package education.testProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TestProjectApplication {



	public static void main(String[] args) {
		SpringApplication.run(TestProjectApplication.class, args);
	}

}
