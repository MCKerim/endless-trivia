package com.kblanks.endlesstrivia.views.signup;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

@PageTitle("Sign Up")
@Menu(icon = "line-awesome/svg/user.svg", order = 6)
@Route(value = "signup")
@AnonymousAllowed
public class SignUpView extends Composite<VerticalLayout> {

    public SignUpView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        TextField textField = new TextField();
        EmailField emailField = new EmailField();
        PasswordField passwordField = new PasswordField();
        PasswordField passwordField2 = new PasswordField();
        Hr hr = new Hr();
        Button buttonPrimary = new Button();
        Button buttonSecondary = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.CENTER);
        getContent().setAlignItems(Alignment.CENTER);
        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        h3.setText("Endless Trivia | Create Account");
        h3.setWidth("100%");
        layoutColumn3.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutColumn3);
        layoutColumn3.addClassName(Gap.XSMALL);
        layoutColumn3.setWidth("100%");
        layoutColumn3.getStyle().set("flex-grow", "1");
        textField.setLabel("Name");
        textField.setWidth("100%");
        emailField.setLabel("Email");
        emailField.setWidth("100%");
        passwordField.setLabel("Password");
        passwordField.setWidth("100%");
        passwordField2.setLabel("Repeat Password");
        passwordField2.setWidth("100%");
        buttonPrimary.setText("Create Account");
        buttonPrimary.setWidth("100%");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonSecondary.setText("Log In");
        buttonSecondary.setWidth("100%");
        getContent().add(layoutColumn2);
        layoutColumn2.add(h3);
        layoutColumn2.add(layoutColumn3);
        layoutColumn3.add(textField);
        layoutColumn3.add(emailField);
        layoutColumn3.add(passwordField);
        layoutColumn3.add(passwordField2);
        layoutColumn3.add(hr);
        layoutColumn3.add(buttonPrimary);
        layoutColumn3.add(buttonSecondary);
    }
}
