package pl.jp.quizlet.config;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jp.quizlet.model.*;
import pl.jp.quizlet.repository.*;

import java.util.List;
import java.util.Locale;

@Component
public class QuestionConfig {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    OptionRepository optionRepository;
    @Autowired
    LectureRepository lectureRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SessionRepository sessionRepository;

    void seed() {
        Faker faker = new Faker(new Locale("pl"));
        List<QuestionType> types = List.of(QuestionType.values());

        var user = new User("j0tp3");
        userRepository.save(user);

        for(int l = 0; l < faker.random().nextInt(1,8); l++){
            var lecture = new Lecture(faker.commerce().productName(),faker.gameOfThrones().quote()){{
                setQuestionsToDisplay(Long.valueOf(faker.random().nextInt(2,8)));
            }};
            lectureRepository.save(lecture);
            for(int q = 0; q < faker.random().nextInt(16); q++){
                var question = new Question(faker.book().title(), types.get(faker.random().nextInt(types.size())));
                lecture.addQuestion(question);
                questionRepository.save(question);

                for(int a = 0; a < faker.random().nextInt(2,8);a++){
                    var option = new Option(faker.book().publisher(), faker.bool().bool());
                    question.addOption(option);
                    optionRepository.save(option);
                }
            }
        }
    }
}