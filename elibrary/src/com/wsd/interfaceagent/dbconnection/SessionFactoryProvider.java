package com.wsd.interfaceagent.dbconnection;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.wsd.interfaceagent.model.*;

public final class SessionFactoryProvider {
	private static final SessionFactory sessionFactory;
	
	static {
		Configuration configuration = new Configuration();
		configuration.configure("/resources/hibernate.cfg.xml").addAnnotatedClass(UserData.class)
														.addAnnotatedClass(UsersUptakesData.class)
														.addAnnotatedClass(BooksData.class)
														.addAnnotatedClass(UsersAccountData.class);
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(ssrb.build());
    }
	
	public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private SessionFactoryProvider() {
    }
    
}
