package com.kblanks.endlesstrivia.service;

import com.kblanks.endlesstrivia.domain.model.Answer;
import com.kblanks.endlesstrivia.domain.model.Question;
import com.kblanks.endlesstrivia.persistence.repository.AnswerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionService {
    private final AnswerRepository answerRepository;

    public QuestionService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Transactional(readOnly = true)
    public List<Answer> getAnswers(Question question) {
        return answerRepository.findAllByQuestion(question);
    }
}
