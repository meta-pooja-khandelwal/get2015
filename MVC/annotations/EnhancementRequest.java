/**
 * @author Pooja Khandelwal
 * @created date 16/10/2015
 * @Name   EnhancementRequest 
 * @description this is an annotation with 4 elements
 */
package com.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface EnhancementRequest {
	int id();

	String synopsis();

	String engineer() default "unassigned";

	String date() default "unknown";
}
