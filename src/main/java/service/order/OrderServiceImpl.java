package service.order;

import base.service.BaseServiceImpl;
import enums.OrderStatus;
import model.Customer;
import model.Order;
import model.Specialist;
import model.SubService;
import org.hibernate.SessionFactory;
import repository.order.OrderRepository;

import java.util.Collection;
import java.util.List;

public class OrderServiceImpl extends BaseServiceImpl<Order, Long, OrderRepository>
        implements OrderService {
    public OrderServiceImpl(OrderRepository repository) {
        super(repository);
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Order> findByCustomer(Customer customer) {
        return repository.findByCustomer(customer);
    }

    @Override
    public List<Order> findBySelectedSpecialist(Specialist specialist) {
        return repository.findBySelectedSpecialist(specialist);
    }

    @Override
    public void removeById(Long orderId) {
        repository.removeById(orderId);
    }

    @Override
    public Collection<? extends Order> findAllBySubServiceAndStatus(SubService subService, OrderStatus orderStatus) {
        return repository.findAllBySubServiceAndStatus(subService, orderStatus);
    }
}
