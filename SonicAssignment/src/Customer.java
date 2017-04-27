import java.util.Random;

public class Customer implements Runnable {
	
	private static Random gen = new Random();
	ParkingSpace space = new ParkingSpace();
	Employee employee = new Employee();

	@Override
	public void run() {
		System.out.println("Customer " + Thread.currentThread().getId() + " is looking for a parking spot.");
		space.takeParkingSpace();
		System.out.println("Customer " + Thread.currentThread().getId() + " found a parking space.");
		employee.takeOrder();
		employee.processCustOrder();
		employee.deliverFood();
		System.out.println("Customer " + Thread.currentThread().getId() + " eat it's food.");
		eatFood();
		space.leaveParkingSpace();
		System.out.println("Customer " + Thread.currentThread().getId() +" leaves it's parking space");
		
		
		
		
	}

	public void eatFood(){
		try {
			Thread.sleep(gen.nextInt(3000)+ 2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
