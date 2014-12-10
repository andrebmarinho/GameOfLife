package br.unb.cic.gameoflife.domain;

import br.unb.cic.gameoflife.ui.MainActivity;

public class AutoRun extends MainActivity implements Runnable {
	@Override
	public void run() {
		while (true) {
			controller.nextGeneration();
			this.updateUi();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				break;
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
