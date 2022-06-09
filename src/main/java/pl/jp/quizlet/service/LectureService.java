package pl.jp.quizlet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jp.quizlet.model.Lecture;
import pl.jp.quizlet.repository.LectureRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LectureService {

    private  final LectureRepository lectureRepository;
    public List<Lecture> getAll() {
        return  lectureRepository.findAll();
    }

    public Optional<Lecture> getLecture(Integer lectureId) {
        return lectureRepository.findById(Long.valueOf(lectureId));
    }

    public void saveLecture(Lecture lecture) {
        lectureRepository.save(lecture);
    }
}
