package sd.lab4.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto {
    private String name;
    private String description;
    private RegionalPriceMark mark;
    private double price;
}
