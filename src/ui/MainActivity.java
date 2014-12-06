package ui;

//Classe Main - é a atividade responsável pela "View" no padrão MVC.

import com.example.gameoflife.R;

import domain.ImgAdapter;
import domain.ImgSwitch;
import domain.Main;
import domain.Status;
import domain.GameController;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.GridView;

public class MainActivity extends Activity implements SurfaceHolder.Callback {

	Status status;
	GridView gridView;
	ImgAdapter adapter;
	GameController controller;
	Button bPause;
	Button bAuto;
	Button bNext;
	Button bUndo;
	Button bRedo;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {         
		
        super.onCreate(savedInstanceState);    
        setContentView(R.layout.main_game_view);
       
        /*bPause = (Button)findViewById(R.id.b_pause);
   		bAuto = (Button)findViewById(R.id.b_play);
   	 	bNext = (Button)findViewById(R.id.b_next);
   	 	bUndo = (Button)findViewById(R.id.b_undo);
   	 	bRedo = (Button)findViewById(R.id.b_redo);
   	 	bPause.setOnClickListener(onClickListener);
   	 	bAuto.setOnClickListener(onClickListener);
   	 	bNext.setOnClickListener(onClickListener);
   	 	bUndo.setOnClickListener(onClickListener);
   	 	bRedo.setOnClickListener(onClickListener);*/
       
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
	
	private OnClickListener onClickListener = new OnClickListener() {
	    @Override
	    public void onClick(final View v) {
	        switch(v.getId()){
	            case R.id.b_undo:
	                 //DO something
	        break;
	        case R.id.b_redo:
	             //DO something
	        break;
	        case R.id.b_pause:
	             //DO something
	        break;
	        case R.id.b_play:
	             //DO something
	        break;
	        case R.id.b_next:
	             //DO something
	            break;
	        }
	    }
	};
	
	private void updateView() {
		adapter.notifyDataSetChanged();
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
		 
}


