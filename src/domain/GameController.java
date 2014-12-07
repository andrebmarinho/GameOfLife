package domain;

import java.security.InvalidParameterException;

/**
 * Classe que atua como um controlador do 
 * padr�o MVC, separando os componentes da 
 * camada de apresentacao e model. 
 * 
 * @author rbonifacio
 */
public class GameController {
	private GameEngine engine;
	
	public GameEngine getEngine() {
		return engine;
	}
	
	public void setEngine(GameEngine engine) {
		this.engine = engine;
	}
		
	public void setStatistics(int revivedCells, int killedCells) {
		Statistics.instance().setStatistics(revivedCells, killedCells);
	}
	
	public void start() {
	}
	
	public void halt() {
		//oops, nao muito legal fazer sysout na classe Controller
		//System.out.println("\n \n");
		//TODO: display de estat�sticas
		Statistics.instance().display();
		System.exit(0);
	}
	
	public void makeCellAlive(int i, int j) {
		try {
			engine.makeCellAlive(i, j);
			changeCellsStatus();
		} catch(InvalidParameterException e) {
			//TODO:tratar aqui 
		}
	}
	
	public void nextGeneration() {
		engine.nextGeneration();
		changeCellsStatus();
	}
			
	public void changeCellsStatus(){
		
		int position;
		Cell[][] oldCells = engine.getOldCells();
		Cell[][] cells = engine.getCells();
		for(int i = 0; i < 10; i++)
			for(int j = 0; j < 10; j++){				
				if(oldCells[i][j].getStatus() != cells[i][j].getStatus()){
					position = ImgSwitch.instance().convertCoordinatesToPosition(i, j);
					ImgSwitch.instance().setImgArray(cells[i][j].getStatus(), position);
					oldCells[i][j].setStatus(cells[i][j].getStatus());
					engine.setOldCells(oldCells);
				}				
			}		
	}	
}
