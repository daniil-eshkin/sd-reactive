package sd.lab4.autoincrement;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CustomIdCounterRepository {
    Mono<IdCounter> incrementCounter(String counterId);
}
