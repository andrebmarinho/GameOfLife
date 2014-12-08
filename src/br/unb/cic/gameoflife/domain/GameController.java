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
	private Caretaker undoStack;
	private Caretaker redoStack;
	
	public GameController(int height, int width) {
		this.engine = new GameEngine(height, width);
		undoStack = new Caretaker();
		redoStack = new Caretaker();
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
		Statistics s = Statistics.instance();
		Originator.instance().setOriginator(engine.getCells(),
				engine.getHeight(), engine.getWidth(), s.getRevivedCells(),
				s.getKilledCells(), s.getCreatedCells());
		undoStack.save(Originator.instance());
		redoStack.undo(Originator.instance());
		engine.nextGeneration();
		updateCellsStatus();
	}

	public void undoGeneration() {
		if (undoStack.isEmpty()) {
			return;
		}
		
		redoStack.save(Originator.instance());
		undoStack.undo(Originator.instance());
		Statistics s = Statistics.instance();
		Originator origin = Originator.instance();
		s.setStatistics(origin.getRevivedCells(), origin.getKilledCells(),
				origin.getCreatedCells());
		engine.setCells(origin.getCells());
		engine.setOldCells(origin.getCells());
		updateCellsStatus();
	}
	
	public void redoGeneration() {
		if (redoStack.isEmpty()) {
			return;
		}
		
		redoStack.undo(Originator.instance());
		Statistics s = Statistics.instance();
		Originator origin = Originator.instance();
		s.setStatistics(origin.getRevivedCells(), origin.getKilledCells(),
				origin.getCreatedCells());
		engine.setCells(origin.getCells());
		engine.setOldCells(origin.getCells());
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
		undoStack.clear();
	}
}