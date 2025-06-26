package controller;

import dao.TradePointDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.Database;
import model.TradePoint;
import view.MainView;

import java.util.List;

public class AddTradePointController {
    private final TextField floorField;
    private final TextField areaField;
    private final TextField rentField;
    private final CheckBox acBox;
    private final Button addButton;
    private final Button backButton;
    private final Label statusLabel;
    private final VBox pointsBox;
    private final MainView mainView;
    private final String username;
    private final TradePointDAO dao;

    public AddTradePointController(TextField floorField,
                                   TextField areaField,
                                   TextField rentField,
                                   CheckBox acBox,
                                   Button addButton,
                                   Button backButton,
                                   Label statusLabel,
                                   String username,
                                   MainView mainView,
                                   VBox pointsBox) {
        this.floorField = floorField;
        this.areaField = areaField;
        this.rentField = rentField;
        this.acBox = acBox;
        this.addButton = addButton;
        this.backButton = backButton;
        this.statusLabel = statusLabel;
        this.username = username;
        this.mainView = mainView;
        this.pointsBox = pointsBox;
        this.dao = new TradePointDAO(Database.getInstance().getConnection());

        this.addButton.setOnAction(new AddPointHandler());
        this.backButton.setOnAction(new BackHandler());

        loadPoints();
    }

    private void loadPoints() {
        pointsBox.getChildren().clear();
        List<TradePoint> points = dao.getAllPoints();
        for (TradePoint point : points) {
            Label label = new Label(point.toString());
            label.setStyle("-fx-font-size: 14px; -fx-font-family: 'Comic Sans MS';");
            pointsBox.getChildren().add(label);
        }
    }

    private class AddPointHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                int floor = Integer.parseInt(floorField.getText().trim());
                double area = Double.parseDouble(areaField.getText().trim());
                boolean ac = acBox.isSelected();
                double rent = Double.parseDouble(rentField.getText().trim());

                boolean success = dao.addTradePoint(floor, area, ac, rent);
                if (success) {
                    statusLabel.setText("Точка добавлена!");
                    statusLabel.setStyle("-fx-text-fill: green;");
                    floorField.clear();
                    areaField.clear();
                    rentField.clear();
                    acBox.setSelected(false);
                    loadPoints();
                } else {
                    statusLabel.setText("Ошибка при добавлении.");
                    statusLabel.setStyle("-fx-text-fill: red;");
                }
            } catch (NumberFormatException e) {
                statusLabel.setText("Проверьте вводимые данные.");
                statusLabel.setStyle("-fx-text-fill: red;");
            }
        }
    }

    private class BackHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            mainView.showAdminPanelStartView(username);
        }
    }
}


