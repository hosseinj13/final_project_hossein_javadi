package service.order;

import base.service.BaseService;
import enums.OrderStatus;
import model.Customer;
import model.Order;
import model.Specialist;
import model.SubService;

import java.util.Collection;
import java.util.List;

public interface OrderService extends BaseService<Order, Long> {
    List<Order> findAll();
    List<Order> findByCustomer(Customer customer);
    List<Order> findBySelectedSpecialist(Specialist specialist);

    void removeById(Long orderId);


    Collection<? extends Order> findAllBySubServiceAndStatus(SubService subService, OrderStatus orderStatus);
}
