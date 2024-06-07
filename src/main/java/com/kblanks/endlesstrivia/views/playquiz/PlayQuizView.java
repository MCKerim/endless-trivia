package com.kblanks.endlesstrivia.views.playquiz;

import com.kblanks.endlesstrivia.domain.model.Quiz;
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

import java.util.Optional;

@PageTitle("Play Quiz")
@Menu(icon = "line-awesome/svg/play-solid.svg", order = 8)
@Route(value = "playquiz")
@PermitAll
public class PlayQuizView extends VerticalLayout implements BeforeEnterObserver, HasUrlParameter<String> {

    private QuizService quizService;
    H3 quizTitle = new H3();

    public PlayQuizView(QuizService quizService) {
        this.quizService = quizService;


        quizTitle.setText("Harry potter");

        add(quizTitle);

        H1 questionText = new H1();
        questionText.setText("What was the name of the first wizard?");
        VerticalLayout verticalLayout = new VerticalLayout(questionText);
        verticalLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

        Button answer1 = new Button("Harry");
        Button answer2 = new Button("Hermione");
        answer1.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        answer2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        answer1.addClickListener(event -> answerClicked(1));
        answer2.addClickListener(event -> answerClicked(2));
        HorizontalLayout horizontalLayout = new HorizontalLayout(answer1, answer2);
        verticalLayout.add(horizontalLayout);

        Button answear3 = new Button("Ron");
        Button answear4 = new Button("Draco");
        answear3.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        answear4.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        answear3.addClickListener(event -> answerClicked(3));
        answear4.addClickListener(event -> answerClicked(4));
        horizontalLayout = new HorizontalLayout(answear3, answear4);
        verticalLayout.add(horizontalLayout);

        add(verticalLayout);
    }

    private void answerClicked(int answer) {
        System.out.println(answer);
    }

    private void loadQuiz(Long quizId) {
        Optional<Quiz> quiz = quizService.get(quizId);
        if (quiz.isEmpty()) {
            quizTitle.setText("Quiz not found");
        } else {
            quizTitle.setText(quiz.get().getTitle());
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
