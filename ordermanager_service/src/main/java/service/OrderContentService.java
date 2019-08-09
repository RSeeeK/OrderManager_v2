package service;

import entity.Order;
import entity.OrderContent;


import java.util.List;


public interface OrderContentService {
    void addOrderContent(OrderContent orderContent);
    void updateOrderContent(OrderContent orderContent);
    void removeOrderContent(Integer id);
    OrderContent getOrderContentById(Integer id);
    List<OrderContent> getListOfOrdersContent();
    List<OrderContent> getOrdersContentByOrder(Order order);
}
