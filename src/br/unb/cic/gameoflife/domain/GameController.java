package br.unb.cic.gameoflife.domain;

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
			updateCellsStatus();
		} catch (InvalidParameterException e) {
			throw new RuntimeException("Célula inexistente");
		}
	}

	public void nextGeneration() {
		engine.nextGeneration();
		updateCellsStatus();
	}

	public void updateCellsStatus() {
		int position;
		Cell[][] cells = engine.getCells();

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				position = ImgSwitch.instance().convertCoordinatesToPosition(i,
						j);
				ImgSwitch.instance().setImgArray(cells[i][j].getStatus(),
						position);
			}
		}
	}

	public void resetGame() {
		this.engine = new GameEngine(10, 10);
		Statistics.instance().reset();
	}
}