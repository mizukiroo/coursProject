package view;

import dao.ClientDAO;
import javafx.event.EventHandler;
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

import java.awt.event.ActionEvent;

public class ClientProfileView {
    private final Stage stage;
    private final String username;
    private final Scene scene;

    public ClientProfileView(Stage stage, String username) {
        this.stage = stage;
        this.username = username;
        this.scene = new Scene(createView(), 600, 550);
    }

    public Scene getScene() {
        return scene;
    }

    private Parent createView() {
        VBox content = new VBox(15);
        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(30));

        Label title = new Label("Личный кабинет");
        title.setFont(Font.font("Comic Sans MS", 22));
        title.setTextFill(Color.BLACK);

        ClientDAO clientDAO = new ClientDAO(Database.getInstance().getConnection());
        Client client = clientDAO.findByUsername(username);

        TextField nameField = createTextField(client.getName());
        TextField addressField = createTextField(client.getAddress());
        TextField phoneField = createTextField(client.getPhone());
        TextField requisitesField = createTextField(client.getRequisites());
        TextField contactField = createTextField(client.getContactPerson());

        Label statusLabel = new Label();
        statusLabel.setTextFill(Color.RED);
        statusLabel.setFont(Font.font("Comic Sans MS", 14));

        Button saveButton = createGreenButton("Сохранить изменения");
        Button backButton = createRedButton("Назад");

        backButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                stage.setScene(new SuccessView(username, "client", stage, new MainView(stage)).getScene());

            }
        });

        content.getChildren().addAll(
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

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(20));
        scrollPane.setStyle("-fx-background: white; -fx-border-color: #F4D03F;");

        VBox container = new VBox(scrollPane);
        container.setPadding(new Insets(20));
        container.setBackground(new Background(new BackgroundFill(Color.web("#FFFACD"), new CornerRadii(10), Insets.EMPTY)));
        container.setBorder(new Border(new BorderStroke(
                Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(10))));

        return container;
    }

    private TextField createTextField(String text) {
        TextField tf = new TextField(text);
        tf.setStyle("-fx-font-size: 14px; -fx-pref-width: 300px; -fx-font-family: 'Comic Sans MS';");
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
        label.setFont(Font.font("Comic Sans MS", 16));
        VBox box = new VBox(5, label, input);
        box.setAlignment(Pos.CENTER_LEFT);
        return box;
    }
}
