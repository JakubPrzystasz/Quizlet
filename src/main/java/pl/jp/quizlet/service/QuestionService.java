package pl.jp.quizlet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jp.quizlet.model.Option;
import pl.jp.quizlet.model.Question;
import pl.jp.quizlet.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }

    public Optional<Question> getQuestion(Integer questionId) {
        return questionRepository.findById(Long.valueOf(questionId));
    }

}
