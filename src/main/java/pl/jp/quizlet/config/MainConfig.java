package pl.jp.quizlet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfig {
    @Autowired
    QuestionConfig questionConfig;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            questionConfig.seed();
        };
    }
}