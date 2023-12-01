import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DatabaseServiceManager databaseServiceManager = new DatabaseServiceManager();
        try {
           databaseServiceManager.createDB();
            //databaseServiceManager.fillProductTable();
            //databaseServiceManager.fillConsumerTable();
            //databaseServiceManager.fillOrderTable();
            //databaseServiceManager.fillGoodsInOrder();
            databaseServiceManager.showAllConsumersOrders();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
