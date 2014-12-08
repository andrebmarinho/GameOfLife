package br.unb.cic.gameoflife.domain;


public class Statistics {
	private static Statistics instance;
	private int revivedCells;
	private int killedCells;
	private int createdCells;

	private Statistics() {
		reset();
	}

	public static synchronized Statistics instance() {
		if (instance == null) {
			instance = new Statistics();
		}
		return instance;
	}

	public void reset() {
		this.revivedCells = 0;
		this.killedCells = 0;
		this.createdCells = 0;
	}

	public int getRevivedCells() {
		return revivedCells;
	}

	public void recordRevive() {
		this.revivedCells++;
	}

	public int getKilledCells() {
		return killedCells;
	}

	public void recordKill() {
		this.killedCells++;
	}

	public int getCreatedCells() {
		return createdCells;
	}

	public void recordCreatedCells() {
		this.createdCells++;
	}
}