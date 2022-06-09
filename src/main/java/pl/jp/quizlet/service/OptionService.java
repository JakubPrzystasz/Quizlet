package pl.jp.quizlet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jp.quizlet.model.Option;
import pl.jp.quizlet.repository.OptionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OptionService {

    private final OptionRepository optionRepository;

    public List<Option> getAll() {
        return optionRepository.findAll();
    }

    public void saveAnswer(Option option) {
        optionRepository.save(option);
    }

    public Optional<Option> getOption(Integer optionId) {
        return optionRepository.findById(Long.valueOf(optionId));
    }

}