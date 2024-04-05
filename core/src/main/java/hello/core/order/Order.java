package hello.core.order;

public class Order {
    private long id;
    private String productName;
    private int productPrice;
    private int discountPrice;

    public Order(long id, String productName, int productPrice, int discountPrice) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.discountPrice = discountPrice;
    }

    public int calculate() {
        return productPrice - discountPrice;
    }

    public long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", prodcutPrice=" + productPrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
