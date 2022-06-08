package pl.jp.quizlet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jp.quizlet.model.Question;

@Repository("QuestionRepository")
public interface QuestionRepository extends JpaRepository<Question, Long> {}