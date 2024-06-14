package service.address;

import base.service.BaseService;
import model.Address;
import model.Customer;

import java.util.List;

public interface AddressService extends BaseService<Address, Long> {

    String existsByPostalCode(String postalCode);

    List<Address> findByCustomer(Customer customer);
}
