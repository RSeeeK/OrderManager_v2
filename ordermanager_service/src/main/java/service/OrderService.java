package service;

import entity.Order;

import java.util.List;

public interface OrderService {
    void addOrder(Order order);
    void updateOrder(Order order);
    void removeOrder(Integer id);
    Order getOrderById(Integer id);
    List<Order> getListOfOrders();
}
