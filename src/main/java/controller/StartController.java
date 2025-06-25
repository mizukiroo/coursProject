package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.MainView;
import view.StartView;
import javafx.scene.control.Button;

public class StartController {
    private final Stage stage;
    private final MainView mainView;
    private final StartView view;
    private final Scene scene;

    public StartController(Stage stage, MainView mainView) {
        this.stage = stage;
        this.mainView = mainView;
        this.view = new StartView(this);
        this.scene = new Scene(view.getView(), 400, 300);
    }

    public Scene getScene() {
        return scene;
    }

    public void setLoginAction(Button loginButton) {
        loginButton.setOnAction(new LoginHandler());
    }

    public void setRegisterAction(Button registerButton) {
        registerButton.setOnAction(new RegisterHandler());
    }

    private class LoginHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            mainView.showLoginView();
        }
    }

    private class RegisterHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            mainView.showRegisterView("client");
        }
    }
}





