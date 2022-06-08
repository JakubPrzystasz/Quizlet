package pl.jp.quizlet.config;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jp.quizlet.model.Answer;
import pl.jp.quizlet.model.Question;
import pl.jp.quizlet.model.QuestionType;
import pl.jp.quizlet.repository.AnswerRepository;
import pl.jp.quizlet.repository.QuestionRepository;

import java.io.IOException;
import java.util.List;

@Component
public class QuestionConfig {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    AnswerRepository answerRepository;

    void seed() {
        Question l1 = new Question("Wykład 1", QuestionType.MULTIPLE_ANSWER);
        Answer ans1 = new Answer("Tak",true);
        Answer ans2 = new Answer("Nie",false);
        questionRepository.save(l1);
        l1.addAnswer(ans1);
        l1.addAnswer(ans2);
        answerRepository.save(ans1);
        answerRepository.save(ans2);

        Question l2 = new Question("Wykład 2",QuestionType.TRUE_FALSE);
        l2.addAnswer(ans1);
        l2.addAnswer(ans2);
        Question l3 = new Question("Wykład 3",QuestionType.TEXT_ANSWER);
        l3.addAnswer(ans1);
        l3.addAnswer(ans2);

        questionRepository.saveAll(List.of(l1, l2, l3));
    }
}