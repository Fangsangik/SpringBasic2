package hello.core.order;

public interface OrderService {
    Order createOrder(Long id, String productName, int productPrice);
}
