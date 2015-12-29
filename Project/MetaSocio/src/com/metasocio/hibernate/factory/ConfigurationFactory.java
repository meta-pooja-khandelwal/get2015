package com.metasocio.hibernate.factory;

import org.hibernate.cfg.Configuration;

public class ConfigurationFactory {
	private static Configuration cfg;

	public static Configuration getConfigurationInstance() {
		if (cfg == null) {
			cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
		}
		return cfg;
	}
}
