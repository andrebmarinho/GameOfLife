package domain;

import ui.MainActivity;

public class AutoRun extends MainActivity implements Runnable {
	
	private boolean run = true;
	
	public void turnOff() {
        this.run = false;
    }
	
	@Override
	public void run() {
		while(true){
		   controller.nextGeneration();
		   updateView();
		   if (!run) break;
	   }
	}

	
	
}
