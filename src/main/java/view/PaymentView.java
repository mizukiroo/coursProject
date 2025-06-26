package view;

import dao.ContractDAO;
import dao.PaymentDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Database;
import model.TradePoint;

import java.time.LocalDate;
import java.util.List;

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
        pointBox.setStyle("-fx-font-size: 14px;");
        ContractDAO contractDAO = new ContractDAO(Database.getInstance().getConnection());
        List<TradePoint> tradePoints = contractDAO.getContractsByUsername(username);
        pointBox.getItems().addAll(tradePoints);

        TextField amountField = new TextField();
        amountField.setPromptText("Сумма платежа");

        Label statusLabel = new Label();

        Button payButton = createGreenButton("Внести");
        Button backButton = createRedButton("Назад");

        backButton.setOnAction(e -> mainView.showProfileView(username));

        payButton.setOnAction(e -> {
            TradePoint selected = pointBox.getValue();
            if (selected == null) {
                statusLabel.setText("Выберите точку.");
                statusLabel.setTextFill(Color.RED);
                return;
            }

            try {
                double amount = Double.parseDouble(amountField.getText().trim());
                PaymentDAO paymentDAO = new PaymentDAO(Database.getInstance().getConnection());
                int contractId = selected.getContractId();
                int pointId = selected.getId();

                boolean ok = paymentDAO.insertPayment(contractId, pointId, LocalDate.now(), amount);

                statusLabel.setText(ok ? "Платёж внесён!" : "Ошибка внесения платежа.");
                statusLabel.setTextFill(ok ? Color.GREEN : Color.RED);
            } catch (Exception ex) {
                statusLabel.setText("Некорректная сумма.");
                statusLabel.setTextFill(Color.RED);
            }
        });

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

