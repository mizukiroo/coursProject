package controller;

import dao.ContractDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Contract;
import model.Database;
import view.MainView;

import java.util.List;

public class MyLeasesController {
    private final VBox container;
    private final Button backButton;
    private final MainView mainView;
    private final String username;

    public MyLeasesController(String username, VBox container, Button backButton, MainView mainView) {
        this.username = username;
        this.container = container;
        this.backButton = backButton;
        this.mainView = mainView;

        loadContracts();
        backButton.setOnAction(new BackHandler());
    }

    private void loadContracts() {
        ContractDAO contractDAO = new ContractDAO(Database.getInstance().getConnection());
        List<Contract> contracts = contractDAO.getContractsByUsername(username);

        for (Contract contract : contracts) {
            String info = String.format("""
                    Договор №%d
                    Дата начала: %s
                    Дата окончания: %s
                    Точка №%d (этаж: %d, площадь: %.2f м², кондиционер: %s)
                    Цена: %.2f₽/день
                    -----------------------------
                    """,
                    contract.getId(),
                    contract.getStartDate(),
                    contract.getEndDate(),
                    contract.getPointId(),
                    contract.getFloor(),
                    contract.getArea(),
                    contract.isAirConditioner() ? "Да" : "Нет",
                    contract.getRent()
            );

            Label label = new Label(info);
            label.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 14px;");
            container.getChildren().add(label);
        }
    }

    private class BackHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            mainView.showSuccessView(username, "client");
        }
    }
}

