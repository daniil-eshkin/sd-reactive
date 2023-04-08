package sd.lab4.autoincrement;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class IdCounter {
    @Id
    private String id;
    private long counter;
}
