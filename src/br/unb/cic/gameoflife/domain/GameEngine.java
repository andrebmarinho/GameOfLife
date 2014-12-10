package br.unb.cic.gameoflife.domain;

import java.security.InvalidParameterException;

public class GameEngine {
	private int height;
	private int width;
	private Cell[][] cells;
	private Cell[][] oldCells;
	private Caretaker undoStack;
	private Caretaker redoStack;

	/**
	 * Construtor da classe Environment.
	 * 
	 * @param height
	 *            dimensao vertical do ambiente
	 * @param width
	 *            dimentsao horizontal do ambiente
	 */
	public GameEngine(int height, int width) {
		this.height = height;
		this.width = width;

		cells = new Cell[height][width];
		oldCells = new Cell[height][width];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				cells[i][j] = new Cell();
				oldCells[i][j] = new Cell();
			}
		}
		undoStack = new Caretaker();
		redoStack = new Caretaker();
	}

	/**
	 * Calcula uma nova geracao do ambiente. Essa implementacao utiliza o
	 * algoritmo do Conway, ou seja:
	 * 
	 * a) uma celula morta com exatamente tres celulas vizinhas vivas se torna
	 * uma celula viva.
	 * 
	 * b) uma celula viva com duas ou tres celulas vizinhas vivas permanece
	 * viva.
	 * 
	 * c) em todos os outros casos a celula morre ou continua morta.
	 */
	public void nextGeneration() {
		Statistics s = Statistics.instance();
		Originator origin = Originator.instance();
		origin.setOriginator(cells, height, width, s.getRevivedCells(),
				s.getKilledCells(), s.getCreatedCells());
		undoStack.save(Originator.instance());
		redoStack.undo(Originator.instance());

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (shouldLive(i, j)) {
					if (!cells[i][j].isAlive()) {
						cells[i][j].revive();
						Statistics.instance().recordRevive();
					}
				} else if (cells[i][j].isAlive()) {
					cells[i][j].kill();
					Statistics.instance().recordKill();
				}
			}
		}

		// Tem que ser em um for separado pois mudar OldCells durante o loop
		// anterior afeta na logica do jogo
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				oldCells[i][j].setStatus(cells[i][j].getStatus());
			}
		}
	}

	public void undoGeneration() {
		if (undoStack.isEmpty()) {
			return;
		}
		
		Statistics s = Statistics.instance();
		Originator origin = Originator.instance();
		origin.setOriginator(cells, height, width, s.getRevivedCells(),
				s.getKilledCells(), s.getCreatedCells());
		redoStack.save(origin);
		undoStack.undo(origin);
		s.setStatistics(origin.getRevivedCells(), origin.getKilledCells(),
				origin.getCreatedCells());
		setCells(origin.getCells());
		setOldCells(origin.getCells());
	}
	

	public void redoGeneration() {
		if (redoStack.isEmpty()) {
			return;
		}
		
		Statistics s = Statistics.instance();
		Originator origin = Originator.instance();
		undoStack.save(origin);
		redoStack.undo(origin);
		s.setStatistics(origin.getRevivedCells(), origin.getKilledCells(),
				origin.getCreatedCells());
		setCells(origin.getCells());
		setOldCells(origin.getCells());
	}
	
	/**
	 * Torna a celula de posicao (i, j) viva
	 * 
	 * @param i
	 *            posicao vertical da celula
	 * @param j
	 *            posicao horizontal da celula
	 * 
	 * @throws InvalidParameterException
	 *             caso a posicao (i, j) nao seja valida.
	 */
	public void makeCellAlive(int i, int j) throws InvalidParameterException {
		if (validPosition(i, j) && (!cells[i][j].isAlive())) {
			cells[i][j].revive();
			oldCells[i][j].revive();
			Statistics.instance().recordCreatedCells();
		} else {
			new InvalidParameterException("Invalid position (" + i + ", " + j
					+ ")");
		}
	}

	private boolean shouldLive(int i, int j) {
		return ((numberOfNeighborhoodAliveCells(i, j) == 2) || (numberOfNeighborhoodAliveCells(
				i, j) == 3));
	}

	/*
	 * Computa o numero de celulas vizinhas vivas, dada uma posicao no ambiente
	 * de referencia identificada pelos argumentos (i,j).
	 */
	private int numberOfNeighborhoodAliveCells(int i, int j) {
		int alive = 0;

		for (int a = 0; a < height; a++) {
			for (int b = 0; b < width; b++) {
				if ((validPosition(a, b)) &&
						(oldCells[a][b].isAlive()) && (!(a == i && b == j)) &&
						((a >= i - 1 && a <= i + 1 && b >= j - 1 && b <= j + 1)
						|| ((a >= i - 1 && a <= i + 1) &&
						((b == 0 && j == width - 1) ||
						(b == width - 1 && j == 0))) ||
						((b >= j - 1 && b <= j + 1) &&
						((a == 0 && i == height - 1) ||
						(a == height - 1 && i == 0))))) {
					alive++;
				}
			}
		}

		return alive;
	}

	/*
	 * Verifica se uma posicao (a, b) referencia uma celula valida no tabuleiro.
	 */
	private boolean validPosition(int a, int b) {
		if ((a >= 0) && (a < height) && (b >= 0) && (b < width)) {
			return true;
		}
		return false;
	}

	public void setCells(Cell cells[][]) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				this.cells[i][j] = new Cell(cells[i][j].getStatus());
			}
		}
	}

	public void setOldCells(Cell cells[][]) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				this.oldCells[i][j] = new Cell(cells[i][j].getStatus());
			}
		}
	}
	
	public void clearCaretakers() {
		undoStack.clear();
		redoStack.clear();
	}
	
	public Cell[][] getCells() {
		return cells;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
}