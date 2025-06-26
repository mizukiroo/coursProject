package controller;

import dao.ContractDAO;
import dao.TradePointDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import model.Database;
import model.TradePoint;
import view.MainView;

import java.time.temporal.ChronoUnit;
import java.util.List;

public class LeaseController {
    private final MainView mainView;
    private final String username;
    private final TradePointDAO tradePointDAO;
    private final ContractDAO contractDAO;

    private ComboBox<TradePoint> pointComboBox;
    private DatePicker startDatePicker;
    private DatePicker endDatePicker;
    private Label costLabel;
    private Label statusLabel;
    private Button confirmButton;
    private Button backButton;

    public LeaseController(MainView mainView, String username) {
        this.mainView = mainView;
        this.username = username;
        this.tradePointDAO = new TradePointDAO(Database.getInstance().getConnection());
        this.contractDAO = new ContractDAO(Database.getInstance().getConnection());
    }

    public void attachHandlers(ComboBox<TradePoint> pointComboBox,
                               DatePicker startDatePicker,
                               DatePicker endDatePicker,
                               Label costLabel,
                               Label statusLabel,
                               Button confirmButton,
                               Button backButton) {
        this.pointComboBox = pointComboBox;
        this.startDatePicker = startDatePicker;
        this.endDatePicker = endDatePicker;
        this.costLabel = costLabel;
        this.statusLabel = statusLabel;
        this.confirmButton = confirmButton;
        this.backButton = backButton;

        loadPoints();
        confirmButton.setOnAction(new ConfirmHandler());
        backButton.setOnAction(new BackHandler());

        startDatePicker.valueProperty().addListener((obs, oldVal, newVal) -> updateCost());
        endDatePicker.valueProperty().addListener((obs, oldVal, newVal) -> updateCost());
        pointComboBox.valueProperty().addListener((obs, oldVal, newVal) -> updateCost());
    }

    private void updateCost() {
        TradePoint point = pointComboBox.getValue();
        if (point == null || startDatePicker.getValue() == null || endDatePicker.getValue() == null) {
            costLabel.setText("Стоимость: -");
            return;
        }

        long days = ChronoUnit.DAYS.between(startDatePicker.getValue(), endDatePicker.getValue()) + 1;
        if (days <= 0) {
            costLabel.setText("Неверный диапазон дат");
            return;
        }

        double cost = point.getDailyRent() * days;
        costLabel.setText("Стоимость: " + cost + " руб.");
    }



    private void loadPoints() {
        List<TradePoint> points = tradePointDAO.getAllPoints();
        pointComboBox.getItems().addAll(points);
    }

    private class ConfirmHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            TradePoint point = pointComboBox.getValue();
            if (point == null || startDatePicker.getValue() == null || endDatePicker.getValue() == null) {
                statusLabel.setText("Заполните все поля");
                return;
            }

            long days = ChronoUnit.DAYS.between(startDatePicker.getValue(), endDatePicker.getValue()) + 1;
            if (days <= 0) {
                statusLabel.setText("Неверный диапазон дат");
                return;
            }

            double cost = point.getDailyRent() * days;
            costLabel.setText("Стоимость: " + cost + " руб.");

            boolean ok = contractDAO.createContractWithPoint(username, point.getId(), startDatePicker.getValue(), endDatePicker.getValue());
            if (ok) {
                statusLabel.setText("Аренда оформлена");
            } else {
                statusLabel.setText("Ошибка при создании аренды");
            }
        }
    }

    private class BackHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            mainView.showSuccessView(username, "client");
        }
    }
}
