package ru.agr.order_manager;

import ru.agr.order_manager.db.entity.Product;
import ru.agr.order_manager.db.repository.ProductDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Реализует сервисный уровень управления данными прайс-листа (сущность Product)
 * Использует уровень доступа к данными ProductDAO
 *
 * @author Rabadanov A.G.
 */
@Stateless
public class ProductServiceImpl implements ProductService, Serializable {

    @Inject
    private ProductDAO productDAO;

    public List<Product> getListOfProducts() {
        return productDAO.getListOfProducts();
    }

    @Override
    public Product getProductByID(Integer id) {
        return productDAO.getProductByID(id);
    }


}
