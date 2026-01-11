package dao.impl;

import dao.IProductDAO;
import model.Product;
import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class ProductDAOImpl implements IProductDAO {
    DBUtil dbUtil = new DBUtil();
    Connection conn = dbUtil.getConnection();
    @Override
    public List<Product> findAllProducts() {
        return List.of();
    }

    @Override
    public Product findProductById(int id) {
        return null;
    }

    @Override
    public Boolean addProduct(Product product) {
        String sql = "insert into product (name, brand, price, stock) values(?,?,?,?)";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, product.getProductName());
            ps.setString(2, product.getBrand());
            ps.setBigDecimal(3, product.getProductPrice());
            ps.setInt(4, product.getStock());
            return  ps.executeUpdate() > 0;
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Boolean updateProduct(Product product) {
        return null;
    }

    @Override
    public Boolean deleteProduct(int id) {
        return null;
    }
}
