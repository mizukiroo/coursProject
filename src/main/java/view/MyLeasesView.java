package view;

import controller.MyLeasesController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MyLeasesView {
    private final Scene scene;

    public MyLeasesView(Stage stage, String username, MainView mainView) {
        VBox root = new VBox(20);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(30));
        root.setBackground(new Background(new BackgroundFill(Color.web("#FFFACD"), new CornerRadii(10), Insets.EMPTY)));
        root.setBorder(new Border(new BorderStroke(
                Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(10)
        )));

        Label title = new Label("Мои аренды");
        title.setStyle("-fx-font-size: 22px; -fx-font-family: 'Comic Sans MS'; -fx-text-fill: black;");

        VBox leaseListBox = new VBox(10);
        leaseListBox.setPadding(new Insets(10));

        ScrollPane scrollPane = new ScrollPane(leaseListBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: white; -fx-border-color: #F4D03F;");
        scrollPane.setPrefHeight(350); // задаёт большую высоту
        VBox.setVgrow(scrollPane, Priority.ALWAYS); // ⬅️ позволяет ScrollPane расширяться вниз

        Button backButton = new Button("Назад");
        backButton.setStyle("""
            -fx-font-family: "Comic Sans MS";
            -fx-font-size: 16px;
            -fx-min-width: 200px;
            -fx-min-height: 40px;
            -fx-background-color: #FF7F7F;
            -fx-text-fill: white;
            -fx-background-radius: 999px;
            -fx-border-radius: 999px;
        """);

        root.getChildren().addAll(title, scrollPane, backButton);

        // Контроллер
        new MyLeasesController(username, leaseListBox, backButton, mainView);

        this.scene = new Scene(root, 650, 550);
    }

    public Scene getScene() {
        return scene;
    }
}

