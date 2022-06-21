package pl.jp.quizlet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.jp.quizlet.model.Answer;
import pl.jp.quizlet.model.Question;
import pl.jp.quizlet.repository.AnswerRepository;
import pl.jp.quizlet.service.AnswerService;

import java.util.List;

@RestController
@RequestMapping("/answers")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @GetMapping()
    public List<Answer> getAllAnswers(){
        return answerService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Answer answer){
        answerService.saveAnswer(answer);
    }

    @GetMapping(value = "/{answerId}")
    public Answer getQuestion(@PathVariable Integer answerId) {
        return answerService.getAnswer(answerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found record with specified id"));
    }
}
