package business;

import model.Customer;

import java.util.List;

public interface ICustomerService {
    Boolean addCustomer(Customer customer);
    Boolean updateCustomer(Customer customer);
    Boolean deleteCustomer(int id);
    List<Customer> getAllCustomers();
    Customer getCustomerById(int id);
}
