import java.util.concurrent.Semaphore;

public class ParkingSpace {
	private static int NUM_OF_PARKINGSPACE = 10;
	
	private static Semaphore locker = new Semaphore(NUM_OF_PARKINGSPACE);
	
	public void takeParkingSpace(){
		try {
			locker.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void leaveParkingSpace(){
		locker.release();
	}

}
