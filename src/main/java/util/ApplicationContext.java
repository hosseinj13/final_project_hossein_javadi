package util;

import connection.SessionFactorySingleton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repository.address.AddressRepository;
import repository.address.AddressRepositoryImpl;
import repository.admin.AdminRepository;
import repository.admin.AdminRepositoryImpl;
import repository.comment.CommentRepository;
import repository.comment.CommentRepositoryImpl;
import repository.customer.CustomerRepository;
import repository.customer.CustomerRepositoryImpl;
import repository.mainservice.MainServiceRepository;
import repository.mainservice.MainServiceRepositoryImpl;
import repository.offer.OfferRepository;
import repository.offer.OfferRepositoryImpl;
import repository.order.OrderRepository;
import repository.order.OrderRepositoryImpl;
import repository.specialist.SpecialistRepository;
import repository.specialist.SpecialistRepositoryImpl;
import repository.subservice.SubServiceRepository;
import repository.subservice.SubServiceRepositoryImpl;
import service.address.AddressService;
import service.address.AddressServiceImpl;
import service.admin.AdminService;
import service.admin.AdminServiceImpl;
import service.comment.CommentService;
import service.comment.CommentServiceImpl;
import service.customer.CustomerService;
import service.customer.CustomerServiceImpl;
import service.mainservice.MainServiceService;
import service.mainservice.MainServiceServiceImpl;
import service.offer.OfferService;
import service.offer.OfferServiceImpl;
import service.order.OrderService;
import service.order.OrderServiceImpl;
import service.specialist.SpecialistService;
import service.specialist.SpecialistServiceImpl;
import service.subservice.SubServiceService;
import service.subservice.SubServiceServiceImpl;


public class ApplicationContext {

    static final SessionFactory SESSION_FACTORY;
    static final Session SESSION;

    private static final AdminRepository ADMIN_REPOSITORY;
    private static final CustomerRepository CUSTOMER_REPOSITORY;
    private static final SpecialistRepository SPECIALIST_REPOSITORY;
    private static final AddressRepository ADDRESS_REPOSITORY;
    private static final MainServiceRepository MAIN_SERVICE_REPOSITORY;
    private static final SubServiceRepository SUB_SERVICE_REPOSITORY;
    private static final OrderRepository ORDER_REPOSITORY;
    private static final OfferRepository OFFER_REPOSITORY;
    private static final CommentRepository COMMENT_REPOSITORY;




    private static final AdminService ADMIN_SERVICE;
    private static final CustomerService CUSTOMER_SERVICE;
    private static final SpecialistService SPECIALIST_SERVICE;
    private static final AddressService ADDRESS_SERVICE;
    private static final MainServiceService MAIN_SERVICE_SERVICE;
    private static final SubServiceService SUB_SERVICE_SERVICE;
    private static final OrderService ORDER_SERVICE;
    private static final OfferService OFFER_SERVICE;
    private static final CommentService COMMENT_SERVICE;

    static {
        SESSION_FACTORY = SessionFactorySingleton.getInstance();
        SESSION = SESSION_FACTORY.openSession();


        ADMIN_REPOSITORY = new AdminRepositoryImpl(SESSION);
        CUSTOMER_REPOSITORY = new CustomerRepositoryImpl(SESSION);
        SPECIALIST_REPOSITORY = new SpecialistRepositoryImpl(SESSION);
        ADDRESS_REPOSITORY = new AddressRepositoryImpl(SESSION);
        MAIN_SERVICE_REPOSITORY = new MainServiceRepositoryImpl(SESSION);
        SUB_SERVICE_REPOSITORY = new SubServiceRepositoryImpl(SESSION);
        ORDER_REPOSITORY = new OrderRepositoryImpl(SESSION);
        OFFER_REPOSITORY = new OfferRepositoryImpl(SESSION);
        COMMENT_REPOSITORY = new CommentRepositoryImpl(SESSION);


        ADMIN_SERVICE = new AdminServiceImpl(ADMIN_REPOSITORY);
        CUSTOMER_SERVICE = new CustomerServiceImpl(CUSTOMER_REPOSITORY);
        SPECIALIST_SERVICE = new SpecialistServiceImpl(SPECIALIST_REPOSITORY);
        ADDRESS_SERVICE = new AddressServiceImpl(ADDRESS_REPOSITORY);
        MAIN_SERVICE_SERVICE = new MainServiceServiceImpl(MAIN_SERVICE_REPOSITORY);
        SUB_SERVICE_SERVICE = new SubServiceServiceImpl(SUB_SERVICE_REPOSITORY);
        ORDER_SERVICE = new OrderServiceImpl(ORDER_REPOSITORY);
        OFFER_SERVICE = new OfferServiceImpl(OFFER_REPOSITORY);
        COMMENT_SERVICE = new CommentServiceImpl(COMMENT_REPOSITORY);

    }

    public static AdminService getAdminService() {
        return ADMIN_SERVICE;
    }
    public static CustomerService getCustomerService() {
        return CUSTOMER_SERVICE;
    }
    public static SpecialistService getSpecialistService() {
        return SPECIALIST_SERVICE;
    }
    public static AddressService getAddressService() {
        return ADDRESS_SERVICE;
    }
    public static MainServiceService getMainServiceService(){
        return MAIN_SERVICE_SERVICE;
    }
    public static SubServiceService getSubServiceService(){
        return SUB_SERVICE_SERVICE;
    }
    public static OrderService getOrderService(){return ORDER_SERVICE;}
    public static OfferService getOfferService(){return OFFER_SERVICE;}
    public static CommentService getCommentService(){return COMMENT_SERVICE;}

}
