package dao;

import entity.GoodsInOrder;
import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsInOrderDao extends AbstractDao<GoodsInOrder> {
    public GoodsInOrderDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT * FROM goodsinorder";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO goodsinorder(orderId, productId, quantity, price) VALUES(?,?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE goodsinorder SET quantity=?, price=? WHERE orderId=? AND productId=?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM goodsinorder WHERE orderId=?";
    }

    @Override
    protected List<GoodsInOrder> parseResultSet(ResultSet resultSet) {
        ArrayList<GoodsInOrder> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                result.add(new GoodsInOrder(resultSet.getInt("orderId"), resultSet.getInt("productId"), resultSet.getInt("quantity"), resultSet.getDouble("price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, GoodsInOrder entity) {
        try {
            statement.setInt(1, entity.getOdderId());
            statement.setInt(2, entity.getProductId());
            statement.setInt(3, entity.getQuantity());
            statement.setDouble(4, entity.getPrice());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, GoodsInOrder entity) {
        try {
            statement.setInt(3, entity.getOdderId());
            statement.setInt(4, entity.getProductId());
            statement.setInt(1, entity.getQuantity());
            statement.setDouble(2, entity.getPrice());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
