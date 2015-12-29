/**
 * 
 */
package com.metasocio.validation.usermanagement;

import com.metasocio.model.usermanagement.User;

/*********************************************************
 * Description: Profile Validation class is for validation.
 **********************************************************/
public class ProfileValidation {

	/***************************************************************************
	 * This method will validate the personal Info filed of user profile and if 
	 * there is an error then generate a error message
	 * 
	 * @param user
	 * @return
	 ***************************************************************************/
	public String validateUserPersonalInfo(User user) {

		String messagePersonalInfo = "";
		String regex = "[0-9]+";
		if (user.getName().trim().isEmpty()) {
			messagePersonalInfo = "Please enter your name";
		} else if (user.getEmail().trim().isEmpty()) {
			messagePersonalInfo = "Please enter email Id";
		} else if (!user.getEmail().endsWith("@metacube.com")) {
			messagePersonalInfo = "Invalid email";

		} else if (!user.getPhoneNo().isEmpty()
				&& user.getPhoneNo().length() != 10) {
			messagePersonalInfo = "Invalid phone no.";
		} else if (!user.getPhoneNo().isEmpty()
				&& !user.getPhoneNo().matches(regex)) {
			messagePersonalInfo = "Invalid phone no";
		} else {
			messagePersonalInfo = "";
		}

		return messagePersonalInfo;
	}

	/***************************************************************************
	 * This method will validate the Work  And Education filed of user profile 
	 *  and if there is an error then generate a error message
	 * 
	 * @param user
	 * @return
	 ***************************************************************************/
	public String validateUserWorkAndEducationInfo(User user) {
		String messageWorkAndEducation = "";
		if (user.getDepartment().trim().isEmpty()) {
			messageWorkAndEducation = "Please specify your department";
		} else if (user.getRole().trim().isEmpty()) {
			messageWorkAndEducation = "Please specify your role in metacube";
		} else {
			messageWorkAndEducation = "";
		}
		return messageWorkAndEducation;
	}
}