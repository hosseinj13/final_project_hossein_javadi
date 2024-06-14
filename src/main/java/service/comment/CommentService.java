package service.comment;

import base.service.BaseService;
import model.Admin;
import model.Comment;
import model.Customer;

import java.util.List;

public interface CommentService extends BaseService<Comment, Long> {

    List<Comment> findAllByCustomer(Customer customer);
    void deleteComment(Comment selectedComment);
}
