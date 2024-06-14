package repository.comment;

import base.repository.BaseRepository;
import model.Comment;
import model.Customer;

import java.util.List;

public interface CommentRepository extends BaseRepository<Comment, Long> {

    List<Comment> findAllByCustomer(Customer customer);

    void deleteComment(Comment selectedComment);


}
