package pl.jp.quizlet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jp.quizlet.model.Option;
import pl.jp.quizlet.model.User;
import pl.jp.quizlet.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public Optional<User> getUser(String login) {
        return userRepository.findByLogin(login);
    }

    public void createUser(String login) {
        if(!getUser(login).isPresent())
            userRepository.save(new User(login));
        else
            throw new RuntimeException("User exists!");
    }
}