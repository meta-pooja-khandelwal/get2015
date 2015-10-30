/**
 * @author Pooja Khandelwal
 * @created date 13/10/2015
 * @name ExceptionHandler 
 * @description this class will handel all the exceptions
 */
package com.exception;

public class ExceptionHandler extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @name errorOnCreationOfPreparedStatement()
	 * @description this method will show error when it is generated during
	 *              creation of prepared statement
	 * @param error
	 *            (error message to be shown)
	 * @return
	 */
	public static void errorOnCreationOfPreparedStatement(String error) {
		System.out.println(error);

	}

	/**
	 * @name errorOnGetconnection()
	 * @description this method will show error when it is generated during get
	 *              connection
	 * @param error
	 *            (error message to be shown)
	 * @return
	 */
	public static void errorOnGetconnection(String error) {
		System.out.println(error);

	}

	/**
	 * @name errorOnCloseConnection()
	 * @description this method will show error when it is generated during
	 *              close connection
	 * @param error
	 *            (error message to be shown)
	 * @return
	 */
	public static void errorOnCloseConnection(String error) {
		// TODO Auto-generated method stub
		System.out.println(error);
	}

	/**
	 * @name errorOnFetchLoginDataFromDatabase()
	 * @description this method will show error when it is generated during
	 *              fetching data from database
	 * @param error
	 *            (error message to be shown)
	 * @return
	 */
	public static void errorOnFetchLoginDataFromDatabase(String error) {
		// TODO Auto-generated method stub
		System.out.println(error);
	}

	/**
	 * @name errorOnIterationOfResultSet()
	 * @description this method will show error when it is generated during
	 *              iteration of result set
	 * @param error
	 *            (error message to be shown)
	 * @return
	 */
	public static void errorOnIterationOfResultSet(String error) {
		// TODO Auto-generated method stub
		System.out.println(error);
	}

}
