package interfaces;

import models.Mover;

public interface AquireResourceListener {

	public void onResourceAquired(Mover customer);
	public void onResourceLocked(Mover customer);
	
}
