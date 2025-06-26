package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;
import view.LoginView;
import view.MainView;

public class LoginController {
    private final Stage stage;
    private final MainView mainView;
    private final AuthController authController;
    private final LoginView loginView;
    private final Scene scene;

    public LoginController(Stage stage, AuthController authController, MainView mainView) {
        this.stage = stage;
        this.authController = authController;
        this.mainView = mainView;
        this.loginView = new LoginView(this);
        this.scene = new Scene(loginView.getView(), 400, 350);
    }

    public Scene getScene() {
        return scene;
    }

    public void attachHandlers(Button loginButton, Button backButton, TextField loginField, PasswordField passwordField, Label errorLabel) {
        loginButton.setOnAction(new LoginHandler(loginField, passwordField, errorLabel));
        backButton.setOnAction(new BackHandler());
    }

    private class LoginHandler implements EventHandler<ActionEvent> {
        private final TextField loginField;
        private final PasswordField passwordField;
        private final Label errorLabel;

        public LoginHandler(TextField loginField, PasswordField passwordField, Label errorLabel) {
            this.loginField = loginField;
            this.passwordField = passwordField;
            this.errorLabel = errorLabel;
        }

        @Override
        public void handle(ActionEvent event) {
            String login = loginField.getText();
            String password = passwordField.getText();

            if (login.isEmpty() || password.isEmpty()) {
                errorLabel.setText("Пожалуйста, введите логин и пароль.");
                return;
            }

            User user = authController.login(login, password);
            if (user != null) {
                mainView.showSuccessView(user.getLogin(), user.getRole());
            } else {
                errorLabel.setText("Неверный логин или пароль.");
            }
        }
    }

    private class BackHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            mainView.showStartView();
        }
    }
}



