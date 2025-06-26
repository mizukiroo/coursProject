package controller;

import dao.AllContractsDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.ContractInfo;
import model.Database;
import view.MainView;

import java.util.List;

public class AllContractsController {
    private final VBox contractBox;
    private final Button backButton;
    private final MainView mainView;
    private final String username;
    private final AllContractsDAO dao;

    public AllContractsController(VBox contractBox, Button backButton, MainView mainView, String username) {
        this.contractBox = contractBox;
        this.backButton = backButton;
        this.mainView = mainView;
        this.username = username;
        this.dao = new AllContractsDAO(Database.getInstance().getConnection());

        loadContracts();
        backButton.setOnAction(new BackHandler());
    }

    private void loadContracts() {
        List<ContractInfo> contracts = dao.getAllContracts();
        contractBox.getChildren().clear();

        for (ContractInfo c : contracts) {
            Label label = new Label(String.format(
                    "Пользователь: %s | С %s по %s | Этаж: %d | Площадь: %.2f м² | Цена в день: %.2f",
                    c.getUsername(), c.getStartDate(), c.getEndDate(), c.getFloor(), c.getArea(), c.getDailyRent()
            ));
            label.setStyle("-fx-font-size: 14px; -fx-padding: 5px;");
            contractBox.getChildren().add(label);
        }
    }

    private class BackHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            mainView.showAdminPanelStartView(username);
        }
    }
}
