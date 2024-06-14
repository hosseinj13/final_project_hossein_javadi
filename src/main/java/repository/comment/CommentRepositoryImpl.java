package repository.comment;

import base.repository.BaseRepositoryImpl;
import connection.SessionFactorySingleton;
import model.Address;
import model.Comment;
import model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.address.AddressRepository;

import java.util.List;

public class CommentRepositoryImpl extends BaseRepositoryImpl<Comment, Long>
        implements CommentRepository {
    public CommentRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Class<Comment> getEntityClass() {
        return Comment.class;
    }

    @Override
    public List<Comment> findAllByCustomer(Customer customer) {
        Session session = SessionFactorySingleton.getInstance().openSession();
        String hql = "FROM Comment c WHERE c.customer = :customer";
        Query<Comment> query = session.createQuery(hql, Comment.class);
        query.setParameter("customer", customer);
        List<Comment> comments = query.list();
        session.close();
        return comments;
    }

    @Override
    public void deleteComment(Comment selectedComment) {
        Session session = SessionFactorySingleton.getInstance().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "DELETE FROM Comment c WHERE c.id = :commentId";
            Query<Comment> query = session.createQuery(hql, Comment.class);
            query.setParameter("commentId", selectedComment.getId());
            int result = query.executeUpdate();
            transaction.commit();
            if (result > 0) {
                System.out.println("Comment deleted successfully.");
            } else {
                System.out.println("No comment found with the provided ID.");
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


}
