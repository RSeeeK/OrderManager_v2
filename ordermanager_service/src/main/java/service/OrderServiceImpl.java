package service;

import entity.Order;
import repository.OrderDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Реализует сервисный уровень управления данными заказа (сущность Order)
 * Использует уровень доступа к данными OrderDAO
 *
 * @author Rabadanov A.G.
 */
@Stateless
public class OrderServiceImpl implements OrderService, Serializable {

    @Inject
    private OrderDAO orderDao;

    public void addOrder(Order order) {
        orderDao.addOrder(order);
    }

    public void updateOrder(Order order) {
        orderDao.updateOrder(order);
    }

    public void removeOrder(Integer id) {
        orderDao.removeOrder(id);
    }

    public Order getOrderById(Integer id) {
        return orderDao.getOrderById(id);
    }

    public List<Order> getListOfOrders() {
        return orderDao.getListOfOrders();
    }
}
