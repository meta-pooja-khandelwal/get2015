/**
 * @author Pooja Khandelwal
 * @created date :21/10/2015
 * @name Approver
 * @description this is an abstract class which have one concrete method to set the successor value and one abstract method which will be implemented by the derived classes
 */
package com.approver;

public abstract class Approver {
	protected Approver successor;

	/**
	 * @name SetSuccessor()
	 * @description it will set the value of successor for an approver
	 * @param successor
	 * @return
	 */
	public void SetSuccessor(Approver successor) {
		this.successor = successor;
	}

	// abstract method
	public abstract void ProcessRequest(int noOfLeaves);
}
