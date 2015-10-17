/**
 * @author Pooja Khandelwal
 * @created date 16/10/2015
 * @Name TestAnnotationParser 
 * @description this class will get all the methods of the input class and then execute them 
 */
package com.annotationparser;

import java.lang.reflect.Method;
import com.annotations.CanRun;
import com.annotations.EnhancementRequest;

public class TestAnnotationParser {
	/**
	 * @name parse
	 * @description this class will get all the methods of the input class,check
	 *              annotations on them and then execute them
	 * @param tempClass
	 * @throws Exception
	 */
	public void parse(Class<?> tempClass) throws Exception {

		Method[] methods = tempClass.getDeclaredMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(EnhancementRequest.class) == true) {
				EnhancementRequest test = method
						.getAnnotation(EnhancementRequest.class);
				int id = test.id();
				String synopsis = test.synopsis();
				if (1 == id) {
					System.out.println("Welcome");
					method.invoke(tempClass.newInstance(), id, synopsis,
							test.engineer(), test.date());
					System.out.println();
				}

			} else if (method.isAnnotationPresent(CanRun.class) == true) {

				try {
					method.invoke(tempClass.newInstance());
					System.out.println();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else {
				try {
					method.invoke(tempClass.newInstance());
					System.out.println();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}

		}

	}
}
