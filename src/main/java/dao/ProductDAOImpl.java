package dao;

import model.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Реализует CRUD операции работы с базой данных сущности "Прайс-лист" (entity Product)
 * Использует контекст базы данных "myUnit"
 *
 * @author Rabadanov A.G.
 */
@Stateless
public class ProductDAOImpl implements ProductDAO {

    @PersistenceContext(unitName = "myUnit")
    private EntityManager em;

    public List<Product> getListOfProducts() {
        TypedQuery<Product> result = em.createNamedQuery("allProducts", Product.class);
        return result.getResultList();
    }

    @Override
    public Product getProductByID(Integer id) {
        TypedQuery<Product> result = em.createNamedQuery("productByID", Product.class);
        result.setParameter("id",id);
        return result.getSingleResult();
    }

    @Override
    public void addProduct(Product product) {
        em.persist(product);
    }

    @Override
    public void updateProduct(Product product) {
        em.merge(product);
        em.flush();
    }

    @Override
    public void removeProduct(Integer id) {
        Product product = getProductByID(id);
        if (product != null)
            em.remove(product);
    }
}
