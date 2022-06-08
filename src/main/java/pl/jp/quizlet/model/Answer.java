package pl.jp.quizlet.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Entity(name = "Answer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Answer_SEQ")
    @SequenceGenerator(name = "Answer_SEQ")
    @Column(name = "id", nullable = false)
    private Long id;

    private String content;

    private boolean correct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Question question;

    public Answer(String content, boolean correct){
        this.content = content;
        this.correct = correct;
    }
}