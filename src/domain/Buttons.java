package domain;

import android.widget.Button;
import android.widget.ImageButton;

public class Buttons {

	private ImageButton bClear;
	private ImageButton bPause;
	private ImageButton bAuto;
	private ImageButton bNext;
	private ImageButton bUndo;
	private ImageButton bRedo;
	private Button bStats;
	private Button bClosePop;
	
	public Buttons(
			Button bStats,
			ImageButton bClear,
			ImageButton bPause, 
			ImageButton bAuto, 
			ImageButton bNext, 
			ImageButton bUndo, 
			ImageButton bRedo ){

		this.setbStats(bStats);
		this.setbClear(bClear);
		this.setbPause(bPause);
		this.setbAuto(bAuto);
		this.setbNext(bNext);
		this.setbUndo(bUndo);
		this.setbRedo(bRedo);
		
	}
	
	public Buttons(	Button bClosePop ){

		this.setbClosePop(bClosePop);
		
	}

	public Button getbClosePop() {
		return bClosePop;
	}

	public void setbClosePop(Button bClosePop) {
		this.bClosePop = bClosePop;
	}
	
	public Button getbStats() {
		return bStats;
	}

	public void setbStats(Button bStats) {
		this.bStats = bStats;
	}
	
	public ImageButton getbClear() {
		return bClear;
	}

	public void setbClear(ImageButton bClear) {
		this.bClear = bClear;
	}

	public ImageButton getbPause() {
		return bPause;
	}

	public void setbPause(ImageButton bPause) {
		this.bPause = bPause;
	}

	public ImageButton getbAuto() {
		return bAuto;
	}

	public void setbAuto(ImageButton bAuto) {
		this.bAuto = bAuto;
	}

	public ImageButton getbNext() {
		return bNext;
	}

	public void setbNext(ImageButton bNext) {
		this.bNext = bNext;
	}

	public ImageButton getbUndo() {
		return bUndo;
	}

	public void setbUndo(ImageButton bUndo) {
		this.bUndo = bUndo;
	}

	public ImageButton getbRedo() {
		return bRedo;
	}

	public void setbRedo(ImageButton bRedo) {
		this.bRedo = bRedo;
	}
	
}
