package repository.address;

import base.repository.BaseRepositoryImpl;
import connection.SessionFactorySingleton;
import model.Address;
import model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class AddressRepositoryImpl extends BaseRepositoryImpl<Address, Long>
        implements AddressRepository {
    public AddressRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Class<Address> getEntityClass() {
        return Address.class;
    }

    @Override
    public String existsByPostalCode(String postalCode) {
            Session session = SessionFactorySingleton.getInstance().openSession();
            String hql = "SELECT a.postalCode FROM Address a WHERE a.postalCode = :postalCode";
            Query<String> query = session.createQuery(hql, String.class);
            query.setParameter("postalCode", postalCode);
            String result = query.uniqueResult();
            session.close();
            return result;
    }

    @Override
    public List<Address> findByCustomer(Customer customer) {
        Session session = SessionFactorySingleton.getInstance().openSession();
        String hql = "FROM Address a WHERE a.customer = :customer";
        Query<Address> query = session.createQuery(hql, Address.class);
        query.setParameter("customer", customer);
        List<Address> addresses = query.list();
        session.close();
        return addresses;
    }

}
