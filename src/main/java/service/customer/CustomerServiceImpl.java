package service.customer;

import base.service.BaseServiceImpl;
import model.Customer;
import org.hibernate.SessionFactory;
import repository.customer.CustomerRepository;

public class CustomerServiceImpl extends BaseServiceImpl<Customer, Long, CustomerRepository>
        implements CustomerService {
    public CustomerServiceImpl(CustomerRepository repository) {
        super(repository);
    }

    @Override
    public Customer findByUsernameAndPassword(String username, String password) {
        return repository.findByUsernameAndPassword(username, password);
    }

    @Override
    public Customer update(Customer customer, Long customerId) {
        return repository.update(customer, customerId);
    }

    @Override
    public void removeByUsername(String username) {
         repository.removeByUsername(username);
    }
}
