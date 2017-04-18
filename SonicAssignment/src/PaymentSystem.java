
public class PaymentSystem {
	private boolean inUse = false;
	
	
	public boolean getPaymentSystem(){
		return this.inUse;
	}
	public void setPaymentSystem(boolean toPass){
		this.inUse = toPass;
	}
}
