package repository.order;

import base.repository.BaseRepository;
import enums.OrderStatus;
import model.Customer;
import model.Order;
import model.Specialist;
import model.SubService;

import java.util.Collection;
import java.util.List;

public interface OrderRepository extends BaseRepository<Order, Long> {

   List<Order> findAll();

   List<Order> findByCustomer(Customer customer);

   List<Order> findBySelectedSpecialist(Specialist specialist);

   void removeById(Long orderId);

   Collection<? extends Order> findAllBySubServiceAndStatus(SubService subService, OrderStatus orderStatus);


}
