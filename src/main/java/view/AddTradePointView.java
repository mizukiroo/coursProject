package view;

import controller.AddTradePointController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AddTradePointView {
    private final Scene scene;

    public AddTradePointView(Stage stage, String username, MainView mainView) {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(30));
        layout.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.web("#FFFACD"), CornerRadii.EMPTY, Insets.EMPTY)));
        layout.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(10))));

        Label title = new Label("Добавить торговую точку");
        title.setStyle("-fx-font-size: 22px; -fx-font-family: 'Comic Sans MS';");

        TextField floorField = new TextField();
        floorField.setPromptText("Этаж");
        floorField.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 14px;");

        TextField areaField = new TextField();
        areaField.setPromptText("Площадь");
        areaField.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 14px;");

        CheckBox acBox = new CheckBox("Кондиционер");
        acBox.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 14px;");

        TextField rentField = new TextField();
        rentField.setPromptText("Цена за день");
        rentField.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 14px;");

        Label statusLabel = new Label();
        statusLabel.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 14px;");

        VBox pointsBox = new VBox(10);
        pointsBox.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-font-family: 'Comic Sans MS'; -fx-font-size: 14px;");
        ScrollPane scrollPane = new ScrollPane(pointsBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(200);
        scrollPane.setStyle("-fx-font-family: 'Comic Sans MS';");

        Button addButton = new Button("Добавить");
        addButton.setStyle("""
            -fx-font-family: 'Comic Sans MS';
            -fx-font-size: 16px;
            -fx-min-width: 200px;
            -fx-min-height: 40px;
            -fx-background-color: #6EBF8B;
            -fx-text-fill: white;
            -fx-background-radius: 999px;
            -fx-border-radius: 999px;
        """);

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

        layout.getChildren().addAll(
                title, floorField, areaField, acBox, rentField, statusLabel,
                addButton, scrollPane, backButton
        );

        new AddTradePointController(
                floorField, areaField, rentField, acBox,
                addButton, backButton, statusLabel,
                username, mainView, pointsBox
        );

        this.scene = new Scene(layout, 600, 550);
    }

    public Scene getScene() {
        return scene;
    }
}

