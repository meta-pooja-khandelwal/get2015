/**
 * @author Pooja Khandelwal
 * @created date :21/10/2015
 * @name ApproverFactory
 * @description this is class which will return the instance of either Mentor, SeniorMentor, HR_Manager class based on the input approver type
 */
package com.approver;

public class ApproverFactory {
	/**
	 * @name getApprover()
	 * @description it will provide the instance of either Mentor, SeniorMentor,
	 *              HR_Manager class based on the input approver type whenever
	 *              required
	 * @param approverType
	 * @return
	 */
	public static Approver getApprover(ApproverType approverType) {
		Approver approver;
		if (approverType.equals(ApproverType.mentor)) {
			approver = Mentor.getInstance();
		} else if (approverType.equals(ApproverType.senioMentor)) {
			approver = SeniorMentor.getInstance();

		} else if (approverType.equals(ApproverType.hr_manager)) {
			approver = HR_Manager.getInstance();
		} else {
			approver = null;
		}
		return approver;
	}
}
