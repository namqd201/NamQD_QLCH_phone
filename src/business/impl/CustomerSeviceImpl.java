package business.impl;

import business.ICustomerService;
import dao.impl.CustomerDAOImpl;
import model.Customer;

import java.util.List;

public class CustomerSeviceImpl implements ICustomerService {
    private final CustomerDAOImpl customerDAO;
    public CustomerSeviceImpl(CustomerDAOImpl customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public Boolean addCustomer(Customer customer) {
        return customerDAO.addCustomer(customer);
    }

    @Override
    public Boolean updateCustomer(Customer customer) {
        if(customerDAO.getCustomerById(customer.getId()) == null){
            return false;
        }
        return customerDAO.updateCustomer(customer);
    }

    @Override
    public Boolean deleteCustomer(int id) {
        if(customerDAO.getCustomerById(id) == null){
            return false;
        }
        return customerDAO.deleteCustomer(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerDAO.getCustomerById(id);
    }
}
