package pl.jp.quizlet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.jp.quizlet.RandomElements;
import pl.jp.quizlet.model.Session;

import pl.jp.quizlet.service.LectureService;
import pl.jp.quizlet.service.SessionService;
import pl.jp.quizlet.service.UserService;


import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
public class SessionController {
    private final SessionService sessionService;
    @Autowired
    private final LectureService lectureService;
    @Autowired
    private final UserService userService;

    @GetMapping()
    public List<Session> getAll(){
        return sessionService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Session save(@RequestParam("lectureId") Integer lectureId,
                     @RequestParam("userId") String userId){
        var lecture = lectureService.getLecture(lectureId);
        var user = userService.getUser(userId);
        if(lecture.isPresent() && user.isPresent()){
            Session session = new Session();
            session.setLecture(lecture.get());
            if(lecture.get().getQuestionsToDisplay() == null) {
                lecture.get().setQuestionsToDisplay(Long.valueOf(lecture.get().getQuestionList().size()));
            }
            if(lecture.get().getQuestionsToDisplay() > 0 && lecture.get().getQuestionsToDisplay() <= lecture.get().getQuestionList().size()){
                Random random = new Random();
                session.setAssignedQuestions(RandomElements.pickNRandomElements(session.getLecture().getQuestionList(),lecture.get().getQuestionsToDisplay().intValue()));
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found record with specified id");
            }
            session.setUser(user.get());
            sessionService.saveSession(session);
            return session;
        }else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found record with specified id");

    }

    @GetMapping(value = "/{sessionId}")
    public Session getSession(@PathVariable Integer sessionId) {
        return sessionService.getSession(sessionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found record with specified id"));
    }

    @GetMapping(value = "/{sessionId}/results")
    public Session getResults(@PathVariable Integer sessionId) {
        var session = sessionService.getSession(sessionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found record with specified id"));
        if(session.getTotalPoints() == null){
            session.setTotalPoints(session.getAssignedQuestions().size());
            final Integer[] result = {0};
            var qa = session.getAnswerList()
                                                    .stream()
                                                    .collect(Collectors.groupingBy( a -> a.getOption().getQuestion()));

            qa.forEach((question, answers) -> {
                answers.get(0).getOption().getIsCorrect();
                switch (question.getType()){
                    case SINGLE_ANSWER:
                        if( answers.stream().filter( o -> o.getOption().getIsCorrect()).toList().size() == 1)
                            result[0] += 1;
                        break;
                    case MULTIPLE_ANSWER:
                    case TRUE_FALSE:
                        if( answers.stream().filter( o -> o.getOption().getIsCorrect()).toList().size() ==
                            answers.stream().
                                    findFirst().get().getOption().getQuestion().
                                    getOptionList().stream().filter( o -> o.getIsCorrect()).toList().size())
                            result[0] += 1;
                }
            });

            session.setResult(result[0]);
            sessionService.saveSession(session);
        }

        return session;
    }
}
