package pl.jp.quizlet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jp.quizlet.model.Answer;
import pl.jp.quizlet.repository.AnswerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public List<Answer> getAll() {
        return answerRepository.findAll();
    }

    public void saveAnswer(Answer answer) {
        answerRepository.save(answer);
    }

    public Optional<Answer> getAnswer(Integer answerId) {
        return answerRepository.findById(Long.valueOf(answerId));
    }

}