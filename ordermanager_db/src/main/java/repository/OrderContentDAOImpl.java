package repository;



import entity.Order;
import entity.OrderContent;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Реализует CRUD операции работы с базой данных сущности "Состав заказа" (entity OrderContent)
 * Использует контекст базы данных "myUnit"
 *
 * @author Rabadanov A.G.
 */
@Stateless
public class OrderContentDAOImpl implements OrderContentDAO {

    @PersistenceContext(unitName = "myUnit")
    private EntityManager em;

    public void addOrderContent(OrderContent orderContent) {
        em.persist(orderContent);
    }

    public void updateOrderContent(OrderContent orderContent) {
        em.merge(orderContent);
    }

    public void removeOrderContent(Integer id) {
        em.remove(em.find(OrderContent.class, id));
    }

    public OrderContent getOrderContentById(Integer id) {
        return em.find(OrderContent.class, id);
    }

    public List<OrderContent> getOrdersContent() {
        TypedQuery<OrderContent> result = em.createNamedQuery("allOrdersContent", OrderContent.class);
        return result.getResultList();
    }

    public List<OrderContent> getOrdersContentByOrder(Order order) {
        if (order.getId() == null) {
            return new ArrayList<>();
        }
        TypedQuery<OrderContent> result = em.createNamedQuery("allOrdersContentByOrder", OrderContent.class);
        result.setParameter("order", order);
        return result.getResultList();
    }
}
