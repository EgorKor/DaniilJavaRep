package education.testProject.domain.dto;

import education.testProject.domain.model.Company;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CompanyDto {
    private Long id;
    private String name;


    public CompanyDto(Company company){
        this.id = company.getId();
        this.name = company.getName();
    }
}
