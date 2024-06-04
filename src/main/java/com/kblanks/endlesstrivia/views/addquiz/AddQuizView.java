package com.kblanks.endlesstrivia.views.addquiz;

import com.kblanks.endlesstrivia.data.Quiz;
import com.kblanks.endlesstrivia.services.QuizService;
import com.kblanks.endlesstrivia.services.UserEndpoint;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PageTitle("Add Quiz")
@Menu(icon = "line-awesome/svg/plus-square-solid.svg", order = 3)
@Route(value = "add")
@PermitAll
public class AddQuizView extends Composite<VerticalLayout> {

    public AddQuizView(QuizService quizService, UserEndpoint userEndpoint) {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.CENTER);
        layoutColumn2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        h3.setText("Generate a new quiz");
        h3.setWidth("100%");
        getContent().add(layoutColumn2);
        layoutColumn2.add(h3);

        TextField nameField = new TextField();
        nameField.setLabel("Name");
        nameField.setWidth("100%");

        TextField descriptionField = new TextField();
        descriptionField.setLabel("Description");
        descriptionField.setWidth("100%");

        TextField imageField = new TextField();
        imageField.setLabel("Image URL");
        imageField.setWidth("100%");

        Button buttonPrimary = new Button();
        buttonPrimary.addClickListener(event -> generateQuizButtonClicked(nameField, descriptionField, imageField, quizService, userEndpoint));
        buttonPrimary.setText("Generate");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary.setWidth("100%");

        layoutColumn2.add(nameField);
        layoutColumn2.add(descriptionField);
        layoutColumn2.add(imageField);
        layoutColumn2.add(buttonPrimary);
    }

    private void generateQuizButtonClicked(TextField nameField, TextField descriptionField, TextField imageField, QuizService quizService, UserEndpoint userEndpoint) {
        if(nameField.getValue().isEmpty() || descriptionField.getValue().isEmpty() || imageField.getValue().isEmpty() || userEndpoint.getAuthenticatedUser().isEmpty()) {
            Notification notification = Notification.show("Please fill in all fields");
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            return;
        }

        Quiz quiz = new Quiz();
        quiz.setName(nameField.getValue());
        quiz.setDescription(descriptionField.getValue());
        quiz.setImage(imageField.getValue());
        quiz.setOwner(userEndpoint.getAuthenticatedUser().get());
        quizService.save(quiz);

        nameField.clear();
        descriptionField.clear();
        imageField.clear();

        Notification notification = Notification.show("Quiz generated");
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }
}
