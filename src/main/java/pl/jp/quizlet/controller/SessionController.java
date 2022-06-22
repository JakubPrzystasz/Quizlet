package pl.jp.quizlet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.jp.quizlet.RandomElements;
import pl.jp.quizlet.model.Question;
import pl.jp.quizlet.model.Session;
import pl.jp.quizlet.repository.LectureRepository;
import pl.jp.quizlet.service.LectureService;
import pl.jp.quizlet.service.QuestionService;
import pl.jp.quizlet.service.SessionService;
import pl.jp.quizlet.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

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
            session.setTimestamp(LocalDateTime.now());
            sessionService.saveSession(session);
            return session;
        }else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found record with specified id");

    }

    @GetMapping(value = "/{sessionId}")
    public Session getQuestion(@PathVariable Integer sessionId) {
        return sessionService.getSession(sessionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found record with specified id"));
    }

}
