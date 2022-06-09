package pl.jp.quizlet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jp.quizlet.model.Option;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<Option, Long> {}