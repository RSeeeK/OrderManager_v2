package service;

import entity.Product;
import java.util.List;


public interface ProductService {
    List<Product> getListOfProducts();
    Product getProductByID(Integer id);
}
