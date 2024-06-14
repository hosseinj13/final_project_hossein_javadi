package repository.customer;

import base.repository.BaseRepository;
import model.Admin;
import model.Customer;

public interface CustomerRepository extends BaseRepository<Customer, Long> {

    Customer findByUsernameAndPassword(String username, String password);

    Customer update(Customer customer, Long customerId);

    void removeByUsername(String username);


}
