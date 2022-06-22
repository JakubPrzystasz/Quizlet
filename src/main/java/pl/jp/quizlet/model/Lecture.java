package pl.jp.quizlet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Lecture")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Lecture_SEQ")
    @SequenceGenerator(name = "Lecture_SEQ")
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;

    private String description;

    //How many random questions to display
    private Long questionsToDisplay;

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Question> questionList;

    public Lecture(String title, String description){
        this.title = title;
        this.description = description;
        questionList = new ArrayList<>();
    }

    public void addQuestion(Question question){
        questionList.add(question);
        question.setLecture(this);
    }

    public void removeQuestion(Question question){
        questionList.remove(question);
        question.setLecture(this);
    }

}
