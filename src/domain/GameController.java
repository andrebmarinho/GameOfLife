package domain;

import java.security.InvalidParameterException;

/**
 * Classe que atua como um controlador do 
 * padrão MVC, separando os componentes da 
 * camada de apresentacao e model. 
 * 
 * @author rbonifacio
 */
public class GameController {

	private GameEngine engine;
	private boolean update;
	private Statistics statistics;
	//private boolean pause = false;
	//private boolean auto = true;	
	
	public GameEngine getEngine() {
		return engine;
	}
	
	public void setEngine(GameEngine engine) {
		this.engine = engine;
	}
		
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}
	
	public void start() {
		update = false;
		nextGeneration();
	}
	
	public void halt() {
		//oops, nao muito legal fazer sysout na classe Controller
		//System.out.println("\n \n");
		//TODO: display de estatísticas
		statistics.display();
		System.exit(0);
	}
	
	public void makeCellAlive(int i, int j) {
		try {
			engine.makeCellAlive(i, j);
		} catch(InvalidParameterException e) {
			//TODO:tratar aqui 
		}
	}
	
	public void nextGeneration() {
		update = false;
		engine.nextGeneration();
		changeCellsStatus(engine.getCells());
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}
	
	public boolean getUpdate() {
		return update;
	}
			
	public void changeCellsStatus(Cell[][] oldCells){
		
		int position;
		Cell[][] cells = engine.getCells();
		for(int i = 0; i < 10; i++)
			for(int j = 0; j < 10; j++){				
				if(oldCells[i][j].getStatus() != cells[i][j].getStatus()){
					position = ImgSwitch.instance().convertCoordinatesToPosition(i, j);
					ImgSwitch.instance().setImgArray(cells[i][j].getStatus(), position);
					update = true;
				}				
			}		
		
	}
	
}
