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
		//TODO: display de estatística
		statistics.display();
		System.exit(0);
	}
	
	public void makeCellAlive(int i, int j) {
		try {
			engine.makeCellAlive(i, j);
			checkCellsPositions(engine.getCells());
		}
		catch(InvalidParameterException e) {
			//TODO:tratar aqui System.out.println(e.getMessage());
		}
	}
	
	public void nextGeneration() {
		engine.nextGeneration();
		checkCellsPositions(engine.getCells());
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}
	
	public boolean getUpdate() {
		return update;
	}
	
	//Comunicação: controller recebe as informações da engine 
	//e prepara a view para ser atualizada.
	public void checkCellsPositions(Cell[][] oldCells){
		
		int position;
		Cell[][] cells = engine.getCells();
		for(int i = 0; i < 10; i++)
			for(int j = 0; j < 10; j++){				
				if(oldCells[i][j] != cells[i][j]){
					position = engine.convertCoordinatesToPosition(i, j);
					ImgSwitch.instance().setImgArray(cells[i][j].getStatus(), position);
					update = true;
				}				
			}		
		
	}
	
}
