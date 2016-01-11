package utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.cfg.Configuration;
//import org.hibernate.service.ServiceRegistry;
 
public class HibernateUtil {
    private static SessionFactory sessionFactory;
     
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            // loads configuration and mappings
            StandardServiceRegistry serviceRegistry = 
            		new StandardServiceRegistryBuilder().configure().build();

            // builds a session factory from the service registry
            sessionFactory = new Configuration().buildSessionFactory(serviceRegistry);              
        }         
        return sessionFactory;
    }
    
// Below code is recommended everywhere after ServiceRegistryBuilder got deprecated but it doesnt work! It fails to read the mapping resources. 
/*    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }
 */
}