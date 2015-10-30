/**  
 * @author: Pooja Khandelwa
 * @created date:21/10/2015
 * @Name: Menu Class
 * @Description: it will set the all menu items contens and then display 
 * them by further calling the displayMenu() function of MenuItems class
 *
 */
package com.company.menu;

import com.menuItems.MenuItems;

public class Menu {
	private String[] menuContent;

	/**
	 * @Name: Menu Class Constructor
	 * 
	 * @Description: set the all menu items content
	 */
	public Menu() {
		menuContent = new String[] { "\npress 1 to Add Employee",
				"Press 2 to show Employee list", "Enter 0 to exit" };
	}

	/**
	 * @Name : displayMenu() function
	 * 
	 * @Description :this function will display the menu contents by further
	 *              calling the displayMenu() function of MenuItems class
	 * 
	 * @Param :
	 * 
	 * @Return :
	 */
	public void displayMenu() {
		MenuItems menuItems = new MenuItems();
		for (int i = 0; i < menuContent.length; i++) {
			menuItems.displayMenu(menuContent[i]);
		}

	}

}
