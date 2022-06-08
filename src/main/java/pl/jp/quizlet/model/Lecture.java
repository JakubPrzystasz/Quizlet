package pl.jp.quizlet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Question> questionList;

}
