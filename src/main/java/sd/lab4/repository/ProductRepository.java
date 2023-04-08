package sd.lab4.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import sd.lab4.model.Product;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, Long> {
}
