/**
 * @author Pooja Khandelwal
 * @created date :21/10/2015
 * @name SeniorMentor
 * @description this is the class which inherits the abstract class Approver and provide the implementation for abstract methods of Approver class
 */
package com.approver;

public class SeniorMentor extends Approver {
	private static SeniorMentor iSeniorMentor;

	/**
	 * @name SeniorMentor()
	 * @description it is a private constructor of SeniorMentor class so that
	 *              its instance can't be created by outSide world
	 * @param
	 */
	private SeniorMentor() {

	}

	/**
	 * @name getInstance()
	 * @description it will create if instance is null and return the instance
	 *              of SeniorMentor class whenever required
	 * @param
	 * @return iSeniorMentor(object/instance of SeniorMentor class)
	 */
	public static Approver getInstance() {
		if (iSeniorMentor == null) {
			iSeniorMentor = new SeniorMentor();
		}
		return iSeniorMentor;
	}

	/**
	 * @name ProcessRequest
	 * @description if no. of leaves will be equals to 2, then it will be
	 *              approved by SeniorMentor otherwise the approval request will
	 *              forward to HR manager
	 * @param noOfLeaves
	 * @return
	 */
	@Override
	public void ProcessRequest(int noOfLeaves) {
		if (noOfLeaves == 2) {
			System.out.println("Request Approved by Senior Mentor!");
		} else if (successor != null) {
			successor.ProcessRequest(noOfLeaves);
		}
	}
}
