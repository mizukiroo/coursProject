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
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.web("#FFFACD"), CornerRadii.EMPTY, Insets.EMPTY)));
        layout.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(10))));

        // Заголовок
        Label title = new Label("Все оформленные договоры");
        title.setStyle("-fx-font-size: 22px; -fx-font-family: 'Comic Sans MS';");

        // Блок договоров
        // Таблица торговых точек
        VBox tableBox = new VBox(10);
        tableBox.setStyle("-fx-background-color: white; -fx-padding: 10px;");
        tableBox.setPrefWidth(800);

        ScrollPane scrollPane = new ScrollPane(tableBox);
        scrollPane.setFitToWidth(false); // чтобы не подгонять под ширину окна
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setPannable(true);
        scrollPane.setPrefHeight(250);


        // Кнопка назад
        Button backButton = new Button("Назад");
        backButton.setStyle("""
            -fx-background-color: #FF7F7F;
            -fx-text-fill: white;
            -fx-font-family: 'Comic Sans MS';
            -fx-font-size: 16px;
            -fx-min-width: 200px;
            -fx-min-height: 40px;
        """);

        layout.getChildren().addAll(title, scrollPane, backButton);

        // Подключение контроллера
        new AllContractsController(tableBox, backButton, mainView, username);

        this.scene = new Scene(layout, 700, 500);
    }

    public Scene getScene() {
        return scene;
    }
}
