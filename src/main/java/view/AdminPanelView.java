package view;

import controller.AdminPanelController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AdminPanelView {
    private final Scene scene;

    public AdminPanelView(Stage stage, String username, MainView mainView) {
        VBox layout = createMainLayout();
        Label title = createTitleLabel();

        Button addPointButton = createGreenButton("Добавить торговую точку");
        Button viewContractsButton = createGreenButton("Посмотреть договоры");
        Button backButton = createRedButton("Назад");

        layout.getChildren().addAll(title, addPointButton, viewContractsButton, backButton);

        new AdminPanelController(addPointButton, viewContractsButton, backButton, mainView, username);

        this.scene = new Scene(layout, 500, 400);
    }

    private VBox createMainLayout() {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(40));
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(new BackgroundFill(
                Color.web("#FFFACD"), CornerRadii.EMPTY, Insets.EMPTY)));
        layout.setBorder(new Border(new BorderStroke(
                Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(10))));
        return layout;
    }

    private Label createTitleLabel() {
        Label title = new Label("Панель администратора");
        title.setStyle("""
            -fx-font-size: 24px;
            -fx-font-family: 'Comic Sans MS';
        """);
        return title;
    }

    private Button createGreenButton(String text) {
        Button btn = new Button(text);
        btn.setStyle("""
            -fx-font-family: "Comic Sans MS";
            -fx-font-size: 16px;
            -fx-min-width: 250px;
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
            -fx-font-size: 16px;
            -fx-min-width: 250px;
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
