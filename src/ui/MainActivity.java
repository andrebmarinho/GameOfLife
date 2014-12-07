package ui;

//Classe Main - é a atividade responsável pela "View" no padrão MVC.

import com.example.gameoflife.R;

import domain.Buttons;
import domain.ImgAdapter;
import domain.ImgSwitch;
import domain.Main;
import domain.Status;
import domain.GameController;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends Activity {

	Status status;
	GridView gridView;
	ImgAdapter adapter;
	GameController controller;
	Buttons buttons;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {         
		
       super.onCreate(savedInstanceState);    
       setContentView(R.layout.main_game_view);
       
       setButtons();    
    	   
	   gridView = (GridView) findViewById(R.id.view); 
	   adapter = new ImgAdapter(this);
	   gridView.setAdapter(adapter);
	   
	   Main.instance().startGame();
	   controller = Main.instance().getController();
	   
	   gridView.setOnTouchListener(new OnTouchListener() {
	       @SuppressLint("ClickableViewAccessibility") public boolean onTouch(View v, MotionEvent me) {
	           int[] coord = ImgSwitch.instance().convertPositionToCoordinates( gridView.pointToPosition((int) me.getX(), (int) me.getY()) );
	           controller.makeCellAlive(coord[0], coord[1]);
	           updateView();
	           return true;
	       }
		
	   });
	       
	}
	
	private void setButtons() {
		buttons = new Buttons(
				(ImageButton)findViewById(R.id.b_pause),
				(ImageButton)findViewById(R.id.b_play),
				(ImageButton)findViewById(R.id.b_next),
				(ImageButton)findViewById(R.id.b_undo),
				(ImageButton)findViewById(R.id.b_redo)
				);

		buttons.getbPause().setOnClickListener(onClickListener);
		buttons.getbAuto().setOnClickListener(onClickListener);
		buttons.getbNext().setOnClickListener(onClickListener);
		buttons.getbUndo().setOnClickListener(onClickListener);
		buttons.getbRedo().setOnClickListener(onClickListener);	
	}	
	
	private OnClickListener onClickListener = new OnClickListener() {
	    @Override
	    public void onClick(final View v) {
		    	switch(v.getId()){
		        case R.id.b_undo:
		        		Toast.makeText(MainActivity.this, "Undo",
							Toast.LENGTH_SHORT).show();
		        	break;
			    case R.id.b_redo:
			    	Toast.makeText(MainActivity.this, "Redo",
							Toast.LENGTH_SHORT).show();
			    	break;
			    case R.id.b_pause:
			    	Toast.makeText(MainActivity.this, "Pause",
							Toast.LENGTH_SHORT).show();
			    	controller.setAuto(false);
			    	break;
			    case R.id.b_play:
			    	Toast.makeText(MainActivity.this, "Play",
							Toast.LENGTH_SHORT).show();
			    	controller.setAuto(true);
			    	break;
			    case R.id.b_next:
			    	Toast.makeText(MainActivity.this, "Next",
							Toast.LENGTH_SHORT).show();
			    	controller.nextGeneration();
			    	updateView();
			        break;
		    }
	    }
	};
		
	private void updateView() {
		adapter.notifyDataSetChanged();
	}
			 
}


