package education.testProject.controller.view;

import education.testProject.domain.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler
    public String handleResourceNotFoundException(ResourceNotFoundException e){
        return "errors/ResourceNotFound";
    }


}
