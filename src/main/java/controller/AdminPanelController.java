package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import view.MainView;

public class AdminPanelController {
    private final Button addPointButton;
    private final Button viewContractsButton;
    private final Button backButton;
    private final MainView mainView;
    private final String username;

    public AdminPanelController(Button addPointButton,
                                Button viewContractsButton,
                                Button backButton,
                                MainView mainView,
                                String username) {
        this.addPointButton = addPointButton;
        this.viewContractsButton = viewContractsButton;
        this.backButton = backButton;
        this.mainView = mainView;
        this.username = username;

        addPointButton.setOnAction(new AddPointHandler());
        viewContractsButton.setOnAction(new ViewContractsHandler());
        backButton.setOnAction(new BackHandler());
    }

    private class AddPointHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            mainView.showAddPointView(username);
        }
    }

    private class ViewContractsHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            mainView.showAllContractsView(username);
        }
    }

    private class BackHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            mainView.showStartView(); // или mainView.showSuccessView(username, "admin");
        }
    }
}

