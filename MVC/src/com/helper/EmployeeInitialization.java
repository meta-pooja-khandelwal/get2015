/**
 * @author Pooja Khandelwal
 * @createdDate 15/10/2015
 * @name  EmployeeInitialization
 * @description this class will called every time when the application starts up and initialize the employee cache with 5 employees details
 */
package com.helper;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.models.Employee;

public class EmployeeInitialization implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub

	}

	/**
	 * @name contextInitialized()
	 * @description this function will call on application start up and
	 *              initialize the employee cache with 5 employees details
	 * @param event
	 *            (ServletContextEvent event)
	 * @return
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		System.out.println("hi");
		System.out.println("date");
		Map<Integer, Employee> employeeCache = new ConcurrentHashMap<Integer, Employee>();
		ServletContext context = event.getServletContext();
		int id = 0;
		Date date = new Date();
		id++;
		employeeCache.put(id, new Employee("pooja", "poojak@gmail.com", 22, id,
				date));
		id++;
		employeeCache.put(id, new Employee("kiran", "kiran@gmail.com", 22, id,
				date));
		id++;
		employeeCache.put(id, new Employee("sumi", "sumi@gmail.com", 22, id,
				date));
		id++;
		employeeCache.put(id, new Employee("ravika", "ravika@gmail.com", 22,
				id, date));
		id++;
		employeeCache.put(id, new Employee("ashvini", "ashvini@gmail.com", 22,
				id, date));

		context.setAttribute("employeeCache", employeeCache);
		EmployeeMain.employeesCache = employeeCache;
	}
}
