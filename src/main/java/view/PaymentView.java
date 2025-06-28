package view;

import controller.PaymentController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.TradePoint;

public class PaymentView {
    private final Scene scene;

    public PaymentView(Stage stage, String username, MainView mainView) {
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setBackground(new Background(new BackgroundFill(Color.web("#FFFACD"), CornerRadii.EMPTY, Insets.EMPTY)));

        Label title = new Label("Внести платёж");
        title.setStyle("-fx-font-size: 22px; -fx-font-family: 'Comic Sans MS';");

        ComboBox<TradePoint> pointBox = new ComboBox<>();
        pointBox.setStyle("-fx-font-size: 14px; -fx-font-family: 'Comic Sans MS';");
        pointBox.setPromptText("Выберите точку");

        TextField amountField = new TextField();
        amountField.setPromptText("Сумма платежа");
        amountField.setStyle("-fx-font-size: 14px; -fx-font-family: 'Comic Sans MS';");

        Label statusLabel = new Label();
        statusLabel.setStyle("-fx-font-size: 14px; -fx-font-family: 'Comic Sans MS';");

        Button payButton = createGreenButton("Внести");
        Button backButton = createRedButton("Назад");

        // ⬇ Классический контроллер с внутренними слушателями
        PaymentController controller = new PaymentController(
                pointBox,
                amountField,
                statusLabel,
                payButton,
                backButton,
                layout,
                username,
                mainView
        );

        layout.getChildren().addAll(title, pointBox, amountField, statusLabel, payButton, backButton);
        this.scene = new Scene(layout, 500, 400);
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
}


