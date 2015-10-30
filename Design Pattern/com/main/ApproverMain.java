/**
 * @author Pooja Khandelwal
 * @created date :21/10/2015
 * @name ApproverMain
 * @description this is the class which will handle the leave approval for employee 
 */
package com.main;

import java.util.Scanner;

import com.approver.Approver;
import com.approver.ApproverFactory;
import com.approver.ApproverType;
import com.approver.ChainOfResponsblitiesForApprover;

public class ApproverMain {
	public static void main(String args[]) {
		Approver approver = ChainOfResponsblitiesForApprover
				.setResponsibilitesByChainning();

		int noOfLeaves = -1;
		System.out.println("Enter no. of leaves you want");
		Scanner scanner = new Scanner(System.in);
		while (!scanner.hasNextInt()) {
			System.out.println("Please Enter only Integer!");
			scanner.next();
		}
		noOfLeaves = scanner.nextInt();
		while (noOfLeaves < 1) {
			System.out.println("please enter value greater than 0");
			while (!scanner.hasNextInt()) {
				System.out.println("Please Enter only Integer!");
				scanner.next();
			}
			noOfLeaves = scanner.nextInt();
		}

		approver.ProcessRequest(noOfLeaves);
		scanner.close();
	}
}
