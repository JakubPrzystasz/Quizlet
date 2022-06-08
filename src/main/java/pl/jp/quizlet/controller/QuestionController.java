package pl.jp.quizlet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.jp.quizlet.model.Question;
import pl.jp.quizlet.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public List<Question> getAllQuestions(){
        return questionService.getAll();
    }

    @PostMapping
    public void save(@RequestBody Question question){
        questionService.saveQuestion(question);
    }
}
