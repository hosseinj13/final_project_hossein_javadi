package service.address;

import base.service.BaseServiceImpl;
import model.Address;
import model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repository.address.AddressRepository;

import java.util.List;

public class AddressServiceImpl extends BaseServiceImpl<Address, Long, AddressRepository>
        implements AddressService {
    public AddressServiceImpl(AddressRepository repository) {
        super(repository);
    }

    @Override
    public String existsByPostalCode(String postalCode) {
        return repository.existsByPostalCode(postalCode);
    }

    @Override
    public List<Address> findByCustomer(Customer customer) {
        return repository.findByCustomer(customer);
    }
}
