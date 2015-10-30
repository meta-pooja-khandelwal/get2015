/**
 * @author Pooja Khandelwal
 * @created date :21/10/2015
 * @name CompanyMain 
 * @description this is the main class to handle the functions of Company 
 */
package com.main;

import java.util.List;
import java.util.Scanner;
import com.company.Company;
import com.company.menu.Menu;
import com.employee.EmployeeFactory;
import com.employee.EmployeeType;
import com.employee.IEmployee;

public class CompanyMain {
	public static void main(String args[]) {

		Menu menu = new Menu();
		Scanner scanner = new Scanner(System.in);
		int choice = -1;
		Company company = Company.getInstance();
		do {
			menu.displayMenu();
			while (!scanner.hasNextInt()) {
				System.out.println("Please Enter only Integer!");
				scanner.next();
			}
			choice = scanner.nextInt();
			while (choice < 0 || choice > 2) {
				System.out.println("Please enter valid integer!");
				while (!scanner.hasNextInt()) {
					System.out.println("Please Enter only Integer!");
					scanner.next();
				}
				choice = scanner.nextInt();
			}
			switch (choice) {
			case 1:
				System.out.println("Please Enter employee type :-\n");
				IEmployee iEmployee = null;
				String name;
				String role;
				int employeeType = -1;
				System.out
						.println("Press 1 to add Developer\nPress 2 to add SalesPerson");
				while (!scanner.hasNextInt()) {
					System.out.println("Please Enter only Integer!");
					scanner.next();
				}
				employeeType = scanner.nextInt();
				while (employeeType < 1 || employeeType > 2) {
					System.out.println("Please enter valid integer!");

					while (!scanner.hasNextInt()) {
						System.out.println("Please Enter only Integer!");
						scanner.next();
					}
					employeeType = scanner.nextInt();
				}
				switch (employeeType) {
				case 1:
					iEmployee = EmployeeFactory
							.getIEmployee(EmployeeType.developer);
					break;
				case 2:
					iEmployee = EmployeeFactory
							.getIEmployee(EmployeeType.salesPerson);

					break;
				}
				if (!iEmployee.equals(null)) {

					System.out.println("Enter name of Employee");
					scanner.nextLine();
					name = scanner.nextLine();
					System.out.println("Enter role of Employee in company");
					role = scanner.nextLine();
					iEmployee.createEmployee(name, role);
					company.addEmployee(iEmployee);
				}
				break;

			case 2:
				List<IEmployee> employees = company.getEmployeesList();
				for (int i = 0; i < employees.size(); i++) {
					iEmployee = employees.get(i);
					System.out.println(iEmployee);
				}
				break;
			case 0:
				scanner.close();
				System.exit(0);
				break;
			}
		} while (choice != 0);

	}
}
