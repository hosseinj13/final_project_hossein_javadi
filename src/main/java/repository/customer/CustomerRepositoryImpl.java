package repository.customer;

import base.repository.BaseRepositoryImpl;
import connection.SessionFactorySingleton;
import model.Admin;
import model.Comment;
import model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.comment.CommentRepository;

public class CustomerRepositoryImpl extends BaseRepositoryImpl<Customer, Long>
        implements CustomerRepository {
    public CustomerRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Class<Customer> getEntityClass() {
        return Customer.class;
    }

    @Override
    public Customer findByUsernameAndPassword(String username, String password) {
        Session session = SessionFactorySingleton.getInstance().openSession();
        Query<Customer> query = session.createQuery("FROM Customer c WHERE c.username = :username AND c.password = :password", Customer.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        Customer customer = query.uniqueResult();
        session.close();
        return customer;
    }

    @Override
    public Customer update(Customer customer, Long customerId) {
        try (Session session = SessionFactorySingleton.getInstance().openSession()) {
            String hql = "UPDATE Customer SET firstName = :firstName, lastName = :lastName, email = :email WHERE id = :customerId";
            Query<Customer> query = session.createQuery(hql, Customer.class);
            query.setParameter("firstName", customer.getFirstName());
            query.setParameter("lastName", customer.getLastName());
            query.setParameter("email", customer.getEmail());
            query.setParameter("customerId", customerId);
            int result = query.executeUpdate();
            if (result > 0) {
                // Assuming you want to return the updated customer
                return session.find(Customer.class, customerId);
            } else {
                return null; // Or throw an exception if customer not found
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Or rethrow the exception
        }
    }

    @Override
    public void removeByUsername(String username) {
        try (Session session = SessionFactorySingleton.getInstance().openSession()) {
            String hql = "DELETE FROM Customer c WHERE c.username = :username";
            Query<Customer> query = session.createQuery(hql, Customer.class);
            query.setParameter("username", username);
            int result = query.executeUpdate();
            if (result == 0) {
                System.out.println("No customer found with the username: " + username);
            } else {
                System.out.println("Customer with username " + username + " has been deleted.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
