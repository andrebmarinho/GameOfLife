package br.unb.cic.gameoflife.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import br.unb.cic.gameoflife.R;
import br.unb.cic.gameoflife.domain.AutoRun;
import br.unb.cic.gameoflife.domain.Buttons;
import br.unb.cic.gameoflife.domain.GameController;
import br.unb.cic.gameoflife.domain.Statistics;

public class MainActivity extends Activity {
	GridView gridView;
	protected static ImgAdapter adapter;
	protected static GameController controller;
	Buttons buttons;
	Thread auto = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_game_view);

		setButtons();

		gridView = (GridView) findViewById(R.id.view);
		adapter = new ImgAdapter(this);
		gridView.setAdapter(adapter);

		controller = new GameController(10, 10);

		gridView.setOnTouchListener(new OnTouchListener() {
			@SuppressLint("ClickableViewAccessibility")
			public boolean onTouch(View v, MotionEvent me) {
				int[] coord = ImgSwitch.instance()
						.convertPositionToCoordinates(
								gridView.pointToPosition((int) me.getX(),
										(int) me.getY()));
				controller.makeCellAlive(coord[0], coord[1]);
				updateView();
				return true;
			}

		});
	}

	private void setButtons() {
		buttons = new Buttons((Button) findViewById(R.id.b_stats),
				(ImageButton) findViewById(R.id.b_clear),
				(ImageButton) findViewById(R.id.b_pause),
				(ImageButton) findViewById(R.id.b_play),
				(ImageButton) findViewById(R.id.b_next),
				(ImageButton) findViewById(R.id.b_undo),
				(ImageButton) findViewById(R.id.b_redo));

		buttons.setListeners(onClickListener);
	}

	private OnClickListener onClickListener = new OnClickListener() {
		@Override
		public void onClick(final View v) {
			switch (v.getId()) {
			case R.id.b_stats:
				showPopup();
				break;

			case R.id.b_clear:
				controller.resetGame();
				ImgSwitch.instance().startOrRestartArray();
				updateView();
				break;

			case R.id.b_pause:
				if (auto != null) {
					auto.interrupt();
					auto = null;
				}
				break;

			case R.id.b_play:
				if (auto == null) {
					auto = new Thread(new AutoRun());
					auto.start();
				}
				break;

			case R.id.b_next:
				controller.nextGeneration();
				updateView();
				break;

			case R.id.b_undo:
				controller.undoGeneration();
				updateView();
				break;

			case R.id.b_redo:
				controller.redoGeneration();
				updateView();
				break;
			}
		}
	};

	protected void updateView() {
		adapter.notifyDataSetChanged();
	}

	private void showPopup() {
		final Dialog dialog = new Dialog(MainActivity.this);
		dialog.setContentView(R.layout.popup);
		dialog.setTitle("Estatísticas");
		dialog.setCancelable(true);

		TextView text = (TextView) dialog.findViewById(R.id.TextViewPopup);
		text.setText("Criadas:" + Statistics.instance().getCreatedCells()
				+ "\nMortas:" + Statistics.instance().getKilledCells()
				+ "\nRevividas:" + Statistics.instance().getRevivedCells());

		Button button = (Button) dialog.findViewById(R.id.b_close_popup);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		dialog.show();
	}
}