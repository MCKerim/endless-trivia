package com.kblanks.endlesstrivia.persistence.repository;

import com.kblanks.endlesstrivia.domain.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
