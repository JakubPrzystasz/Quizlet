package pl.jp.quizlet.config;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jp.quizlet.model.Answer;
import pl.jp.quizlet.model.Lecture;
import pl.jp.quizlet.model.Question;
import pl.jp.quizlet.model.QuestionType;
import pl.jp.quizlet.repository.AnswerRepository;
import pl.jp.quizlet.repository.LectureRepository;
import pl.jp.quizlet.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class QuestionConfig {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    LectureRepository lectureRepository;

    void seed() {
        Faker faker = new Faker(new Locale("pl"));
        List<QuestionType> types = List.of(QuestionType.values());

        for(int l = 0; l < faker.random().nextInt(1,8); l++){
            var lecture = new Lecture(faker.commerce().productName(),faker.gameOfThrones().quote());
            lectureRepository.save(lecture);

            for(int q = 0; q < faker.random().nextInt(16); q++){
                var question = new Question(faker.book().title(), types.get(faker.random().nextInt(types.size())));
                lecture.addQuestion(question);
                questionRepository.save(question);

                for(int a = 0; a < faker.random().nextInt(2,8);a++){
                    var answer = new Answer(faker.book().publisher(), faker.bool().bool());
                    question.addAnswer(answer);
                    answerRepository.save(answer);
                }
            }
        }
    }
}