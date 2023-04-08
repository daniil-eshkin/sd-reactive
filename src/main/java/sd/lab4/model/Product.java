package sd.lab4.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import sd.lab4.util.PriceUtils;

@Document
@Data
public class Product {
    public static final String COUNTER_ID = "product";

    @Id
    private long id;
    private String name;
    private String description;
    private double usdPrice;

    public ProductDto toDto(RegionalPriceMark mark) {
        return new ProductDto(name, description, mark, PriceUtils.convertUsdPrice(mark, usdPrice));
    }
}
