package com.metasocio.dbhelper.usermanagement;
import org.hibernate.Criteria;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import com.metasocio.exception.MetaSocioException;
import com.metasocio.model.usermanagement.User;

/****************************************************************************
 * Description: This class is directly querying the database regarding 
 * validation of user to get the required information.
 ****************************************************************************/
public class ValidateUser {
	private static ValidateUser iUser;
	public ValidateUser() {
	}
	public static ValidateUser getInstance() {
		iUser = new ValidateUser();
		return iUser;
	}
	/********************************************************************************
	 * Method to check whether the user with given email id exists in database or not
	 * @param email
	 * @param transaction
	 * @param session
	 * @return
	 * @throws MetaSocioException
	 ********************************************************************************/
	public boolean isEmailExists(String email,
			Session session) throws MetaSocioException {
		boolean isUserExists = false;
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		User user = (User) criteria.uniqueResult();
		if (user != null) {
			isUserExists = true;
		} else {
			isUserExists = false;
		}
		return isUserExists;
	}
}
