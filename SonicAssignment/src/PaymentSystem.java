
public class PaymentSystem {
<<<<<<< HEAD

	
	
	public void use(){
		System.out.println( "Thread " + Thread.currentThread().getId() + " is using the payment system");
=======
	private boolean inUse = false;
	
	
	public boolean getPaymentSystem(){
		return this.inUse;
	}
	public void setPaymentSystem(boolean toPass){
		this.inUse = toPass;
>>>>>>> 4f4bb4ae483a7801b8f4a30af169285e2c0faf6e
	}
}
