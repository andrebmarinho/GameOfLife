package br.unb.cic.gameoflife.domain;

public class Originator {
	private static Originator instance;
	private Cell cells[][] = null;
	private int height;
	private int width;
	private int revivedCells;
	private int killedCells;
	private int createdCells;

	private Originator() {	}

	public static synchronized Originator instance() {
		if (instance == null) {
			instance = new Originator();
		}
		return instance;
	}

	public void setOriginator(Cell cells[][], int height, int width,
			int revivedCells, int killedCells, int createdCells) {
		this.height = height;
		this.width = width;
		this.revivedCells = revivedCells;
		this.killedCells = killedCells;
		this.createdCells = createdCells;


		this.cells = new Cell[height][width];
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				this.cells[i][j] = new Cell(cells[i][j].getStatus());
			}
		}
	}

	public Memento save() {
		Statistics s = Statistics.instance();
		return new Memento(cells, height, width, s.getRevivedCells(),
				s.getKilledCells(), s.getCreatedCells());
	}

	public void undoToLastSave(Object obj) {
		Memento memento = (Memento) obj;
		this.height = memento.height;
		this.width = memento.width;
		this.revivedCells = memento.revivedCells;
		this.killedCells = memento.killedCells;
		this.createdCells = memento.createdCells;

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				this.cells[i][j] = new Cell(memento.cells[i][j].getStatus());
			}
		}
	}

	public Cell[][] getCells() {
		return cells;
	}
	
	public int getRevivedCells() {
		return revivedCells;
	}
	
	public int getKilledCells() {
		return killedCells;
	}
	
	public int getCreatedCells() {
		return createdCells;
	}
	
	private class Memento {
		private Cell cells[][];
		private int height;
		private int width;
		private int revivedCells;
		private int killedCells;
		private int createdCells;

		public Memento(Cell cells[][], int height, int width, int revivedCells,
				int killedCells, int createdCells) {
			this.height = height;
			this.width = width;
			this.revivedCells = revivedCells;
			this.killedCells = killedCells;
			this.createdCells = createdCells;

			this.cells = new Cell[height][width];
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					this.cells[i][j] = new Cell(cells[i][j].getStatus());
				}
			}
		}
	}
}
