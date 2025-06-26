package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import view.MainView;
import view.RegisterView;

public class RegisterController {
    private final Stage stage;
    private final AuthController authController;
    private final String role;
    private final MainView mainView;
    private final RegisterView view;
    private final Scene scene;

    private TextField loginField;
    private PasswordField passwordField;
    private PasswordField confirmField;
    private TextField orgField;
    private TextField addressField;
    private TextField phoneField;
    private TextField requisitesField;
    private TextField contactField;
    private Label errorLabel;

    public RegisterController(Stage stage, AuthController authController, String role, MainView mainView) {
        this.stage = stage;
        this.authController = authController;
        this.role = role;
        this.mainView = mainView;
        this.view = new RegisterView(this, role);
        this.scene = new Scene(view.getView(), 500, 600);
    }

    public Scene getScene() {
        return scene;
    }

    public void attachHandlers(
            Button registerButton,
            Button backButton,
            TextField orgField,
            TextField addressField,
            TextField phoneField,
            TextField requisitesField,
            TextField contactField,
            TextField loginField,
            PasswordField passwordField,
            PasswordField confirmField,
            Label errorLabel
    ) {
        this.orgField = orgField;
        this.addressField = addressField;
        this.phoneField = phoneField;
        this.requisitesField = requisitesField;
        this.contactField = contactField;
        this.loginField = loginField;
        this.passwordField = passwordField;
        this.confirmField = confirmField;
        this.errorLabel = errorLabel;

        registerButton.setOnAction(new RegisterHandler());
        backButton.setOnAction(new BackHandler());
    }

    private class RegisterHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String org = orgField.getText().trim();
            String addr = addressField.getText().trim();
            String phone = phoneField.getText().trim();
            String req = requisitesField.getText().trim();
            String contact = contactField.getText().trim();
            String login = loginField.getText().trim();
            String pwd = passwordField.getText().trim();
            String conf = confirmField.getText().trim();

            if (org.isEmpty() || login.isEmpty() || pwd.isEmpty() || conf.isEmpty()) {
                errorLabel.setText("Все обязательные поля должны быть заполнены!");
                return;
            }

            if (!pwd.equals(conf)) {
                errorLabel.setText("Пароли не совпадают!");
                return;
            }

            boolean success = authController.registerWithClient(
                    login, pwd, role, org, addr, phone, req, contact
            );

            if (success) {
                mainView.showLoginView();
            } else {
                errorLabel.setText("Ошибка регистрации. Возможно, логин уже занят.");
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



