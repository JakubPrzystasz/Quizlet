package pl.jp.quizlet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jp.quizlet.model.User;

import java.util.Optional;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
