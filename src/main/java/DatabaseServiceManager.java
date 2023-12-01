import dao.ConsumerDao;
import dao.GoodsInOrderDao;
import dao.OrderDao;
import dao.ProductDao;
import entity.Consumer;
import entity.GoodsInOrder;
import entity.Order;
import entity.Product;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class DatabaseServiceManager {
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/mydb?serverTimezone=Europe/Kiev";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Password";
    private static Connection connection;
    private ConsumerDao consumerDao;
    private OrderDao orderDao;
    private ProductDao productDao;
    private GoodsInOrderDao goodsInOrderDao;

    public DatabaseServiceManager() {
        try {
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            consumerDao = new ConsumerDao(connection);
            orderDao = new OrderDao(connection);
            productDao = new ProductDao(connection);
            goodsInOrderDao = new GoodsInOrderDao(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public void createDB() throws SQLException {
        Statement statement = connection.createStatement();
        try {

            statement.execute("CREATE TABLE IF NOT EXISTS Consumer(" +
                    "userId INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "userName VARCHAR(20) NOT NULL," +
                    "userSurname VARCHAR(20) NOT NULL," +
                    "userAddress VARCHAR(20)" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS Product(" +
                    "productId INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "productName VARCHAR(50) NOT NULL" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS Ord(" +
                    "orderId INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "userId INT NOT NULL," +
                    "orderDate Date NOT NULL" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS GoodsInOrder(" +
                    "orderId INT NOT NULL," +
                    "productId INT NOT NULL," +
                    "quantity INT NOT NULL," +
                    "price DOUBLE NOT NULL" +
                    ")");
        } finally {
            statement.close();
        }
    }

    private void clearTable(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fillProductTable() {
        clearTable("TRUNCATE TABLE Product");
        productDao.create(new Product("Perfect iron Tefal 2315"));
        productDao.create(new Product("Magnificent iron Philips 5555"));
        productDao.create(new Product("Magnificent TV Sony 2315"));
        productDao.create(new Product("Perfect TV LG 2315"));
        productDao.create(new Product("Perfect TV Samsung 2315"));
        productDao.create(new Product("Magnificent Smartphone Apple 2315"));
        productDao.create(new Product("Perfect Smartphone Samsung 2315"));
        productDao.create(new Product("Perfect Smartphone OPPO 2315"));
        productDao.create(new Product("Perfect Smartphone Huawei 2315"));
        System.out.println("Table product was filled");
    }

    public void fillConsumerTable() {
        clearTable("TRUNCATE TABLE Consumer");
        consumerDao.create(new Consumer("User1", "Surname1", "Address 1"));
        consumerDao.create(new Consumer("User2", "Surname2", "Address 2"));
        consumerDao.create(new Consumer("User3", "Surname3", "Address 3"));
        consumerDao.create(new Consumer("User4", "Surname4", "Address 4"));
        consumerDao.create(new Consumer("User5", "Surname5", "Address 5"));
        consumerDao.create(new Consumer("User6", "Surname6", "Address 6"));
        consumerDao.create(new Consumer("User7", "Surname7", "Address 7"));
        consumerDao.create(new Consumer("User8", "Surname8", "Address 8"));
        consumerDao.create(new Consumer("User9", "Surname9", "Address 9"));
        System.out.println("Table consumer was filled");
    }

    public void fillOrderTable() {
        java.sql.Date date = new java.sql.Date(new Date().getTime());
        clearTable("TRUNCATE TABLE Ord");
        orderDao.create(new Order(1, date));
        orderDao.create(new Order(1, date));
        orderDao.create(new Order(1, date));
        orderDao.create(new Order(2, date));
        orderDao.create(new Order(2, date));
        orderDao.create(new Order(2, date));
        orderDao.create(new Order(2, date));
        orderDao.create(new Order(3, date));
        orderDao.create(new Order(3, date));
        orderDao.create(new Order(4, date));
        orderDao.create(new Order(4, date));
        orderDao.create(new Order(4, date));
        orderDao.create(new Order(5, date));
        orderDao.create(new Order(6, date));
        orderDao.create(new Order(7, date));
        orderDao.create(new Order(8, date));
        orderDao.create(new Order(8, date));
        orderDao.create(new Order(9, date));
        System.out.println("Table order was filled");
    }

    public void fillGoodsInOrder() {
        clearTable("TRUNCATE TABLE GoodsInOrder");
        goodsInOrderDao.create(new GoodsInOrder(1, 1, 5, 10.5));
        goodsInOrderDao.create(new GoodsInOrder(1, 2, 6, 10.5));
        goodsInOrderDao.create(new GoodsInOrder(1, 3, 7, 10.5));
        goodsInOrderDao.create(new GoodsInOrder(2, 4, 2, 10.5));
        goodsInOrderDao.create(new GoodsInOrder(2, 5, 3, 10.5));
        goodsInOrderDao.create(new GoodsInOrder(3, 6, 4, 10.5));
        goodsInOrderDao.create(new GoodsInOrder(4, 7, 5, 10.5));
        goodsInOrderDao.create(new GoodsInOrder(4, 8, 6, 10.5));
        goodsInOrderDao.create(new GoodsInOrder(5, 9, 7, 10.5));
        goodsInOrderDao.create(new GoodsInOrder(5, 8, 8, 10.5));
        goodsInOrderDao.create(new GoodsInOrder(5, 7, 9, 10.5));
        goodsInOrderDao.create(new GoodsInOrder(6, 6, 8, 10.5));
        goodsInOrderDao.create(new GoodsInOrder(7, 5, 7, 10.5));
        goodsInOrderDao.create(new GoodsInOrder(8, 4, 8, 10.5));
        goodsInOrderDao.create(new GoodsInOrder(9, 3, 5, 10.5));
        System.out.println("Table GoodsInOrder was filled");
    }

    public void showAllConsumersOrders() {
        List<Consumer> consumers = consumerDao.getAll();
        List<Product> products = productDao.getAll();
        List<Order> orders = orderDao.getAll();
        for (Consumer consumer : consumers) {
            System.out.println(consumer);
            try {
                String sql = "SELECT  y.orderid, y.productid, y.quantity, y.price, x.orderdate FROM\n" +
                        "(SELECT goodsinorder.orderid, productid,quantity,price FROM goodsinorder) y RIGHT JOIN(\n" +
                        "SELECT ord.orderId, userId, orderDate FROM ord WHERE userId=?) x ON y.orderId=x.orderId WHERE y.orderid IS NOT NULL";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, consumer.getUserId());
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    System.out.println("Order id : " + resultSet.getString(1)+
                            " ProductId : "+resultSet.getString(2)+
                            " Quantity : "+resultSet.getString(3)+
                            " Price : "+resultSet.getString(4)+
                            " Date : "+resultSet.getString(5)
                            );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("----------------------------------------------------------------------------------------------");
        }

    }
}
