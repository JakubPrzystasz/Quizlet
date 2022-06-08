package pl.jp.quizlet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jp.quizlet.model.Lecture;

public interface LectureRepository  extends JpaRepository<Lecture, Long> {}
