package domain;

import java.security.InvalidParameterException;

/**
 * Classe que atua como um controlador do padr�o MVC, separando os componentes
 * da camada de apresentacao e model.
 * 
 * @author rbonifacio
 */
public class GameController {
	private GameEngine engine;

	public GameController(int height, int width) {
		this.engine = new GameEngine(height, width);
	}

	public void makeCellAlive(int i, int j) {
		try {
			engine.makeCellAlive(i, j);
			changeCellsStatus();
		} catch (InvalidParameterException e) {
			// TODO:tratar aqui
		}
	}

	public void nextGeneration() {
		engine.nextGeneration();
		changeCellsStatus();
	}

	public void changeCellsStatus() {
		int position;
		Cell[][] oldCells = engine.getOldCells();
		Cell[][] cells = engine.getCells();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (oldCells[i][j].getStatus() != cells[i][j].getStatus()) {
					position = ImgSwitch.instance()
							.convertCoordinatesToPosition(i, j);
					ImgSwitch.instance().setImgArray(cells[i][j].getStatus(),
							position);
					oldCells[i][j].setStatus(cells[i][j].getStatus());
					engine.setOldCells(oldCells);
				}
			}
		}
	}

	public void resetGame() {
		this.engine = null;
		this.engine = new GameEngine(10, 10);
		Statistics.instance().reset();
	}
}