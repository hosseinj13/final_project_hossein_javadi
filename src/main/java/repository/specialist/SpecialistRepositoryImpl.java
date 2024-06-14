package repository.specialist;

import base.repository.BaseRepositoryImpl;
import connection.SessionFactorySingleton;
import enums.SpecialistStatus;
import exception.NotFoundException;
import jakarta.persistence.EntityNotFoundException;
import model.Specialist;
import model.SubService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Set;

public class SpecialistRepositoryImpl extends BaseRepositoryImpl<Specialist, Long>
        implements SpecialistRepository {
    public SpecialistRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Class<Specialist> getEntityClass() {
        return Specialist.class;
    }

    @Override
    public Specialist findByUsernameAndPassword(String username, String password) {
        Session session = SessionFactorySingleton.getInstance().openSession();
        Query<Specialist> query = session.createQuery("FROM Specialist s WHERE s.username = :username AND s.password = :password", Specialist.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        Specialist specialist = query.uniqueResult();
        session.close();
        return specialist;
    }

    @Override
    public List<Specialist> findByStatusIn(List<SpecialistStatus> specialistStatusList) {
        Session session = SessionFactorySingleton.getInstance().openSession();
        String hql = "FROM Specialist s WHERE s.status IN (:statusList)";
        Query<Specialist> query = session.createQuery(hql, Specialist.class);
        query.setParameter("statusList", specialistStatusList);
        List<Specialist> specialists = query.list();
        session.close();
        return specialists;
    }

    @Override
    public Specialist update(Specialist specialist, Long specialistId, Set<SubService> subServices) throws IllegalStateException {
        Session session = SessionFactorySingleton.getInstance().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Specialist existingSpecialist;
            if (specialistId == null) {
                // If specialistId is null, we're creating a new Specialist
                session.persist(specialist);
                existingSpecialist = specialist;
            } else {
                // If specialistId is not null, we're updating an existing Specialist
                existingSpecialist = session.get(Specialist.class, specialistId);
                if (existingSpecialist == null) {
                    throw new EntityNotFoundException("Specialist not found with id: " + specialistId);
                }

                existingSpecialist.setUsername(specialist.getUsername());
                existingSpecialist.setPassword(specialist.getPassword());
                existingSpecialist.setSubServices(subServices);
                existingSpecialist = session.merge(existingSpecialist);
            }

            transaction.commit();
            return existingSpecialist;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Failed to save or update Specialist", e);
        }
    }

    @Override
    public List<Specialist> findByStatus(SpecialistStatus status) {
        Session session = SessionFactorySingleton.getInstance().openSession();
        String hql = "FROM Specialist s WHERE s.status = :status";
        Query<Specialist> query = session.createQuery(hql, Specialist.class);
        query.setParameter("status", status);
        List<Specialist> specialists = query.list();
        session.close();
        return specialists;
    }

    @Override
    public Specialist updateSpecialistWithSubServices(Long specialistId, Set<SubService> subServices) {

        Session session = SessionFactorySingleton.getInstance().openSession();
        Transaction transaction = null;
        Specialist specialist = null;

        try {
            transaction = session.beginTransaction();

            // Fetch the specialist by ID
            specialist = session.get(Specialist.class, specialistId);
            if (specialist == null) {
                throw new NotFoundException("Specialist not found with ID: " + specialistId);
            }

            // Update the specialist's sub-services
            specialist.setSubServices(subServices);
            session.update(specialist);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }

        return specialist;
    }

    @Override
    public void removeByUsername(String username) {
        Session session = SessionFactorySingleton.getInstance().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            String hql = "DELETE FROM Specialist s WHERE s.username = :username";
            Query<?> query = session.createQuery(hql);
            query.setParameter("username", username);

            int result = query.executeUpdate();

            transaction.commit();

            if (result == 0) {
                System.out.println("No specialist found with the username: " + username);
            } else {
                System.out.println("Specialist with username " + username + " has been deleted.");
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
    public List<Specialist> findAll() {
        Session session = SessionFactorySingleton.getInstance().openSession();
        String hql = "FROM Specialist ";
        Query<Specialist> query = session.createQuery(hql, Specialist.class);
        List<Specialist> specialists = query.list();
        session.close();
        return specialists;
    }

    @Override
    public void removeById(Long specialistId) {
        Session session = SessionFactorySingleton.getInstance().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            String hql = "DELETE FROM Specialist s WHERE s.id = :specialistId";
            Query<Specialist> query = session.createQuery(hql, Specialist.class);
            query.setParameter("specialistId", specialistId);

            int result = query.executeUpdate();

            transaction.commit();

            if (result == 0) {
                System.out.println("No specialist found with the ID: " + specialistId);
            } else {
                System.out.println("Specialist with ID " + specialistId + " has been deleted.");
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
    public List<Specialist> findBySubService(SubService subService) {
        Session session = SessionFactorySingleton.getInstance().openSession();
        String hql = "SELECT s FROM Specialist s JOIN s.subServices ss WHERE ss = :subService";
        Query<Specialist> query = session.createQuery(hql, Specialist.class);
        query.setParameter("subService", subService);
        List<Specialist> specialists = query.list();
        session.close();
        return specialists;
    }

    @Override
    public Specialist findByIdWithSubServices(Long id) {
        Session session = SessionFactorySingleton.getInstance().openSession();
        Specialist specialist = null;
        try {
            String hql = "SELECT s FROM Specialist s LEFT JOIN FETCH s.subServices WHERE s.id = :specialistId";
            Query<Specialist> query = session.createQuery(hql, Specialist.class);
            query.setParameter("specialistId", id);
            specialist = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return specialist;
    }

//    @Override
//    public void addSpecialty(Long specialistId, Long specialtyId) {
//        Session session = SessionFactorySingleton.getInstance().openSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//
//            Specialist specialist = session.get(Specialist.class, specialistId);
//            Specialty specialty = session.get(Specialty.class, specialtyId);
//
//            if (specialist == null || specialty == null) {
//                throw new NotFoundException("Specialist or Specialty not found!");
//            }
//
//            specialist.getSpecialties().add(specialty);
//            session.update(specialist);
//
//            tx.commit();
//        } catch (Exception e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    }
//
//    @Override
//    public Set<Specialty> getSpecialties(Long specialistId) {
//        Session session = SessionFactorySingleton.getInstance().openSession();
//        Specialist specialist = session.get(Specialist.class, specialistId);
//        session.close();
//        return specialist != null ? specialist.getSpecialties() : null;
//    }
}
