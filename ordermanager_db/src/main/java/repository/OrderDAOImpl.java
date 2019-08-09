package repository;

import entity.Order;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Реализует CRUD операции работы с базой данных сущности "Заказ" (entity Order)
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

    public List<Order> getListOfOrders() {
        TypedQuery<Order> result = em.createNamedQuery("allOrders", Order.class);
        return result.getResultList();
    }
}
