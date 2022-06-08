package pl.jp.quizlet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jp.quizlet.model.Answer;
import pl.jp.quizlet.model.Question;

public interface AnswerRepository extends JpaRepository<Answer, Long> {};