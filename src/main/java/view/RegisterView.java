package view;

import controller.RegisterController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class RegisterView {
    private final RegisterController controller;
    private final String role;
    private Parent view;

    public RegisterView(RegisterController controller, String role) {
        this.controller = controller;
        this.role = role;
        this.view = createView();
    }

    public Parent getView() {
        return view;
    }

    private Parent createView() {
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.setBackground(new Background(new BackgroundFill(Color.web("#FFFACD"), new CornerRadii(10), Insets.EMPTY)));
        layout.setBorder(new Border(new BorderStroke(
                Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(10))));

        Label title = new Label("Регистрация");
        title.setFont(Font.font("Comic Sans MS", 22));
        title.setTextFill(Color.BLACK);

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        errorLabel.setStyle("-fx-font-size: 14px;");

        //Поля формы
        TextField orgField = createTextField("Введите название организации");
        TextField addressField = createTextField("Введите адрес");
        TextField phoneField = createTextField("Введите телефон");
        TextField requisitesField = createTextField("Введите реквизиты");
        TextField contactField = createTextField("Введите контактное лицо");
        TextField loginField = createTextField("Придумайте логин");
        PasswordField passwordField = createPasswordField("Придумайте пароль");
        PasswordField confirmField = createPasswordField("Повторите пароль");

        //Кнопки
        Button registerButton = createGreenButton("Зарегистрироваться");
        Button backButton = createRedButton("Назад");

        //Добавляем поля с подписями
        layout.getChildren().addAll(
                title,
                createLabeledField("Название организации:", orgField),
                createLabeledField("Адрес:", addressField),
                createLabeledField("Телефон:", phoneField),
                createLabeledField("Реквизиты:", requisitesField),
                createLabeledField("Контактное лицо:", contactField),
                createLabeledField("Логин:", loginField),
                createLabeledField("Пароль:", passwordField),
                createLabeledField("Подтвердите пароль:", confirmField),
                errorLabel,
                registerButton,
                backButton
        );

        //Передача всех полей в контроллер
        controller.attachHandlers(
                registerButton,
                backButton,
                orgField,
                addressField,
                phoneField,
                requisitesField,
                contactField,
                loginField,
                passwordField,
                confirmField,
                errorLabel
        );

        ScrollPane scrollPane = new ScrollPane(layout);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(10));
        return scrollPane;
    }

    private TextField createTextField(String prompt) {
        TextField tf = new TextField();
        tf.setPromptText(prompt);
        tf.setStyle("-fx-font-size: 14px; -fx-pref-width: 300px;");
        return tf;
    }

    private PasswordField createPasswordField(String prompt) {
        PasswordField pf = new PasswordField();
        pf.setPromptText(prompt);
        pf.setStyle("-fx-font-size: 14px; -fx-pref-width: 300px;");
        return pf;
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
