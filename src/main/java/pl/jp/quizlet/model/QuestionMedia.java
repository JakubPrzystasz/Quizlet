package pl.jp.quizlet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "QuestionMedia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QuestionMedia_SEQ")
    @SequenceGenerator(name = "QuestionMedia_SEQ")
    @Column(name = "id", nullable = false)
    private Long id;

    private byte[] data;

    @Enumerated(EnumType.ORDINAL)
    private MediaType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Question question;
}
