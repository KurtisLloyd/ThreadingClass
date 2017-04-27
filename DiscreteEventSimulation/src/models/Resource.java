package models;

public class Resource {

	private Mover currentMover;

	public synchronized void lockResource(Mover m){
		if(currentMover == null){
			this.currentMover = m;
		}
	}

	public synchronized void releaseResource(Mover m){
		if(m.equals(currentMover)){
			this.currentMover = null;
		}
	}
	
	public synchronized boolean isLockedByMe(Mover m){
		
		boolean isLocked = false;
		
		if(currentMover != null){
			isLocked = currentMover.equals(m);
		}
		
		return isLocked;
	}
	
	public synchronized boolean canGetResource(){
		return currentMover == null;
	}

	public synchronized boolean isBusy() {
		return currentMover != null;
	}
	
}
