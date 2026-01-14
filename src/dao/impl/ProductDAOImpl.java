package dao.impl;

import dao.IProductDAO;
import model.Product;
import utils.DBUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements IProductDAO {
    DBUtil dbUtil = new DBUtil();
    Connection conn = dbUtil.getConnection();
    @Override
    public List<Product> findAllProducts() {
        String sql = "select * from product";
        List<Product> products = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("id"));
                product.setProductName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setProductPrice(rs.getBigDecimal("price"));
                product.setStock(rs.getInt("stock"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public Product findProductById(int id) {
        String sql = "select * from product where id = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("id"));
                product.setProductName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setProductPrice(rs.getBigDecimal("price"));
                product.setStock(rs.getInt("stock"));
                return product;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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
        String sql = "update product set name = ?, brand = ?, price = ?, stock = ? where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, product.getProductName());
            ps.setString(2, product.getBrand());
            ps.setBigDecimal(3, product.getProductPrice());
            ps.setInt(4, product.getStock());
            ps.setInt(5, product.getProductId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean deleteProduct(int id) {
        String sql = "delete from product where id = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> findAllProductsByBrand(String brand) {
        String sql = "SELECT * FROM product WHERE brand ILIKE ?";
        List<Product> products = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + brand + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("id"));
                product.setProductName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setProductPrice(rs.getBigDecimal("price"));
                product.setStock(rs.getInt("stock"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> findAllProductsByPrice(BigDecimal minPrice, BigDecimal maxPrice) {
        String sql = "SELECT * FROM product WHERE price BETWEEN ? AND ?";
        List<Product> products = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1, minPrice);
            ps.setBigDecimal(2, maxPrice);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("id"));
                product.setProductName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setProductPrice(rs.getBigDecimal("price"));
                product.setStock(rs.getInt("stock"));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}
