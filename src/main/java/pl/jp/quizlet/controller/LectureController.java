package pl.jp.quizlet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.jp.quizlet.model.Answer;
import pl.jp.quizlet.model.Lecture;
import pl.jp.quizlet.service.LectureService;

import java.util.List;

@RestController
@RequestMapping("/lectures")
public class LectureController {

    @Autowired
    LectureService lectureService;

    @GetMapping()
    public List<Lecture> getAll(){ return  lectureService.getAll(); }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Lecture lecture){
        lectureService.saveLecture(lecture);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping(
            value = "/{lectureId}"
    )
    public @ResponseBody Lecture getLecture(@PathVariable Integer lectureId) {
        var ans = lectureService.getLecture(lectureId);
        if(ans.isPresent())
            return ans.get();
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found record with specified id");
    }



}
