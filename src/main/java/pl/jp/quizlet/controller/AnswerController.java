package pl.jp.quizlet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.jp.quizlet.model.Answer;
import pl.jp.quizlet.service.AnswerService;
import pl.jp.quizlet.service.OptionService;
import pl.jp.quizlet.service.SessionService;

import java.util.List;

@RestController
@RequestMapping("/answers")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;
    @Autowired
    private final OptionService optionService;
    @Autowired
    private final SessionService sessionService;

    @GetMapping()
    public List<Answer> getAllAnswers(){
        return answerService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Answer save(@RequestParam("sessionId") Integer sessionId,
                     @RequestParam("optionId") Integer optionId){
        var session = sessionService.getSession(sessionId);
        var option = optionService.getOption(optionId);

        if(session.isPresent() && option.isPresent()){
            var answer = new Answer();
            answer.setOption(option.get());
            answer.setSession(session.get());
            answerService.saveAnswer(answer);
            return answer;
        }else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found record with specified id");
    }

    @GetMapping(value = "/{answerId}")
    public Answer getQuestion(@PathVariable Integer answerId) {
        return answerService.getAnswer(answerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found record with specified id"));
    }
}
