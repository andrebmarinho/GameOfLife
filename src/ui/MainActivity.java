package ui;

//Classe Main - � a atividade respons�vel pela "View" no padr�o MVC.

import com.example.gameoflife.R;

import domain.AutoRun;
import domain.Buttons;
import domain.ImgAdapter;
import domain.ImgSwitch;
import domain.Statistics;
import domain.Status;
import domain.GameController;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	Status status;
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
				Toast.makeText(MainActivity.this, "Pause", Toast.LENGTH_SHORT)
						.show();
				if (auto != null) {
					auto.interrupt();
					auto = null;
				}
				break;

			case R.id.b_play:
				Toast.makeText(MainActivity.this, "Play", Toast.LENGTH_SHORT)
						.show();

				if (auto == null) {
					auto = new Thread(new AutoRun());
					auto.start();
				}
				break;

			case R.id.b_next:
				Toast.makeText(MainActivity.this, "Next", Toast.LENGTH_SHORT)
						.show();
				controller.nextGeneration();
				updateView();
				break;

			case R.id.b_undo:
				Toast.makeText(MainActivity.this, "Undo", Toast.LENGTH_SHORT)
						.show();
				break;

			case R.id.b_redo:
				Toast.makeText(MainActivity.this, "Redo", Toast.LENGTH_SHORT)
						.show();
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