package service;

import model.Product;

import javax.jws.WebService;
import java.util.List;


public interface ProductService {
    List<Product> getListOfProducts();
    Product getProductByID(Integer id);
}
