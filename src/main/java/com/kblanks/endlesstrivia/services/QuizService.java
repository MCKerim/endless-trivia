package com.kblanks.endlesstrivia.services;

import com.kblanks.endlesstrivia.data.Quiz;
import com.kblanks.endlesstrivia.data.QuizRepository;
import com.kblanks.endlesstrivia.data.User;
import com.kblanks.endlesstrivia.data.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;

    public QuizService(QuizRepository quizRepository, UserRepository userRepository) {
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
    }

    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    public Quiz get(Long id) {
        return Objects.requireNonNull(quizRepository.findById(id).orElse(null));
    }

    public long count() {
        return quizRepository.count();
    }

    public void delete(Quiz quiz) {
        quizRepository.delete(quiz);
    }

    public void save(Quiz quiz) {
        quizRepository.save(quiz);
    }

    public void update(Quiz quiz) {
        quizRepository.save(quiz);
    }

    @PostConstruct
    public void populateTestData() {
        if (quizRepository.count() == 0) {
            List<User> users = userRepository.findAll();
            quizRepository.saveAll(
                    Stream.of("General Knowledge", "Mathematics", "Sports", "History", "Geography", "Science")
                            .map(name -> {
                                Quiz quiz = new Quiz();
                                quiz.setName(name);
                                quiz.setOwner(users.getFirst());
                                return quiz;
                            }).collect(Collectors.toList()));
        }
    }
}

