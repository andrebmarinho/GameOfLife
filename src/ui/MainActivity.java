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
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.GridView;

public class MainActivity extends Activity {

	Status status;
	GridView gridView;
	ImgAdapter adapter;
	GameController controller;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {         

       super.onCreate(savedInstanceState);    
       setContentView(R.layout.main_game_view);
         
       gridView = (GridView) findViewById(R.id.view); 
       adapter = new ImgAdapter(this);
       gridView.setAdapter(adapter);
       
       Main.instance().startGame();
       controller = Main.instance().getController();
       
       gridView.setOnTouchListener(new OnTouchListener() {
           @SuppressLint("ClickableViewAccessibility") public boolean onTouch(View v, MotionEvent me) {
               
        	   int x = (int) me.getX();
        	   int y = (int) me.getY();               
               int[] coord = ImgSwitch.instance().convertPositionToCoordinates( gridView.pointToPosition(x, y) );
               controller.makeCellAlive(coord[0], coord[1]);
               updateView();
               return true;
           }
		
       });
              
    }
	
	private void updateView() {
		adapter.notifyDataSetChanged();
	}
		 
}


