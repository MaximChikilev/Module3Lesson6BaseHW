package dao;

import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao extends AbstractDao<Product>{
    public ProductDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT * FROM Product";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO Product(productName) VALUES(?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE Product SET productName=? WHERE productId=?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM Product WHERE productId=?";
    }

    @Override
    protected List<Product> parseResultSet(ResultSet resultSet) {
        ArrayList<Product> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                result.add(new Product(resultSet.getInt("productId"),resultSet.getString("productName"))) ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Product entity) {
        try {
            statement.setString(1,entity.getProductName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Product entity) {
        try {
            statement.setString(1,entity.getProductName());
            statement.setDouble(2,entity.getProductId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
