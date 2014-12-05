package ui;

//Classe Main - � a atividade respons�vel pela "View" no padr�o MVC.

import com.example.gameoflife.R;

import domain.AdaptadorDeImgs;
import domain.Estados;
import domain.GameController;
import domain.GameEngine;
import domain.Statistics;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends Activity {

	Statistics statistics;
	GameController controller;
	GameEngine engine;
	Estados est;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {         

       super.onCreate(savedInstanceState);    
       setContentView(R.layout.main_game_view);
         
       GridView gridView = (GridView) findViewById(R.id.view);       
       gridView.setAdapter(new AdaptadorDeImgs(this));
       
       iniciarJogo();
       
    }

	private void iniciarJogo() {
		setUp();
		controller.start();		
	}

	private void setUp() {
		controller = new GameController();		
		statistics = new Statistics();		
		engine = new GameEngine(10, 10, statistics);
		controller.setEngine(engine);
		controller.setStatistics(statistics);
	}
	 
}


