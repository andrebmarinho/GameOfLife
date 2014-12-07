package domain;

import ui.MainActivity;

public class AutoRun extends MainActivity implements Runnable {
	
	/*private boolean run = true;
	
	public void turnOff() {
        this.run = false;
    }*/
	
	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()){
		   if(controller != null){
			   controller.nextGeneration();
			   this.updateUi();
			   
			   try {
				Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				}
			   
		   }
		  // if (!run) break;
	   }
	}
	
	public void updateUi(){
		
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				updateView();
			}
		});
		
	}

	
	
}
