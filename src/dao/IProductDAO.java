package dao;

import model.Product;

import java.util.List;

public interface IProductDAO {
    List<Product> findAllProducts();
    Product findProductById(int id);
    Boolean addProduct(Product product);
    Boolean updateProduct(Product product);
    Boolean deleteProduct(int id);
}
