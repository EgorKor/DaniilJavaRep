package education.testProject.domain.dto;

import education.testProject.domain.exception.ResourceNotFoundException;
import education.testProject.domain.exception.ValidationException;
import lombok.Data;

import java.util.List;

@Data
public class ExceptionDto {
    private List<String> errors;

    public ExceptionDto(ResourceNotFoundException e){
        errors = List.of(e.getMessage());
    }

    public ExceptionDto(ValidationException e){
        errors = e.getErrors();
    }
}
