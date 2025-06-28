package controller;

import dao.ClientDAO;
import dao.UserDAO;
import model.Database;
import model.User;

public class AuthController {
    private final UserDAO userDAO;
    private final ClientDAO clientDAO;


    public AuthController() {
        this.userDAO = new UserDAO(Database.getInstance().getConnection());
        this.clientDAO = new ClientDAO(Database.getInstance().getConnection()); // ✅ добавлено

    }

    public User login(String username, String password) {
        return userDAO.authenticate(username, password);
    }

    public boolean registerWithClient(String login, String password, String role,
                                      String orgName, String address, String phone,
                                      String requisites, String contact) {
        if (userDAO.existsByLogin(login)) {
            return false;
        }

        int clientId = clientDAO.insertClient(orgName, address, phone, requisites, contact);
        if (clientId == -1) return false;

        return userDAO.insertUser(login, password, role, clientId);
    }
}
