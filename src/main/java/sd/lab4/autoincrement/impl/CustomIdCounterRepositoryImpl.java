package sd.lab4.autoincrement.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Mono;
import sd.lab4.autoincrement.IdCounter;
import sd.lab4.autoincrement.CustomIdCounterRepository;

@RequiredArgsConstructor
public class CustomIdCounterRepositoryImpl implements CustomIdCounterRepository {
    private final ReactiveMongoTemplate template;

    @Override
    public Mono<IdCounter> incrementCounter(String counterId) {
        return template.findAndModify(
                new Query(Criteria.where("id").is(counterId)),
                new Update().inc("counter", 1),
                FindAndModifyOptions.options().returnNew(true).upsert(true),
                IdCounter.class);
    }
}
