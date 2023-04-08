package sd.lab4.autoincrement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import sd.lab4.model.Product;
import sd.lab4.model.User;

@Service
@RequiredArgsConstructor
public class IdCounterService {
    private final IdCounterRepository idCounterRepository;

    public Mono<Long> generateId(String counterId) {
        return idCounterRepository.incrementCounter(counterId)
                .map(IdCounter::getCounter);
    }

    public Mono<User> setUserId(User user) {
        return generateId(User.COUNTER_ID)
                .map(id -> {
                   user.setId(id);
                   return user;
                });
    }

    public Mono<Product> setProductId(Product product) {
        return generateId(Product.COUNTER_ID)
                .map(id -> {
                    product.setId(id);
                    return product;
                });
    }
}
