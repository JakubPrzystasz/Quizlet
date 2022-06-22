package pl.jp.quizlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan( basePackages = {"pl.jp.quizlet"})
@SpringBootApplication
public class QuizletApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizletApplication.class, args);
    }

}
