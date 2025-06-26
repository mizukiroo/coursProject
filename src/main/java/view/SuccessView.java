package view;

import controller.SuccessController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SuccessView {
    private final Scene scene;

    public SuccessView(String username, String role, Stage stage, MainView mainView) {
        this.scene = createScene(username, role, stage, mainView);
    }

    private Scene createScene(String username, String role, Stage stage, MainView mainView) {
        Label welcomeLabel = new Label("Добро пожаловать, " + username);
        welcomeLabel.setFont(Font.font("Comic Sans MS", 24));
        welcomeLabel.setTextFill(Color.BLACK);

        Button profileButton = createGreenButton("Личный кабинет");
        Button leaseButton = createGreenButton("Оформить аренду");
        Button myLeasesButton = createGreenButton("Мои аренды");
        Button logoutButton = createRedButton("Выйти");

        SuccessController controller = new SuccessController(mainView, username, profileButton, leaseButton, myLeasesButton, logoutButton);

        VBox layout = new VBox(20, welcomeLabel, profileButton, leaseButton, myLeasesButton, logoutButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.setBackground(new Background(new BackgroundFill(Color.web("#FFFACD"), new CornerRadii(10), Insets.EMPTY)));
        layout.setBorder(new Border(new BorderStroke(
                Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(3))));

        return new Scene(layout, 550, 450); // чуть больше фиксированный размер
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

    private Button createRedButton(String text) {
        Button btn = new Button(text);
        btn.setStyle("""
        -fx-font-family: "Comic Sans MS";
        -fx-font-size: 18px;
        -fx-min-width: 200px;
        -fx-min-height: 40px;
        -fx-background-color: #FF7F7F;
        -fx-text-fill: white;
        -fx-background-radius: 999px;
        -fx-border-radius: 999px;
    """);
        return btn;
    }

    public Scene getScene() {
        return scene;
    }
}
