package pl.jp.quizlet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jp.quizlet.model.Answer;
import pl.jp.quizlet.model.Question;
import pl.jp.quizlet.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;
@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }

    public Optional<Question> getQuestion(Integer questionId) {
        return questionRepository.findById(Long.valueOf(questionId));
    }

    public Optional<Answer> getCorrectAnswer(Integer questionId) {
        return getQuestion(questionId).get().getAnswerList().stream().filter(ans -> ans.isCorrect()).findAny();
    }
}
