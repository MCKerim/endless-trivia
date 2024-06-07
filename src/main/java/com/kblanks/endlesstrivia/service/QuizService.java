package com.kblanks.endlesstrivia.service;

import com.kblanks.endlesstrivia.domain.model.Question;
import com.kblanks.endlesstrivia.domain.model.Quiz;
import com.kblanks.endlesstrivia.persistence.repository.QuestionRepository;
import com.kblanks.endlesstrivia.persistence.repository.QuizRepository;
import com.kblanks.endlesstrivia.domain.model.User;
import com.kblanks.endlesstrivia.persistence.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public QuizService(QuizRepository quizRepository, UserRepository userRepository, QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Quiz> get(Long id) {
        return quizRepository.findById(id);
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

    @Transactional(readOnly = true)
    public List<Question> getQuestions(Quiz quiz) {
        return questionRepository.findAllByQuiz(quiz);
    }

    @PostConstruct
    public void populateTestData() {
        if (quizRepository.count() == 0) {
            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                return;
            }
            quizRepository.saveAll(
                    Stream.of("General Knowledge", "Mathematics", "Sports", "History", "Geography", "Science")
                            .map(name -> {
                                Quiz quiz = new Quiz();
                                quiz.setTitle(name);
                                quiz.setDescription("Description");
                                quiz.setImage("image.png");
                                quiz.setUser(users.getFirst());
                                return quiz;
                            }).collect(Collectors.toList()));
        }
    }
}

