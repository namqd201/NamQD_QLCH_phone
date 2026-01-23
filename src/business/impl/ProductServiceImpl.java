package business.impl;

import business.IProductService;
import dao.impl.ProductDAOImpl;
import model.Product;

import java.math.BigDecimal;
import java.util.List;

public class ProductServiceImpl implements IProductService {
    private final ProductDAOImpl productDAO;

    public ProductServiceImpl(ProductDAOImpl productDAO) {
        this.productDAO = productDAO;
    }
    @Override
    public Boolean addProduct(Product product) {
        return productDAO.addProduct(product);
    }

    @Override
    public Boolean updateProduct(Product product) {
        if(productDAO.findProductById(product.getProductId())==null){
            return false;
        }
        return productDAO.updateProduct(product);
    }

    @Override
    public Boolean deleteProduct(int id) {
        if(productDAO.findProductById(id)==null){
            return false;
        }
        return productDAO.deleteProduct(id);
    }

    @Override
    public Product findById(int id) {
        return productDAO.findProductById(id);
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAllProducts();
    }

    @Override
    public List<Product> findByBrand(String brand) {
        return productDAO.findAllProductsByBrand(brand);
    }

    @Override
    public List<Product> findByPriceRange(BigDecimal min, BigDecimal max) {
        return productDAO.findAllProductsByPrice(min,max);
    }

    @Override
    public List<Product> findByName(String name) {
        return productDAO.findAllProductsByName(name);
    }

    public BigDecimal findPriceById(int id) {
        return productDAO.getPriceById(id);
    }
}
