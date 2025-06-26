package dao;

import model.ContractInfo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AllContractsDAO {
    private final Connection connection;

    public AllContractsDAO(Connection connection) {
        this.connection = connection;
    }

    public List<ContractInfo> getAllContracts() {
        List<ContractInfo> list = new ArrayList<>();

        String sql = """
            SELECT cl.name AS client_name, c.start_date, c.end_date,
                   tp.floor, tp.area, tp.daily_rent
            FROM contracts c
            JOIN clients cl ON c.client_id = cl.client_id
            JOIN contract_trade_point ctp ON c.id = ctp.contract_id
            JOIN trade_points tp ON ctp.point_id = tp.id
            ORDER BY c.start_date DESC
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new ContractInfo(
                        rs.getString("client_name"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate(),
                        rs.getInt("floor"),
                        rs.getDouble("area"),
                        rs.getDouble("daily_rent")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при загрузке договоров: " + e.getMessage());
        }

        return list;
    }
}
