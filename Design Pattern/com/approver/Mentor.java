/**
 * @author Pooja Khandelwal
 * @created date :21/10/2015
 * @name Mentor
 * @description this is the class which inherits the abstract class Approver and provide the implementation for abstract methods of Approver class
 */
package com.approver;

public class Mentor extends Approver {
	private static Mentor iMentor;

	/**
	 * @name Mentor()
	 * @description it is a private constructor of Mentor class so that its
	 *              instance can't be created by outSide world
	 * @param
	 */
	private Mentor() {

	}

	/**
	 * @name getInstance()
	 * @description it will create if instance is null and return the instance
	 *              of Mentor class whenever required
	 * @param
	 * @return iMentor(object/instance of Mentor class)
	 */
	public static Approver getInstance() {
		if (iMentor == null) {
			iMentor = new Mentor();
		}
		return iMentor;
	}

	/**
	 * @name ProcessRequest
	 * @description if no. of leaves will be less than equals to 1, then it will
	 *              be approved by Mentor otherwise the approval request will
	 *              forward to srMentor
	 * @param noOfLeaves
	 * @return
	 */
	@Override
	public void ProcessRequest(int noOfLeaves) {
		if (noOfLeaves <= 1) {
			System.out.println("Request Approved by Mentor!");
		} else if (successor != null) {
			successor.ProcessRequest(noOfLeaves);
		}
	}
}