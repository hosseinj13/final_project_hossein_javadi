package connection;

import menu.Menu;
import model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class SessionFactorySingleton {
    private SessionFactorySingleton() {
    }

    private static class LazyHolder {
        static SessionFactory INSTANCE;

        static {
            var registry = new StandardServiceRegistryBuilder ()
                    .configure ()
                    .build ();
            INSTANCE = new MetadataSources ( registry )
                    .addAnnotatedClass(Menu.class)
                    .addAnnotatedClass ( User.class )
                    .addAnnotatedClass(Admin.class)
                    .addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(Specialist.class)
                    .addAnnotatedClass(Address.class)
                    .addAnnotatedClass(Comment.class)
                    .addAnnotatedClass(Order.class)
                    .addAnnotatedClass(Offer.class)
                    .addAnnotatedClass(MainService.class)
                    .addAnnotatedClass(SubService.class)
                    .addAnnotatedClass(SecurityContext.class)
                    .buildMetadata ()
                    .buildSessionFactory ();
        }
    }

    public static SessionFactory getInstance() {
        return LazyHolder.INSTANCE;
    }
}