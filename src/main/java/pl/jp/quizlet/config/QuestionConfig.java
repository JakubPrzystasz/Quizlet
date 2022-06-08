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
@NoArgsConstructor
@AllArgsConstructor
public class QuestionConfig {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    AnswerRepository answerRepository;

    void seed() throws IOException {
        Question l1 = new Question("Wykład 1", QuestionType.MULTIPLE_ANSWER);
        Answer ans = new Answer("Tak",true);
        questionRepository.save(l1);
        l1.addAnswer(ans);
        answerRepository.save(ans);
//        l1.addAnswer(new Answer("Nie",false));

//        Question l2 = new Question(null,"Wykład 2",QuestionType.TRUE_FALSE,null);
//
//        Question l3 = new Question(null,"Wykład 3",QuestionType.TEXT_ANSWER,null);
//
//        questionRepository.saveAll(List.of(l1, l2, l3));
    }
}