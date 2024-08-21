package education.testProject.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
@Controller
public class AuthController {


    @GetMapping("/login")
    public String getLoginPage(){
        return "auth/LoginPage";
    }
}
