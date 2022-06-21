package pl.jp.quizlet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jp.quizlet.model.Option;
import pl.jp.quizlet.model.Session;
import pl.jp.quizlet.repository.SessionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;

    public List<Session> getAll() {
        return sessionRepository.findAll();
    }

    public void saveSession(Session session) {
        sessionRepository.save(session);
    }

    public Optional<Session> getSession(Integer sessionId) {
        return sessionRepository.findById(Long.valueOf(sessionId));
    }

}