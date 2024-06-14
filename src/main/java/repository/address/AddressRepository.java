package repository.address;

import base.repository.BaseRepository;
import model.Address;
import model.Customer;

import java.util.List;

public interface AddressRepository extends BaseRepository<Address, Long> {

    String existsByPostalCode(String postalCode);

    List<Address> findByCustomer(Customer customer);

}
