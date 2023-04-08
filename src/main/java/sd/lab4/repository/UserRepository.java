package sd.lab4.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import sd.lab4.model.User;

public interface UserRepository extends ReactiveMongoRepository<User, Long> {
}
