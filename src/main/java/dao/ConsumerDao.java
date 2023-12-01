package dao;

import entity.Consumer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsumerDao extends AbstractDao<Consumer>{
    public ConsumerDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT * FROM Consumer";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO Consumer(userName, userSurname, userAddress) VALUES(?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE Consumer SET userName=?, userSurname=?, userAddress=?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM Consumer WHERE userId=?";
    }

    @Override
    protected List<Consumer> parseResultSet(ResultSet resultSet) {
        ArrayList<Consumer> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                result.add(new Consumer(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4))) ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Consumer entity) {
        try {
            statement.setString(1,entity.getUserName());
            statement.setString(2,entity.getUserSurname());
            statement.setString(3,entity.getAddress());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Consumer entity) {
        try {
            statement.setString(1,entity.getUserName());
            statement.setString(2,entity.getUserSurname());
            statement.setString(2,entity.getAddress());
            statement.setInt(2,entity.getUserId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
