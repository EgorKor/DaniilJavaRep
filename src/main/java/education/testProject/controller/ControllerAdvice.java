package education.testProject.controller;

import education.testProject.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler
    public String handleResourceNotFoundException(ResourceNotFoundException e){
        return "errors/ResourceNotFound";
    }


}
