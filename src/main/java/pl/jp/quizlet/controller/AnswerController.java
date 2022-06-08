package pl.jp.quizlet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.jp.quizlet.model.Answer;
import pl.jp.quizlet.model.Question;
import pl.jp.quizlet.service.AnswerService;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping()
    public List<Answer> getAllAnswers(){
        return answerService.getAll();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Answer answer){
        answerService.saveAnswer(answer);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
    @GetMapping(
            value = "/{answerId}"
    )
    public @ResponseBody Answer getAnswer(@PathVariable Integer answerId) {
        var ans = answerService.getAnswer(answerId);
        if(ans.isPresent())
            return ans.get();
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found record with specified id");
    }

}