import java.util.concurrent.Semaphore;

public class Employee {

	private static int NUM_OF_EMPLOYEES =5;
	PaymentSystem system = new PaymentSystem();

	private static Semaphore locker = new Semaphore(NUM_OF_EMPLOYEES);

	public void takeOrder(){
		try {
			System.out.println(" Customer " + Thread.currentThread().getId() + " is Ordering.");
			locker.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void processCustOrder(){
		synchronized(system){
			try {
				Thread.sleep(1000);
				System.out.println("Processing Order for customer " + Thread.currentThread().getId());
				system.use();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Order for cutomer " + Thread.currentThread().getId() + " has finished Processing");
		}
		
		locker.release();	

	}

	public void deliverFood(){
		try {
			locker.acquire();
			System.out.println("Customer's " + Thread.currentThread().getId() + " food is being delieverd.");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Customer's " + Thread.currentThread().getId() + " food has been delivered.");
		locker.release();
	}

}
