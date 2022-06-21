package pl.jp.quizlet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jp.quizlet.model.Lecture;

@Repository("LectureRepository")
public interface LectureRepository  extends JpaRepository<Lecture, Long> {}
