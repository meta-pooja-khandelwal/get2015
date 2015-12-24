import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.connection.ConnectionUtil;
import com.exception.ExceptionClass;

public class Service {

	public static void insertDataIntoUser() throws ExceptionClass {
		Connection con = null;
		Statement stmt = null;
		ConnectionUtil conUtil = new ConnectionUtil();

		try {
			con = conUtil.getConnection();
			stmt = con.createStatement();

			Dao.insertDataIntoUser(stmt);
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				ExceptionClass.setClass(Service.class);
				throw new ExceptionClass("could not rollback the transaction,["
						+ e.getMessage() + "]", e1);
			} catch (Exception e1) {
				ExceptionClass.setClass(Service.class);
				throw new ExceptionClass("[" + e.getMessage() + "]", e1);
			}
			ExceptionClass.setClass(Service.class);
			throw new ExceptionClass(
					"could not insert data into the user table,["
							+ e.getMessage() + "]", e);
		} catch (Exception e) {
			ExceptionClass.setClass(Service.class);
			throw new ExceptionClass("[" + e.getMessage() + "]", e);
		} finally {
			/* close connection */
			try {
				if (con != null) {
					con.close();
				}
				if (stmt != null) {
					stmt.close();
				}

			} catch (SQLException e) {
				ExceptionClass.setClass(Service.class);
				throw new ExceptionClass(
						"could not close the connection or statement,["
								+ e.getMessage() + "]", e);
			}
		}

	}

}
