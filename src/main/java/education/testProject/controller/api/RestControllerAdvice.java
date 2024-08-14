package education.testProject.controller.api;


import education.testProject.domain.dto.ExceptionDto;
import education.testProject.domain.exception.ResourceNotFoundException;
import education.testProject.domain.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ExceptionDto handleResourceNotFoundException(ResourceNotFoundException e){
        return new ExceptionDto(e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ExceptionDto handleValidationException(ValidationException e){
        return new ExceptionDto(e);
    }
}
