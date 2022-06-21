package pl.jp.quizlet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jp.quizlet.model.Answer;

@Repository("AnswerRepository")
public interface AnswerRepository extends JpaRepository<Answer, Long> {}
