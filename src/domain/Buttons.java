package domain;

import android.widget.ImageButton;

public class Buttons {

	private ImageButton bPause;
	private ImageButton bAuto;
	private ImageButton bNext;
	private ImageButton bUndo;
	private ImageButton bRedo;
	
	public Buttons(
			ImageButton bPause, 
			ImageButton bAuto, 
			ImageButton bNext, 
			ImageButton bUndo, 
			ImageButton bRedo ){
		
		this.setbPause(bPause);
		this.setbAuto(bAuto);
		this.setbNext(bNext);
		this.setbUndo(bUndo);
		this.setbRedo(bRedo);
		
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
