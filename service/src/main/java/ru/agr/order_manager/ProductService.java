package ru.agr.order_manager;

import ru.agr.order_manager.db.entity.Product;
import java.util.List;


public interface ProductService {
    List<Product> getListOfProducts();
    Product getProductByID(Integer id);
}
