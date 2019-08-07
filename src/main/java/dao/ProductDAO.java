package dao;

import model.Product;

import java.util.List;

public interface ProductDAO {

    void addProduct(Product product);
    void updateProduct(Product product);
    void removeProduct(Integer id);
    Product getProductByID(Integer id);
    List<Product> getListOfProducts();

}
