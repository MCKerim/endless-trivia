package com.kblanks.endlesstrivia.persistence.repository;

import com.kblanks.endlesstrivia.domain.model.Question;
import com.kblanks.endlesstrivia.domain.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByQuiz(Quiz quiz);
}
