import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	private static int NUM_CUSTOMERS = 2;
	
	private static ExecutorService service = Executors.newFixedThreadPool(NUM_CUSTOMERS);

	
	public static void main(String[] args){
		for (int i = 0; i < NUM_CUSTOMERS; i++) {
			service.submit(new Customer());
		}
		
	}
}
