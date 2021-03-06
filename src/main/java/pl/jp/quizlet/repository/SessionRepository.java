package pl.jp.quizlet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jp.quizlet.model.Session;

@Repository("SessionRepository")
public interface SessionRepository extends JpaRepository<Session, Long> {}
