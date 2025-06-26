package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import view.MainView;

public class SuccessController {
    private final MainView mainView;
    private final String username;

    public SuccessController(MainView mainView, String username,
                             Button profileButton, Button leaseButton, Button myLeasesButton, Button logoutButton) {
        this.mainView = mainView;
        this.username = username;

        profileButton.setOnAction(new ProfileButtonHandler());
        leaseButton.setOnAction(new LeaseButtonHandler());
        myLeasesButton.setOnAction(new MyLeasesButtonHandler());
        logoutButton.setOnAction(new LogoutButtonHandler());
    }

    private class ProfileButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            mainView.showProfileView(username);
        }
    }

    private class LeaseButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            mainView.showLeaseView(username);
        }
    }

    private class MyLeasesButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            mainView.showMyLeasesView(username);
        }
    }

    private class LogoutButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            mainView.showStartView();
        }
    }
}

