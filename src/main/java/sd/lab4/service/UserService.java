package sd.lab4.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import sd.lab4.autoincrement.IdCounterService;
import sd.lab4.model.User;
import sd.lab4.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IdCounterService idCounterService;
    private final UserRepository userRepository;

    public Mono<User> getUser(long id) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("No such user")));
    }

    public Mono<User> registerUser(User user) {
        return idCounterService.setUserId(user)
                .flatMap(userRepository::save);
    }
}
