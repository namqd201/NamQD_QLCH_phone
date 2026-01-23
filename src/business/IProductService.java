package business;

import model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface IProductService {
    Boolean addProduct(Product product);

    Boolean updateProduct(Product product);

    Boolean deleteProduct(int id);

    Product findById(int id);

    List<Product> findAll();

    List<Product> findByBrand(String brand);

    List<Product> findByPriceRange(BigDecimal min, BigDecimal max);

    List<Product> findByName(String name);
}
