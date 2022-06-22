package pl.jp.quizlet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Session_SEQ")
    @SequenceGenerator(name = "Session_SEQ")
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime timestamp;

    @ManyToMany
    @JoinTable(
            name = "session_questions",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private List<Question> assignedQuestions;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Answer> answerList;

    @OneToOne
    @JsonIgnore
    private Lecture lecture;
}
