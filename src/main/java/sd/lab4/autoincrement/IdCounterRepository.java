package sd.lab4.autoincrement;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdCounterRepository extends ReactiveMongoRepository<IdCounter, Long>, CustomIdCounterRepository {
}
