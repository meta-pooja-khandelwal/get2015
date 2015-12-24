import com.exception.ExceptionClass;

public class MainClass {

	public static void main(String[] args) {
		try {
			Service.insertDataIntoUser();
		} catch (ExceptionClass e) {
			System.out.println("Could not insert data into user table");
		}
	}
}
