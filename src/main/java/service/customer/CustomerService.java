package service.customer;

import base.service.BaseService;
import model.Customer;

public interface CustomerService extends BaseService<Customer, Long> {

    Customer findByUsernameAndPassword(String username, String password);
    Customer update(Customer customer, Long customerId);


    void removeByUsername(String username);
}
