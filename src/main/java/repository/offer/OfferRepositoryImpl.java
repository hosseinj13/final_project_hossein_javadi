package repository.offer;

import base.repository.BaseRepositoryImpl;
import connection.SessionFactorySingleton;
import model.MainService;
import model.Offer;
import model.Specialist;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.mainservice.MainServiceRepository;

import java.util.List;
import java.util.Optional;

public class OfferRepositoryImpl extends BaseRepositoryImpl<Offer, Long>
        implements OfferRepository {
    public OfferRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Class<Offer> getEntityClass() {
        return Offer.class;
    }

    @Override
    public Optional<Offer> findBySpecialist(Specialist specialist) {
        Session session = SessionFactorySingleton.getInstance().openSession();
        Optional<Offer> optionalOffer = Optional.empty();
        try {
            String hql = "SELECT o FROM Offer o WHERE o.specialist = :specialist ORDER BY o.proposalDate ASC";
            Query<Offer> query = session.createQuery(hql, Offer.class);
            query.setParameter("specialist", specialist);
            query.setMaxResults(1); // Retrieve only the first result
            List<Offer> offers = query.list();
            if (!offers.isEmpty()) {
                optionalOffer = Optional.of(offers.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return optionalOffer;
    }

    @Override
    public void update(Offer selectedOffer, Specialist specialist) {
        Session session = SessionFactorySingleton.getInstance().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            // Update the specialist of the selected offer
            selectedOffer.setSpecialist(specialist);
            session.saveOrUpdate(selectedOffer);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteOffer(Offer selectedOffer) {
        Session session = SessionFactorySingleton.getInstance().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            // Delete the selected offer
            session.delete(selectedOffer);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }



}
