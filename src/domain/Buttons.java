package domain;

import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class Buttons {
	private Button bStats;
	private ImageButton bClear;
	private ImageButton bPause;
	private ImageButton bAuto;
	private ImageButton bNext;
	private ImageButton bUndo;
	private ImageButton bRedo;

	public Buttons(Button bStats, ImageButton bClear, ImageButton bPause,
			ImageButton bAuto, ImageButton bNext, ImageButton bUndo,
			ImageButton bRedo) {

		this.bStats = bStats;
		this.bClear = bClear;
		this.bPause = bPause;
		this.bAuto = bAuto;
		this.bNext = bNext;
		this.bUndo = bUndo;
		this.bRedo = bRedo;
	}

	public void setListeners(OnClickListener onClickListener) {
		bStats.setOnClickListener(onClickListener);
		bClear.setOnClickListener(onClickListener);
		bPause.setOnClickListener(onClickListener);
		bAuto.setOnClickListener(onClickListener);
		bNext.setOnClickListener(onClickListener);
		bUndo.setOnClickListener(onClickListener);
		bRedo.setOnClickListener(onClickListener);
	}
}