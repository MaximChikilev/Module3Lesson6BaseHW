package entity;

public class GoodsInOrder {
    private int odderId;
    private int productId;
    private int quantity;
    private Double price;

    public GoodsInOrder(int odderId, int productId, int quantity, Double price) {
        this.odderId = odderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getOdderId() {
        return odderId;
    }

    public void setOdderId(int odderId) {
        this.odderId = odderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
