package com.kblanks.endlesstrivia.views.playquiz;

import com.kblanks.endlesstrivia.domain.model.Answer;
import com.kblanks.endlesstrivia.domain.model.Question;
import com.kblanks.endlesstrivia.domain.model.Quiz;
import com.kblanks.endlesstrivia.service.QuestionService;
import com.kblanks.endlesstrivia.service.QuizService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import jakarta.annotation.security.PermitAll;

import java.util.List;
import java.util.Optional;

@PageTitle("Play Quiz")
@Menu(icon = "line-awesome/svg/play-solid.svg", order = 8)
@Route(value = "playquiz")
@PermitAll
public class PlayQuizView extends VerticalLayout implements BeforeEnterObserver, HasUrlParameter<String> {

    private final QuizService quizService;
    private final QuestionService questionService;

    H3 quizTitle = new H3();
    H1 questionText = new H1();
    VerticalLayout verticalLayout = new VerticalLayout();
    VerticalLayout answersLayout = new VerticalLayout();

    H1 evaluationText = new H1();
    Button nextButton = new Button("Next");

    Optional<Quiz> currentQuiz = Optional.empty();

    public PlayQuizView(QuizService quizService, QuestionService questionService) {
        this.quizService = quizService;
        this.questionService = questionService;

        quizTitle.setText("Harry potter");
        add(quizTitle);

        questionText.setText("Loading question...");

        verticalLayout.add(questionText);
        verticalLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        add(verticalLayout);

        answersLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        add(answersLayout);

        evaluationText.setVisible(false);
        add(evaluationText);
        nextButton.addClickListener(event -> {
            currentQuiz.ifPresent(this::loadQuestion);
        });
        nextButton.setVisible(false);
        add(nextButton);
    }

    private void answerClicked(boolean isCorrect) {
        evaluationText.setText(isCorrect ? "Correct!" : "Incorrect!");
        evaluationText.setVisible(true);
        nextButton.setVisible(true);
    }

    private void loadQuiz(Long quizId) {
        currentQuiz = quizService.get(quizId);
        if (currentQuiz.isEmpty()) {
            quizTitle.setText("Quiz not found");
            questionText.setText("Quiz not found");
        } else {
            quizTitle.setText(currentQuiz.get().getTitle());
            loadQuestion(currentQuiz.get());
        }
    }

    private void loadQuestion(Quiz quiz) {
        evaluationText.setVisible(false);
        nextButton.setVisible(false);
        List<Question> questions = quizService.getQuestions(quiz);

        if (questions.isEmpty()) {
            questionText.setText("No questions found");
        } else {
            // Select a random question
            int questionIndex = (int) (Math.random() * questions.size());
            Question question = questions.get(questionIndex);
            questionText.setText(question.getId() + ": " + question.getText());

            // Load the answers
            answersLayout.removeAll();

            List<Answer> answers = questionService.getAnswers(question);
            answers.forEach(answer -> {
                Button answerButton = new Button(answer.getText());
                answerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
                answerButton.addClickListener(event -> answerClicked(answer.isCorrect()));
                answersLayout.add(answerButton);
            });
        }
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // You can add additional logic before entering the view, if needed
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {
        loadQuiz(Long.parseLong(s));
    }
}
