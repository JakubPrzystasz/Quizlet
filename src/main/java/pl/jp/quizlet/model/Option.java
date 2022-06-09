package pl.jp.quizlet.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private boolean correct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Question question;

    public Option(String content, boolean correct){
        this.content = content;
        this.correct = correct;
    }
}