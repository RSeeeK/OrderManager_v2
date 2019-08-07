package service;

import model.Order;

import javax.jws.WebService;
import java.util.List;

public interface OrderService {
    void addOrder(Order order);
    void updateOrder(Order order);
    void removeOrder(Integer id);
    Order getOrderById(Integer id);
    List<Order> getListOfOrders();
}
