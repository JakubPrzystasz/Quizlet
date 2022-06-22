package pl.jp.quizlet.model;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

@Entity(name = "Option")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Option_SEQ")
    @SequenceGenerator(name = "Option_SEQ")
    @Column(name = "id", nullable = false)
    private Long id;

    private String content;

    private boolean correct = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Question question;

    public Option(String content, boolean correct) {
        this.content = content;
        this.correct = correct;
    }

    @JsonIgnore
    public boolean getIsCorrect() {
        return correct;
    }
}