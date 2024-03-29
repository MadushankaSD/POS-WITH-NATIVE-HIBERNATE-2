package lk.ijse.dep.pos.db;

import lk.ijse.dep.crypto.DEPCrypt;
import lk.ijse.dep.pos.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HibernateUtil {

    private static String username="root";
    private static String password="123";
    private static String database;
    private static String host;
    private static String port;
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        File propFile = new File("resources/application.properties");
        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream(propFile)) {
            properties.load(fis);
        } catch (Exception e) {
            Logger.getLogger("lk.ijse.dep.pos.db.HibernateUtil").log(Level.SEVERE, null,e);
            System.exit(2);
        }

       /* username = DEPCrypt.decode(properties.getProperty("hibernate.connection.username"),"dep4");
        password = DEPCrypt.decode(properties.getProperty("hibernate.connection.password"), "dep4");*/
        host = properties.getProperty("ijse.dep.ip");
        database = properties.getProperty("ijse.dep.db");
        port = properties.getProperty("ijse.dep.port");

        // (1)
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .loadProperties(propFile)
                .build();

        Metadata metadata = new MetadataSources(registry)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(OrderDetail.class)
                .addAnnotatedClass(CustomEntity.class)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();

        // (3)
        return metadata.getSessionFactoryBuilder()
                .build();
    }

    public static String getUsername(){
        return username;
    }

    public static String getPassword(){
        return password;
    }

    public static String getDatabase(){
        return database;
    }

    public static String getPort(){
        return port;
    }

    public static String getHost(){
        return host;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
