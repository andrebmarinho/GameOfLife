package domain;

import ui.MainActivity;

public class AutoRun extends MainActivity implements Runnable {
	@Override
	public void run() {
		while (true) {
			if (controller != null) {
				controller.nextGeneration();
				this.updateUi();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					break;
				}
			}
		}
	}

	public void updateUi() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				updateView();
			}
		});
	}
}
