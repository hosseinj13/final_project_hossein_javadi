package repository.order;

import base.repository.BaseRepositoryImpl;
import connection.SessionFactorySingleton;
import enums.OrderStatus;
import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.offer.OfferRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OrderRepositoryImpl extends BaseRepositoryImpl<Order, Long>
        implements OrderRepository {
    public OrderRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Class<Order> getEntityClass() {
        return Order.class;
    }

    @Override
    public List<Order> findAll() {
        Session session = SessionFactorySingleton.getInstance().openSession();
        Query<Order> query = session.createQuery("FROM Order", Order.class);
        List<Order> orders = query.list();
        session.close();
        return orders;
    }

    @Override
    public List<Order> findByCustomer(Customer customer) {
        Session session = SessionFactorySingleton.getInstance().openSession();
        String hql = "FROM Order o WHERE o.customer = :customer";
        Query<Order> query = session.createQuery(hql, Order.class);
        query.setParameter("customer", customer);
        List<Order> orders = query.list();
        session.close();
        return orders;
    }

    @Override
    public List<Order> findBySelectedSpecialist(Specialist specialist) {
        Session session = SessionFactorySingleton.getInstance().openSession();
        String hql = "FROM Order o WHERE o.selectedSpecialist = :specialist";
        Query<Order> query = session.createQuery(hql, Order.class);
        query.setParameter("specialist", specialist);
        List<Order> orders = query.list();
        session.close();
        return orders;
    }

    @Override
    public void removeById(Long orderId) {
        Session session = SessionFactorySingleton.getInstance().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            String hql = "DELETE FROM Order o WHERE o.id = :orderId";
            Query<Order> query = session.createQuery(hql, Order.class);
            query.setParameter("orderId", orderId);

            int result = query.executeUpdate();

            transaction.commit();

            if (result == 0) {
                System.out.println("No order found with the ID: " + orderId);
            } else {
                System.out.println("Order with ID " + orderId + " has been deleted.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public Collection<? extends Order> findAllBySubServiceAndStatus(SubService subService, OrderStatus orderStatus) {
        Session session = SessionFactorySingleton.getInstance().openSession();
        List<Order> orders = new ArrayList<>();
        try {
            String hql = "SELECT o FROM Order o WHERE o.subService = :subService AND o.status = :orderStatus";
            Query<Order> query = session.createQuery(hql, Order.class);
            query.setParameter("subService", subService);
            query.setParameter("orderStatus", orderStatus);
            orders = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return orders;
    }


}
