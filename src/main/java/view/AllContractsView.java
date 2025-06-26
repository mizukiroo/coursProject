package view;

import controller.AllContractsController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AllContractsView {
    private final Scene scene;

    public AllContractsView(Stage stage, String username, MainView mainView) {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(30));
        layout.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.web("#FFFACD"), CornerRadii.EMPTY, Insets.EMPTY)));
        layout.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(10))));

        //Заголовок
        Label title = new Label("Все оформленные договоры");
        title.setStyle("-fx-font-size: 22px; -fx-font-family: 'Comic Sans MS';");

        //Область для отображения договоров
        VBox tableBox = new VBox(10);
        tableBox.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-font-family: 'Comic Sans MS'; -fx-font-size: 14px;");

        ScrollPane scrollPane = new ScrollPane(tableBox);
        scrollPane.setFitToWidth(false);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setPannable(true);
        scrollPane.setPrefHeight(250);

        //Кнопка "Назад"
        Button backButton = new Button("Назад");
        backButton.setStyle("""
            -fx-font-family: 'Comic Sans MS';
            -fx-font-size: 16px;
            -fx-min-width: 200px;
            -fx-min-height: 40px;
            -fx-background-color: #FF7F7F;
            -fx-text-fill: white;
            -fx-background-radius: 999px;
            -fx-border-radius: 999px;
        """);

        layout.getChildren().addAll(title, scrollPane, backButton);

        new AllContractsController(tableBox, backButton, mainView, username);

        this.scene = new Scene(layout, 700, 550);
    }

    public Scene getScene() {
        return scene;
    }
}

