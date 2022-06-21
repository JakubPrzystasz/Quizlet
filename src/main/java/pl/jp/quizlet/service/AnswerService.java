package pl.jp.quizlet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jp.quizlet.model.Answer;
import pl.jp.quizlet.model.Option;
import pl.jp.quizlet.repository.AnswerRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

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