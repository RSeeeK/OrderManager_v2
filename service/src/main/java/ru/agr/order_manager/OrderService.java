package ru.agr.order_manager;

import ru.agr.order_manager.db.entity.Order;

import java.util.List;

public interface OrderService {
    void addOrder(Order order);
    void updateOrder(Order order);
    void removeOrder(Integer id);
    Order getOrderById(Integer id);
    Order getOrderByEmail(String email);
    List<Order> getListOfOrders();
}
