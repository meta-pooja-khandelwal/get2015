package com.metasocio.hibernate.factory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtil {
	private static SessionFactory sessionFactory;

	public static SessionFactory getConfigurationInstance() {
		if (sessionFactory == null) {
			Configuration cfg = ConfigurationFactory.getConfigurationInstance();
			sessionFactory = cfg.buildSessionFactory();

		}
		return sessionFactory;
	}
}
