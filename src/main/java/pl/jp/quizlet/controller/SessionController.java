package pl.jp.quizlet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.jp.quizlet.model.Question;
import pl.jp.quizlet.model.Session;
import pl.jp.quizlet.service.QuestionService;
import pl.jp.quizlet.service.SessionService;

import java.util.List;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @GetMapping()
    public List<Session> getAll(){
        return sessionService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Session session){
        sessionService.saveSession(session);
    }

    @GetMapping(value = "/{sessionId}")
    public Session getQuestion(@PathVariable Integer sessionId) {
        return sessionService.getSession(sessionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found record with specified id"));
    }

}
