import java.util.concurrent.Semaphore;

public class Employee {

	private static int NUM_OF_EMPLOYEES =5;
<<<<<<< HEAD
	PaymentSystem system = new PaymentSystem();
=======
	private PaymentSystem paymentSystem = new PaymentSystem();
>>>>>>> 4f4bb4ae483a7801b8f4a30af169285e2c0faf6e

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
<<<<<<< HEAD
		synchronized(system){
=======
		synchronized(paymentSystem){
>>>>>>> 4f4bb4ae483a7801b8f4a30af169285e2c0faf6e
			try {
				System.out.println("Thread : " +Thread.currentThread().getId() + "uses the PaymentSystem ");
				Thread.sleep(1000);
				System.out.println("Processing Order for customer " + Thread.currentThread().getId());
<<<<<<< HEAD
				system.use();
=======
				paymentSystem.setPaymentSystem(false);
				System.out.println("Thread : " +Thread.currentThread().getId() + "drops the PaymentSystem ");
>>>>>>> 4f4bb4ae483a7801b8f4a30af169285e2c0faf6e
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
<<<<<<< HEAD
			System.out.println("Order for cutomer " + Thread.currentThread().getId() + " has finished Processing");
		}
		
		locker.release();	

	}

=======


			System.out.println("Order for cutomer " + Thread.currentThread().getId() + " has finished Processing");

			locker.release();
		}
	}


>>>>>>> 4f4bb4ae483a7801b8f4a30af169285e2c0faf6e
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
