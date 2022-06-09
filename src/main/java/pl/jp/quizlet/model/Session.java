package pl.jp.quizlet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Session")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Session_SEQ")
    @SequenceGenerator(name = "Session_SEQ")
    @Column(name = "id", nullable = false)
    private Long id;

    private String Name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime sessionTimestamp;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Answer> answerList;

    @OneToOne
    private Lecture lecture;
}
