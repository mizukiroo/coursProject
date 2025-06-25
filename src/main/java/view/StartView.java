package view;

import controller.StartController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StartView {
    private final VBox layout;

    public StartView(StartController controller) {
        layout = new VBox(15);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(30));
        layout.setBackground(new Background(new BackgroundFill(Color.web("#FFFACD"), new CornerRadii(10), Insets.EMPTY)));
        layout.setBorder(new Border(new BorderStroke(
                Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(10))));

        Image logo = new Image(getClass().getResourceAsStream("/images/logo.png"));
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(150);
        logoView.setPreserveRatio(true);

        Label title = new Label("Добро пожаловать");
        title.setFont(Font.font("Comic Sans MS", 24));
        title.setTextFill(Color.BLACK);

        Button loginButton = createGreenButton("Войти");
        Button registerButton = createGreenButton("Зарегистрироваться");

        controller.setLoginAction(loginButton);
        controller.setRegisterAction(registerButton);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        Image bush = new Image(getClass().getResourceAsStream("/images/kust.png"));
        ImageView bushView = new ImageView(bush);
        bushView.setFitHeight(400);
        bushView.setPreserveRatio(true);
        bushView.setTranslateY(-200); // поднимет на 10px вверх


        layout.getChildren().addAll(logoView, title, loginButton, registerButton, spacer, bushView);
    }

    public Parent getView() {
        return layout;
    }

    private Button createGreenButton(String text) {
        Button btn = new Button(text);
        btn.setStyle("""
        -fx-font-family: "Comic Sans MS";
        -fx-font-size: 18px;
        -fx-min-width: 200px;
        -fx-min-height: 40px;
        -fx-background-color: #6EBF8B;
        -fx-text-fill: white;
        -fx-background-radius: 999px;
        -fx-border-radius: 999px;
    """);
        return btn;
    }

}

