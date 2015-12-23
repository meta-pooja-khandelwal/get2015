import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.connection.ConnectionUtil;

public class DeadlockDemo {
	public static void main(String[] args) throws InterruptedException {

		Thread t1 = new Thread(new SyncThread1(), "t1");
		Thread t2 = new Thread(new SyncThread2(), "t2");

		t1.start();
		Thread.sleep(5);
		t2.start();
		Thread.sleep(5);

	}

}

class SyncThread1 implements Runnable {

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		System.out.println(name);
		System.out.println(name
				+ " will acquire lock on table ad_data in SyncThread1");

		String query = "update  ad_data set cost=1900 where day='2006-08-01' AND ad_id=1; ";
		Connection con = null;
		ResultSet rs = null;
		Statement stmt = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		con = conUtil.getConnection();
		try {
			stmt = con.createStatement();
			System.out.println(name
					+ " will execute update query in  SyncThread1");
			stmt.executeUpdate(query);
			System.out.println(name
					+ " has executed update query in  SyncThread1");
			work();// sleep mode
			System.out.println(name
					+ " is come from sleep mode in  SyncThread1");
			con.commit();
			System.out.println(name
					+ " is realising table ad_data  SyncThread1");

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
			Thread.sleep(30000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class SyncThread2 implements Runnable {

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		System.out.println(name);
		System.out.println(name
				+ " will acquire lock on table ad_data in SyncThread2");

		String query = "update  ad_data set cost=3000 where day='2006-08-01' AND ad_id=1; ";
		Connection con = null;
		ResultSet rs = null;
		Statement stmt = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		con = conUtil.getConnection();
		try {
			stmt = con.createStatement();
			System.out.println(name
					+ " will execute update query in  SyncThread2");
			stmt.executeUpdate(query);// this statement will never execute for
										// thread t2 because of mutual
										// exclusion,no preemption,condition
										// t1 thread have already acquired the
										// lock on table ad_data for a long
										// period
			System.out.println(name
					+ " has executed update query in  SyncThread2");
			work();// sleep mode
			System.out.println(name
					+ " is come from sleep mode in  SyncThread2");
			con.commit();
			System.out.println(name
					+ " is realising table ad_data  SyncThread2");

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
			Thread.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}