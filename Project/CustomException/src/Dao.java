import java.sql.SQLException;
import java.sql.Statement;

public class Dao {

	public static void insertDataIntoUser(Statement stmt) throws SQLException {
		String query = "insert into user values(1,'pooja');";
		stmt.executeUpdate(query);
	}

}
