package pl.jp.quizlet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.jp.quizlet.model.Option;
import pl.jp.quizlet.service.OptionService;

import java.util.List;

@RestController
@RequestMapping("/options")
@RequiredArgsConstructor
public class OptionController {

    private final OptionService optionService;

    @GetMapping()
    public List<Option> getAllAnswers(){
        return optionService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Option option){
        optionService.saveAnswer(option);
    }

    @GetMapping(value = "/{optionId}")
    public Option getOption(@PathVariable Integer optionId) {
        return optionService.getOption(optionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found record with specified id"));
    }

}