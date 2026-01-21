package dao.impl;

import dao.ICustomerDAO;
import model.Customer;
import model.Product;
import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements ICustomerDAO {
    private final DBUtil dbUtil;

    public CustomerDAOImpl(DBUtil dbUtil) {
        this.dbUtil = dbUtil;
    }

    @Override
    public Boolean addCustomer(Customer customer) {
        String sql = "insert into customer(name, phone, email, address) values (?, ?, ?, ?)";

        try(Connection conn = dbUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){

            ps.setString(1, customer.getName());
            ps.setString(2, customer.getPhone());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getAddress());
            return ps.executeUpdate() > 0;
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Boolean updateCustomer(Customer customer) {
        String sql = "update customer set name=?, phone=?, email=?, address=? where id=?";
        try(Connection conn = dbUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getPhone());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getAddress());
            ps.setInt(5, customer.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean deleteCustomer(int id) {
        String sql = "delete from customer where id=?";
        try(Connection conn = dbUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        String sql = "select * from customer";
        List<Customer> customers = new ArrayList<>();
        try(Connection conn = dbUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {

            while(rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customer getCustomerById(int id) {
        String sql = "select * from customer where id=?";
        try(Connection conn = dbUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);)
        {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    Customer customer = new Customer();
                    customer.setId(rs.getInt("id"));
                    customer.setName(rs.getString("name"));
                    customer.setPhone(rs.getString("phone"));
                    customer.setEmail(rs.getString("email"));
                    customer.setAddress(rs.getString("address"));
                    return customer;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
