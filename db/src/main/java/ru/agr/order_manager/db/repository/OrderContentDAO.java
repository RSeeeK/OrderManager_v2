package ru.agr.order_manager.db.repository;

import ru.agr.order_manager.db.entity.Order;
import ru.agr.order_manager.db.entity.OrderContent;

import java.util.List;

public interface OrderContentDAO {
    void addOrderContent(OrderContent orderContent);
    void updateOrderContent(OrderContent orderContent);
    void removeOrderContent(Integer id);
    OrderContent getOrderContentById(Integer id);
    List<OrderContent> getOrdersContent();
    List<OrderContent> getOrdersContentByOrder(Order order);
}
