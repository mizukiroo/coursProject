package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import view.MainView;

public class SuccessController {

    public SuccessController(MainView mainView, String username,
                             Button profileButton,
                             Button leaseButton,
                             Button myLeasesButton,
                             Button paymentButton,
                             Button logoutButton) {

        profileButton.setOnAction(new ProfileHandler(mainView, username));
        leaseButton.setOnAction(new LeaseHandler(mainView, username));
        myLeasesButton.setOnAction(new MyLeasesHandler(mainView, username));
        paymentButton.setOnAction(new PaymentHandler(mainView, username));
        logoutButton.setOnAction(new LogoutHandler(mainView));
    }

    private static class ProfileHandler implements EventHandler<ActionEvent> {
        private final MainView mainView;
        private final String username;

        public ProfileHandler(MainView mainView, String username) {
            this.mainView = mainView;
            this.username = username;
        }

        @Override
        public void handle(ActionEvent event) {
            mainView.showProfileView(username);
        }
    }

    private static class LeaseHandler implements EventHandler<ActionEvent> {
        private final MainView mainView;
        private final String username;

        public LeaseHandler(MainView mainView, String username) {
            this.mainView = mainView;
            this.username = username;
        }

        @Override
        public void handle(ActionEvent event) {
            mainView.showLeaseView(username);
        }
    }

    private static class MyLeasesHandler implements EventHandler<ActionEvent> {
        private final MainView mainView;
        private final String username;

        public MyLeasesHandler(MainView mainView, String username) {
            this.mainView = mainView;
            this.username = username;
        }

        @Override
        public void handle(ActionEvent event) {
            mainView.showMyLeasesView(username);
        }
    }

    private static class PaymentHandler implements EventHandler<ActionEvent> {
        private final MainView mainView;
        private final String username;

        public PaymentHandler(MainView mainView, String username) {
            this.mainView = mainView;
            this.username = username;
        }

        @Override
        public void handle(ActionEvent event) {
            mainView.showPaymentView(username);
        }
    }

    private static class LogoutHandler implements EventHandler<ActionEvent> {
        private final MainView mainView;

        public LogoutHandler(MainView mainView) {
            this.mainView = mainView;
        }

        @Override
        public void handle(ActionEvent event) {
            mainView.showLoginView();
        }
    }
}

