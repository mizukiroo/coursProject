package view;

import controller.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView {
    private final Stage stage;
    private final AuthController authController;

    public MainView(Stage stage) {
        this.stage = stage;
        this.authController = new AuthController();
        showStartView();
    }

    public void showStartView() {
        StartController startController = new StartController(stage, this);
        stage.setScene(startController.getScene());
        stage.setTitle("Добро пожаловать");
        stage.setResizable(false);
        stage.setWidth(550);
        stage.setHeight(600);
        stage.show();
    }

    public void showLoginView() {
        LoginController loginController = new LoginController(stage, authController, this);
        stage.setScene(loginController.getScene());
        stage.setTitle("Авторизация");
        stage.setResizable(false);
        stage.setWidth(550);
        stage.setHeight(600);
        stage.show();
    }

    public void showRegisterView(String role) {
        RegisterController registerController = new RegisterController(stage, authController, role, this);
        stage.setTitle("Регистрация - " + role);
        stage.setScene(registerController.getScene());
        stage.setResizable(false);
        stage.setWidth(550);
        stage.setHeight(600);
        stage.show();
    }




    public void showSuccessView(String username, String role) {
        if (role.equals("admin")) {
            showAdminPanelStartView(username);
        } else {
            SuccessView view = new SuccessView(username, role, stage, this);
            stage.setScene(view.getScene());
        }
    }

    // ✅ Окно выбора: Добавить точку / Просмотр договоров / Назад
    public void showAdminPanelStartView(String username) {
        AdminPanelView view = new AdminPanelView(stage, username, this);
        stage.setScene(view.getScene());
        stage.setTitle("Администратор");
        stage.setResizable(false);
        stage.setWidth(550);
        stage.setHeight(600);
        stage.show();
    }

    // ✅ Окно добавления торговой точки
    public void showAddPointView(String username) {
        AddTradePointView view = new AddTradePointView(stage, username, this);
        stage.setScene(view.getScene());
        stage.setTitle("Добавить торговую точку");
        stage.setResizable(false);
        stage.setWidth(550);
        stage.setHeight(600);
        stage.show();
    }

    public void showAllContractsView(String username) {
        AllContractsView view = new AllContractsView(stage, username, this);
        stage.setScene(view.getScene());
        stage.setTitle("Все договоры");
        stage.setResizable(false);
        stage.setWidth(550);
        stage.setHeight(600);
        stage.show();
    }

    public void showProfileView(String username) {
        ClientProfileView profileView = new ClientProfileView(stage, username);
        stage.setScene(profileView.getScene());
        stage.setTitle("Личный кабинет");
        stage.setResizable(false);
        stage.setWidth(550);
        stage.setHeight(600);
        stage.show();
    }

//    public void showPaymentView(String username) {
//        PaymentView paymentView = new PaymentView(stage, username, this);
//        stage.setTitle("Регистрация - ");
//        stage.setScene(paymentView.getScene());
//        stage.setResizable(false);
//        stage.setWidth(550);
//        stage.setHeight(600);
//        stage.show();
//    }


    public void showLeaseView(String username) {
        LeaseView leaseView = new LeaseView(stage, username, this);
        stage.setScene(leaseView.getScene());
        stage.setTitle("Оформление аренды");
        stage.setResizable(false);
        stage.setWidth(550);
        stage.setHeight(600);
        stage.show();
    }

    public void showMyLeasesView(String username) {
        MyLeasesView view = new MyLeasesView(stage, username, this);
        stage.setScene(view.getScene());
        stage.setTitle("Мои аренды");
        stage.setResizable(false);
        stage.setWidth(550);
        stage.setHeight(600);
        stage.show();
    }
}





//    public void showProfileView(String username) {
//        System.out.println("Открыт личный кабинет: " + username);
//    }

//    public void showLeaseView(String username) {
//        System.out.println("Открыта форма аренды для: " + username);
//    }

//    public void showMyLeasesView(String username) {
//        System.out.println("Просмотр аренд для: " + username);
//    }
//}
