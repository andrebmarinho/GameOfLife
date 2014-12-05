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
       
       gridView.setOnTouchListener(new OnTouchListener() {
           @SuppressLint("ClickableViewAccessibility") public boolean onTouch(View v, MotionEvent me) {
               
               int position = gridView.pointToPosition((int) me.getX(), (int) me.getY());
               ImgSwitch.instance().setImgArray(Status.Alive, position); 
               updateView();
               return true;
           }
		
       });
       
       
     //Isso na verdade vai no loop infinito do jogo.
       /*controller = Main.instance().getController();
       
       if (controller.getUpdate())
			updateView();*/
       
       
       
    }
	
	private void updateView() {
		adapter.notifyDataSetChanged();
	}
		 
}


