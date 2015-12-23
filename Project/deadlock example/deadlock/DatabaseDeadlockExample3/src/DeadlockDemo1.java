import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.connection.ConnectionUtil;

// deadlock will never occur in read mode

public class DeadlockDemo1 {
	public static void main(String[] args) throws InterruptedException {

		Thread t1 = new Thread(new SyncThread3(), "t1");
		Thread t2 = new Thread(new SyncThread4(), "t2");

		t1.start();
		Thread.sleep(500);
		t2.start();
		Thread.sleep(500);

	}

}

class SyncThread3 implements Runnable {

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		System.out.println(name);
		System.out.println(name
				+ " will acquire lock on table customer in SyncThread1");

		String query = "select * from customer; ";
		Connection con = null;
		ResultSet rs = null;
		Statement stmt = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		con = conUtil.getConnection();
		try {
			stmt = con.createStatement();
			System.out
					.println(name
							+ " will execute select query on table customer in  SyncThread1");
			stmt.execute(query);
			System.out
					.println(name
							+ " has executed select query on table customer in  SyncThread1");
			work();// sleep mode
			System.out.println(name
					+ " is come from sleep mode in  SyncThread1");

			System.out.println(name
					+ " will acquire lock on table orders in SyncThread1");
			System.out
					.println(name
							+ " will execute select query on table orders in  SyncThread1");
			String query1 = "select * from orders; ";
			stmt.execute(query1);
			System.out
					.println(name
							+ " has executed select query on table orders in  SyncThread1");
			work();// sleep mode
			System.out.println(name
					+ " is come from sleep mode in  SyncThread1");
			con.commit();
			System.out
					.println(name
							+ " is realising table customer and orders in  SyncThread1");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/* close connection */
			try {
				if (con != null) {
					con.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void work() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class SyncThread4 implements Runnable {

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		System.out.println(name);
		System.out.println(name
				+ " will acquire lock on table orders in SyncThread2");

		String query = "select * from orders; ";
		Connection con = null;
		ResultSet rs = null;
		Statement stmt = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		con = conUtil.getConnection();
		try {
			stmt = con.createStatement();
			System.out
					.println(name
							+ " will execute select query on table orders in  SyncThread2");
			stmt.execute(query);
			System.out
					.println(name
							+ " has executed select query on table orders in  SyncThread2");
			work();// sleep mode
			System.out.println(name
					+ " is come from sleep mode in  SyncThread1");

			System.out.println(name
					+ " will acquire lock on table customers in SyncThread2");
			System.out
					.println(name
							+ " will execute select query on table customers in  SyncThread2");
			String query1 = "select * from customer; ";
			stmt.execute(query1);
			System.out
					.println(name
							+ " has executed select query on table customers in  SyncThread2");
			work();// sleep mode
			System.out.println(name
					+ " is come from sleep mode in  SyncThread2");
			con.commit();
			System.out
					.println(name
							+ " is realising table orders and customers in  SyncThread2");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/* close connection */
			try {
				if (con != null) {
					con.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void work() {
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
