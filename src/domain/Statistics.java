package domain;

import domain.Statistics;

/**
 * Essa tambem eh uma classe com baixa coesao, 
 * pois mustura caracteristicas de Model (as propriedades) 
 * com caracteristicas de view (metodo display())
 * 
 * Nao eh uma boa implementacao.
 * 
 * @author rodrigobonifacio
 */
public class Statistics {
	private static Statistics instance;
	private int revivedCells;
	private int killedCells;
	
	private Statistics() {
		revivedCells = 0;
		killedCells = 0;
	}
	
	public static synchronized Statistics instance() {
		if (instance == null) {
			instance = new Statistics();
		}
		return instance;
	}
	
	public void setStatistics(int revivedCells, int killedCells){
		this.revivedCells = revivedCells;
		this.killedCells = killedCells;
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
	
	//TODO: mudar o display para a view.
	public void display() {
		System.out.println("=================================");
		System.out.println("           Statistics            ");
		System.out.println("=================================");
		System.out.println("Revived cells: " + revivedCells);
		System.out.println("Killed cells: " + killedCells);
		System.out.println("=================================");
	}

}

