package education.testProject.domain.dto;

import education.testProject.domain.model.Order;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String name;
    private Integer price;

    public OrderDto(Order order){
        this.id = order.getId();
        this.name = order.getName();
        this.price = order.getPrice();
    }
}
