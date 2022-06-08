package pl.jp.quizlet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jp.quizlet.model.Question;

public interface QuestionRepository extends JpaRepository<Question,Long> {

}
