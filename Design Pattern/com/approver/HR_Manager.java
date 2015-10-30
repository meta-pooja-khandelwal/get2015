/**
 * @author Pooja Khandelwal
 * @created date :21/10/2015
 * @name HR_Manager
 * @description this is the class which inherits the abstract class Approver and provide the implementation for abstract methods of Approver class
 */
package com.approver;

public class HR_Manager extends Approver {
	private static HR_Manager iHr_Manager;

	/**
	 * @name HR_Manager()
	 * @description it is a private constructor of HR_Manager class so that its
	 *              instance can't be created by outSide world
	 * @param
	 */
	private HR_Manager() {
	}

	/**
	 * @name getInstance()
	 * @description it will create if instance is null and return the instance
	 *              of HR_Manager class whenever required
	 * @param
	 * @return iHr_Manager(object/instance of HR_Manager class)
	 */
	public static Approver getInstance() {
		if (iHr_Manager == null) {
			iHr_Manager = new HR_Manager();
		}
		return iHr_Manager;
	}

	/**
	 * @name ProcessRequest
	 * @description if no. of leaves will be greater than 2 and less than equals
	 *              to 5, then it will be approved by HR manager otherwise
	 *              message will be shown to employee
	 * @param noOfLeaves
	 * @return
	 */
	@Override
	public void ProcessRequest(int noOfLeaves) {
		if (noOfLeaves > 2 && noOfLeaves <= 5) {
			System.out.println("Request Approved by Hr Manager!");
		} else {
			System.out.println("Request requires an executive meeting!");
		}
	}
}
