package view;

import controller.LeaseController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.TradePoint;

public class LeaseView {
    private final Scene scene;

    public LeaseView(Stage stage, String username, MainView mainView) {
        LeaseController controller = new LeaseController(mainView, username);
        this.scene = new Scene(createView(controller), 600, 550);
    }

    public Scene getScene() {
        return scene;
    }

    private Parent createView(LeaseController controller) {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setBackground(new Background(new BackgroundFill(Color.web("#FFFACD"), new CornerRadii(10), Insets.EMPTY)));
        layout.setBorder(new Border(new BorderStroke(
                Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(10))));

        Label title = new Label("Оформление аренды");
        title.setFont(Font.font("Comic Sans MS", 24));
        title.setTextFill(Color.BLACK);

        Label pointLabel = new Label("Выберите торговую точку:");
        pointLabel.setFont(Font.font("Comic Sans MS", 16));
        pointLabel.setTextFill(Color.BLACK);

        ComboBox<TradePoint> pointComboBox = new ComboBox<>();
        pointComboBox.setPrefWidth(300);
        pointComboBox.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 14px;");

        Label startLabel = new Label("Дата начала:");
        startLabel.setFont(Font.font("Comic Sans MS", 16));
        startLabel.setTextFill(Color.BLACK);

        DatePicker startDatePicker = new DatePicker();
        startDatePicker.setPrefWidth(300);
        startDatePicker.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 14px;");

        Label endLabel = new Label("Дата окончания:");
        endLabel.setFont(Font.font("Comic Sans MS", 16));
        endLabel.setTextFill(Color.BLACK);

        DatePicker endDatePicker = new DatePicker();
        endDatePicker.setPrefWidth(300);
        endDatePicker.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 14px;");

        Label costLabel = new Label("Стоимость: ");
        costLabel.setFont(Font.font("Comic Sans MS", 16));
        costLabel.setTextFill(Color.BLACK);

        Label statusLabel = new Label();
        statusLabel.setStyle("-fx-text-fill: red; -fx-font-family: 'Comic Sans MS';");

        Button confirmButton = createGreenButton("Подтвердить аренду");
        Button backButton = createRedButton("Назад");

        controller.attachHandlers(pointComboBox, startDatePicker, endDatePicker, costLabel, statusLabel, confirmButton, backButton);

        layout.getChildren().addAll(
                title,
                pointLabel, pointComboBox,
                startLabel, startDatePicker,
                endLabel, endDatePicker,
                costLabel,
                statusLabel,
                confirmButton,
                backButton
        );

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
}

