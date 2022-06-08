package pl.jp.quizlet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.jp.quizlet.model.Answer;
import pl.jp.quizlet.model.Question;
import pl.jp.quizlet.service.QuestionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping()
    public List<Question> getAllQuestions(){
        return questionService.getAll();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Question question){
        questionService.saveQuestion(question);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping(
            value = "/{questionId}"
    )
    public @ResponseBody Question getQuestion(@PathVariable Integer questionId) {
        var question = questionService.getQuestion(questionId);
        if(question.isPresent())
            return question.get();
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found record with specified id");
    }

    @GetMapping(
            value = "/{questionId}/correct"
    )
    public @ResponseBody Answer getCorrectAnswer(@PathVariable Integer questionId) {
        var answer = questionService.getCorrectAnswer(questionId);
        if(answer.isPresent())
            return answer.get();
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found record with specified id");
    }
}
