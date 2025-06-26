package view;

import dao.ClientDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Client;
import model.Database;

public class ClientProfileView {
    private final Stage stage;
    private final String username;
    private final Scene scene;

    public ClientProfileView(Stage stage, String username) {
        this.stage = stage;
        this.username = username;
        this.scene = new Scene(createView(), 520, 480);
    }

    public Scene getScene() {
        return scene;
    }

    private Parent createView() {
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        Label title = new Label("Личный кабинет");
        title.setFont(Font.font("Comic Sans MS", 22));

        ClientDAO clientDAO = new ClientDAO(Database.getInstance().getConnection());
        Client client = clientDAO.findByUsername(username); // ты реализуешь этот метод

        // Поля редактирования
        TextField nameField = createTextField(client.getName());
        TextField addressField = createTextField(client.getAddress());
        TextField phoneField = createTextField(client.getPhone());
        TextField requisitesField = createTextField(client.getRequisites());
        TextField contactField = createTextField(client.getContactPerson());

        Label statusLabel = new Label();
        statusLabel.setTextFill(Color.RED);

        Button saveButton = createGreenButton("Сохранить изменения");
        Button backButton = createRedButton("Назад");

        saveButton.setOnAction(e -> {
            boolean updated = clientDAO.updateClient(
                    client.getId(),
                    nameField.getText(),
                    addressField.getText(),
                    phoneField.getText(),
                    requisitesField.getText(),
                    contactField.getText()
            );
            statusLabel.setText(updated ? "Данные обновлены" : "Ошибка обновления");
            if (updated) statusLabel.setTextFill(Color.GREEN);
        });

        backButton.setOnAction(e -> stage.setScene(new SuccessView(username, "client", stage, new MainView(stage)).getScene()));

        layout.getChildren().addAll(
                title,
                createLabeledField("Название организации:", nameField),
                createLabeledField("Адрес:", addressField),
                createLabeledField("Телефон:", phoneField),
                createLabeledField("Реквизиты:", requisitesField),
                createLabeledField("Контактное лицо:", contactField),
                statusLabel,
                saveButton,
                backButton
        );

        return layout;
    }

    private TextField createTextField(String text) {
        TextField tf = new TextField(text);
        tf.setStyle("-fx-font-size: 14px; -fx-pref-width: 300px;");
        return tf;
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

    private VBox createLabeledField(String labelText, Control input) {
        Label label = new Label(labelText);
        label.setFont(Font.font("Arial", 16));
        VBox box = new VBox(5, label, input);
        box.setAlignment(Pos.CENTER_LEFT);
        return box;
    }
}
