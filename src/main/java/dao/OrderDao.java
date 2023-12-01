package dao;

import entity.Order;
import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends AbstractDao<Order>{
    public OrderDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT * FROM Ord";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO Ord (userId, orderDate) VALUES (?,?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE Ord SET userId=?, orderDate=? WHERE orderId=?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM Ord WHERE orderId=?";
    }


    @Override
    protected List<Order> parseResultSet(ResultSet resultSet) {
        ArrayList<Order> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                result.add(new Order(resultSet.getInt("userId"),resultSet.getDate("orderDate"))) ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Order entity) {
        try {
            statement.setInt(1,entity.getUserId());
            statement.setDate(2,entity.getOrderDate());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Order entity) {
        try {
            statement.setInt(1,entity.getUserId());
            statement.setDate(2,entity.getOrderDate());
            statement.setInt(3,entity.getOrderId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
