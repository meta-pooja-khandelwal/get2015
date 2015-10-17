/**
 * @author Pooja Khandelwal
 * @created date 16/10/2015
 * @Name   AnnotationRunner 
 * @description this is the class having some methods using custom annotation (CanRun)
 */
package com.employeehelper;

import com.annotations.CanRun;

public class AnnotationRunner {
	@CanRun
	public void method1() {
		System.out.println("Executing method-1 ");
	}

	public void method2() {
		System.out.println("Executing method-2");
	}

	@CanRun
	public void method3() {
		System.out.println("Executing method-3");
	}

	public void method4() {
		System.out.println("Executing method-4");
	}

	@CanRun
	public void method5() {
		System.out.println("Executing method-5");
	}

	public void method6() {
		System.out.println("No annotation");
	}

}
