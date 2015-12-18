
public class DeadLoackSolution {
	
	public static void main(String[] args) throws InterruptedException {
		Object obj1 = new Object();
		Object obj2 = new Object();
		Object obj3 = new Object();

		Thread t1 = new Thread(new SyncThread1(obj1, obj2), "t1");
		Thread t2 = new Thread(new SyncThread1(obj2, obj3), "t2");
		Thread t3 = new Thread(new SyncThread1(obj3, obj1), "t3");

		t1.start();
		Thread.sleep(50);
		t2.start();
		Thread.sleep(50);
		t3.start();
		Thread.sleep(50);

	}

}

class SyncThread1 implements Runnable {
	private Object obj1;
	private Object obj2;

	public SyncThread1(Object obj12, Object obj22) {
		this.obj1 = obj12;
		this.obj2 = obj22;
	}

	
	@Override
	public void run() {
	    String name = Thread.currentThread().getName();
	    System.out.println(name + " acquiring lock on obj11 " + obj1);
	    synchronized (obj1) {
	        System.out.println(name + " acquired lock on obj12 " + obj1);
	        work();
	    }
	    System.out.println(name + " released lock on obj13 " + obj1);
	    System.out.println(name + " acquiring lock on 0bj21 " + obj2);
	    synchronized (obj2) {
	        System.out.println(name + " acquired lock on obj22 " + obj2);
	        work();
	    }
	    System.out.println(name + " released lock on obj23 " + obj2);
	 
	    System.out.println(name + " finished execution.");
	}
	
	private void work() {
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
