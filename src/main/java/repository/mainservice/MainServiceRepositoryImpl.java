package repository.mainservice;

import base.repository.BaseRepositoryImpl;
import connection.SessionFactorySingleton;
import model.Customer;
import model.MainService;
import model.Specialist;
import model.SubService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.customer.CustomerRepository;

import java.util.List;

public class MainServiceRepositoryImpl extends BaseRepositoryImpl<MainService, Long>
        implements MainServiceRepository {
    public MainServiceRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Class<MainService> getEntityClass() {
        return MainService.class;
    }

    @Override
    public List<MainService> findAll() {
        Session session = SessionFactorySingleton.getInstance().openSession();
        String hql = "FROM MainService ";
        Query<MainService> query = session.createQuery(hql, MainService.class);
        List<MainService> mainServices = query.list();
        session.close();
        return mainServices;
    }

    @Override
    public void removeById(Long mainServiceId) {
        Session session = SessionFactorySingleton.getInstance().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            String hql = "DELETE FROM MainService m WHERE m.id = :mainServiceId";
            Query<MainService> query = session.createQuery(hql, MainService.class);
            query.setParameter("mainServiceId", mainServiceId);

            int result = query.executeUpdate();

            transaction.commit();

            if (result == 0) {
                System.out.println("No specialist found with the ID: " + mainServiceId);
            } else {
                System.out.println("Specialist with ID " + mainServiceId + " has been deleted.");
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
    public MainService findByServiceName(String serviceName) {
        Session session = SessionFactorySingleton.getInstance().openSession();
        String hql = "FROM MainService ms WHERE ms.serviceName = :serviceName";
        Query<MainService> query = session.createQuery(hql, MainService.class);
        query.setParameter("serviceName", serviceName);
        MainService mainService = query.uniqueResult();
        session.close();
        return mainService;
    }

}
