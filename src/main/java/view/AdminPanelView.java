package view;

import controller.AdminPanelController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AdminPanelView {
    private final VBox layout;
    private final Scene scene;

    public AdminPanelView(Stage stage, String username, MainView mainView) {
        layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.setBackground(new Background(new BackgroundFill(Color.web("#FFFACD"), new CornerRadii(10), Insets.EMPTY)));
        layout.setBorder(new Border(new BorderStroke(
                Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(10))));

        Image duckphone = new Image(getClass().getResourceAsStream("/images/duckphone.png"));
        ImageView duckphoneView = new ImageView(duckphone);
        duckphoneView.setFitHeight(100);
        duckphoneView.setPreserveRatio(true);

        Label title = new Label("Панель администратора");
        title.setFont(Font.font("Comic Sans MS", 22));
        title.setTextFill(Color.BLACK);

        Button addPointButton = createGreenButton("Добавить торговую точку");
        Button viewContractsButton = createGreenButton("Посмотреть договоры");
        Button paymentsButton = createGreenButton("Все платежи");
        Button backButton = createRedButton("Назад");

        layout.getChildren().addAll(duckphoneView, title, addPointButton, viewContractsButton, paymentsButton, backButton);


        new AdminPanelController(addPointButton, viewContractsButton, paymentsButton, backButton, mainView, username);
        this.scene = new Scene(layout, 500, 500);
    }

    public Scene getScene() {
        return scene;
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

    public Parent getView() {
        return layout;
    }
}

