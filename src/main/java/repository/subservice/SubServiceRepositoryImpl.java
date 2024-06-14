package repository.subservice;

import base.repository.BaseRepositoryImpl;
import connection.SessionFactorySingleton;
import model.MainService;
import model.Order;
import model.SubService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.order.OrderRepository;

import java.util.List;

public class SubServiceRepositoryImpl extends BaseRepositoryImpl<SubService, Long>
        implements SubServiceRepository {
    public SubServiceRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Class<SubService> getEntityClass() {
        return SubService.class;
    }

    @Override
    public List<SubService> findAll() {
        Session session = SessionFactorySingleton.getInstance().openSession();
        String hql = "FROM SubService";
        Query<SubService> query = session.createQuery(hql, SubService.class);
        List<SubService> subServices = query.list();
        session.close();
        return subServices;
    }

    @Override
    public List<SubService> findByMainService(MainService mainService) {
        Session session = SessionFactorySingleton.getInstance().openSession();
        String hql = "FROM SubService s WHERE s.service = :mainService";
        Query<SubService> query = session.createQuery(hql, SubService.class);
        query.setParameter("mainService", mainService);
        List<SubService> subServices = query.list();
        session.close();
        return subServices;
    }

    @Override
    public void removeById(Long subserviceId) {
        Session session = SessionFactorySingleton.getInstance().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            String hql = "DELETE FROM SubService s WHERE s.id = :subserviceId";
            Query<SubService> query = session.createQuery(hql, SubService.class);
            query.setParameter("subserviceId", subserviceId);

            int result = query.executeUpdate();

            transaction.commit();

            if (result == 0) {
                System.out.println("No specialist found with the ID: " + subserviceId);
            } else {
                System.out.println("Specialist with ID " + subserviceId + " has been deleted.");
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
    public SubService findBySubserviceNameAndService(String subServiceName, MainService service) {
        Session session = SessionFactorySingleton.getInstance().openSession();
        String hql = "FROM SubService ss WHERE ss.subserviceName = :subServiceName AND ss.service = :mainService";
        Query<SubService> query = session.createQuery(hql, SubService.class);
        query.setParameter("subServiceName", subServiceName);
        query.setParameter("mainService", service);
        SubService subService = query.uniqueResult();
        session.close();
        return subService;
    }

}
