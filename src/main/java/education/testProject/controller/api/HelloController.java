package education.testProject.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {


    @GetMapping("/hello")
    public String hello(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return "Hello";
    }

}
