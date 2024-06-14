package service.comment;

import base.service.BaseServiceImpl;
import model.Comment;
import model.Customer;
import org.hibernate.SessionFactory;
import repository.comment.CommentRepository;

import java.util.List;

public class CommentServiceImpl extends BaseServiceImpl<Comment, Long, CommentRepository>
        implements CommentService {
    public CommentServiceImpl(CommentRepository repository) {
        super(repository);
    }


    @Override
    public List<Comment> findAllByCustomer(Customer customer) {
        return repository.findAllByCustomer(customer);
    }

    @Override
    public void deleteComment(Comment selectedComment) {
        repository.deleteComment(selectedComment);
    }
}
