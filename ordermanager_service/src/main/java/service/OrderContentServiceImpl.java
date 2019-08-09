package service;

import entity.Order;
import entity.OrderContent;
import repository.OrderContentDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;


/**
* Реализует сервисный уровень управления составом заказов (сущность OrderContent),
* Использует уровень доступа к данными OrderContentDAO.
*
* @author Rabadanov A.G.
*/
@Stateless
public class OrderContentServiceImpl implements OrderContentService, Serializable {

    @Inject
    private OrderContentDAO orderContentDAO;

    public void addOrderContent(OrderContent orderContent) {
        orderContentDAO.addOrderContent(orderContent);
    }

    public void updateOrderContent(OrderContent orderContent) {
        orderContentDAO.updateOrderContent(orderContent);
    }

    public void removeOrderContent(Integer id) {
        orderContentDAO.removeOrderContent(id);
    }

    public OrderContent getOrderContentById(Integer id) {
        return orderContentDAO.getOrderContentById(id);
    }

    public List<OrderContent> getListOfOrdersContent() {
        return orderContentDAO.getOrdersContent();
    }

    public List<OrderContent> getOrdersContentByOrder(Order order) {
        return orderContentDAO.getOrdersContentByOrder(order);
    }
}
