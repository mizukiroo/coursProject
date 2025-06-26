package view;

import controller.LeaseController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.TradePoint;

public class LeaseView {
    private final Scene scene;

    public LeaseView(Stage stage, String username, MainView mainView) {
        LeaseController controller = new LeaseController(mainView, username);
        this.scene = new Scene(createView(controller), 550, 450);
    }

    public Scene getScene() {
        return scene;
    }

    private Parent createView(LeaseController controller) {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(new BackgroundFill(Color.web("#FFF9C4"), CornerRadii.EMPTY, Insets.EMPTY)));

        Label title = new Label("Оформление аренды");
        title.setStyle("-fx-font-size: 22px; -fx-text-fill: #333333;");

        ComboBox<TradePoint> pointComboBox = new ComboBox<>();
        pointComboBox.setPrefWidth(300);

        DatePicker startDatePicker = new DatePicker();
        startDatePicker.setPrefWidth(300);

        DatePicker endDatePicker = new DatePicker();
        endDatePicker.setPrefWidth(300);

        Label costLabel = new Label("Стоимость: ");
        costLabel.setStyle("-fx-font-size: 16px;");

        Label statusLabel = new Label();
        statusLabel.setStyle("-fx-text-fill: red;");

        Button confirmButton = createGreenButton("Подтвердить аренду");
        Button backButton = createRedButton("Назад");

        controller.attachHandlers(pointComboBox, startDatePicker, endDatePicker, costLabel, statusLabel, confirmButton, backButton);

        layout.getChildren().addAll(
                title,
                new Label("Выберите торговую точку:"), pointComboBox,
                new Label("Дата начала:"), startDatePicker,
                new Label("Дата окончания:"), endDatePicker,
                costLabel,
                statusLabel,
                confirmButton,
                backButton
        );

        return layout;
    }

    private Button createGreenButton(String text) {
        Button btn = new Button(text);
        btn.setStyle("-fx-font-size: 16px; -fx-min-width: 250px; -fx-background-color: #6EBF8B; -fx-text-fill: white;");
        return btn;
    }

    private Button createRedButton(String text) {
        Button btn = new Button(text);
        btn.setStyle("-fx-font-size: 16px; -fx-min-width: 250px; -fx-background-color: #FF7F7F; -fx-text-fill: white;");
        return btn;
    }
}
