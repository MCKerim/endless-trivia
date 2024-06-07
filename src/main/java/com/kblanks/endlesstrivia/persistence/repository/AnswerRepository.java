package com.kblanks.endlesstrivia.persistence.repository;

import com.kblanks.endlesstrivia.domain.model.Answer;
import com.kblanks.endlesstrivia.domain.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByQuestion(Question question);
}
