package ru.agr.order_manager.service;

import ru.agr.order_manager.db.entity.Product;
import java.util.List;


public interface ProductService {
    List<Product> getListOfProducts();
    Product getProductByID(Integer id);
}
