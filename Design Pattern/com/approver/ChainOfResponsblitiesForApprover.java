/**
 * @author Pooja Khandelwal
 * @created date :21/10/2015
 * @name ChainOfResponsblitiesForApprover
 * @description this is class which will get the instance of Mentor,SeniorMentor and HrManager class and then set their responsibilities
 */
package com.approver;

public class ChainOfResponsblitiesForApprover {
	/**
	 * @name setResponsibilitesByChainning
	 * @description it will will get the instance of Mentor,SeniorMentor and
	 *              HrManager class and then set their responsibilities
	 * @param
	 * @return mentor(instance of Mentor class)
	 */
	public static Approver setResponsibilitesByChainning() {
		Approver mentor = ApproverFactory.getApprover(ApproverType.mentor);
		Approver srMentor = ApproverFactory
				.getApprover(ApproverType.senioMentor);
		Approver hrManager = ApproverFactory
				.getApprover(ApproverType.hr_manager);

		mentor.SetSuccessor(srMentor);
		srMentor.SetSuccessor(hrManager);

		return mentor;
	}
}
