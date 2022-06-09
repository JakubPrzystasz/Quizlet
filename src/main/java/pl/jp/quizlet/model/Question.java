package pl.jp.quizlet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Question")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Question_SEQ")
    @SequenceGenerator(name = "Question_SEQ")
    @Column(name = "id", nullable = false)
    private Long id;

    private String content;

    @Enumerated(EnumType.ORDINAL)
    private QuestionType type;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Answer> answerList;

    public Question(String content,QuestionType type){
        this.content = content;
        this.type = type;
        answerList = new ArrayList<>();
    }

    public void addAnswer(Answer answer){
        answerList.add(answer);
        answer.setQuestion(this);
    }

    public void removeAnswer(Answer answer){
        answerList.remove(answer);
        answer.setQuestion(null);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Lecture lecture;

}
