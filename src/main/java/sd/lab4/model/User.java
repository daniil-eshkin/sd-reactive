package sd.lab4.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class User {
    public static final String COUNTER_ID = "user";

    private long id;
    private String name;
    private RegionalPriceMark pricePreferences;
}
