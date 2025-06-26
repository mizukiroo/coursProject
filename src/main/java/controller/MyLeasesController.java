package controller;

import dao.MyLeaseDAO;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Database;
import model.MyLease;
import view.MainView;

import java.util.List;

public class MyLeasesController {
    private final String username;
    private final VBox leaseListBox;
    private final Button backButton;
    private final MainView mainView;

    public MyLeasesController(String username, VBox leaseListBox, Button backButton, MainView mainView) {
        this.username = username;
        this.leaseListBox = leaseListBox;
        this.backButton = backButton;
        this.mainView = mainView;

        backButton.setOnAction(e -> mainView.showSuccessView(username, "client"));
        loadLeases();
    }

    private void loadLeases() {
        MyLeaseDAO dao = new MyLeaseDAO(Database.getInstance().getConnection());
        List<MyLease> leases = dao.getLeasesByUsername(username);

        leaseListBox.getChildren().clear();
        if (leases.isEmpty()) {
            leaseListBox.getChildren().add(new Label("Нет аренд"));
            return;
        }

        for (MyLease lease : leases) {
            Label label = new Label(
                    String.format("С %s по %s | Этаж: %d | Площадь: %.2f м² | Сумма: %.2f руб.",
                            lease.getStartDate(), lease.getEndDate(),
                            lease.getFloor(), lease.getArea(), lease.getTotalCost())
            );
            label.setStyle("-fx-font-size: 16px;");
            leaseListBox.getChildren().add(label);
        }
    }
}
