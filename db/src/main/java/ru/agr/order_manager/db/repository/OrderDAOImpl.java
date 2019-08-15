package ru.agr.order_manager.db.repository;

import ru.agr.order_manager.db.entity.Order;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Реализует CRUD операции работы с базой данных сущности "Заказ" (ru.agr.ordermanager.entity Order)
 * Использует контекст базы данных "myUnit"
 *
 * @author Rabadanov A.G.
 */
@Stateless
public class OrderDAOImpl implements OrderDAO {

    @PersistenceContext(unitName = "myUnit")
    private EntityManager em;

    public void addOrder(Order order) {
        em.persist(order);
    }

    public void updateOrder(Order order) {
        em.merge(order);
    }

    public void removeOrder(Integer id) {
        Order order = getOrderById(id);

        if (order != null) {
            em.remove(order);
        }
    }

    public Order getOrderById(Integer id) {
        return em.find(Order.class, id);
    }

    @Override
    public Order getOrderByNumber(String number) {
        TypedQuery<Order> query = em.createQuery("SELECT o FROM ORDERS o where o.number = :number", Order.class);
        query.setParameter("number",number);
        List<Order> orders = query.getResultList();
        return (orders.isEmpty()) ? null : orders.get(0);
    }

    @Override
    public Order getOrderByEmail(String email) {
        TypedQuery<Order> query = em.createQuery("SELECT o FROM ORDERS o where o.customerEmail = :customeremail", Order.class);
        query.setParameter("customeremail",email);
        List<Order> orders = query.getResultList();
        return (orders.isEmpty()) ? null : orders.get(0);
    }

    public List<Order> getListOfOrders() {
        TypedQuery<Order> result = em.createNamedQuery("allOrders", Order.class);
        return result.getResultList();
    }
}
