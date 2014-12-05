package domain;

public class Main {

	Statistics statistics;
	GameController controller;
	GameEngine engine;
	
	private static Main instance;
	
	public static Main instance() {
		if(instance == null) {
			instance = new Main();
		}
		return instance; 
	}
	
	private Main(){		
	}
	
	public void startGame() {
		setUp();
		controller.start();
	}

	public void setUp() {
		controller = new GameController();		
		statistics = new Statistics();		
		engine = new GameEngine(10, 10, statistics);
		controller.setEngine(engine);
		controller.setStatistics(statistics);
	}

	public Statistics getStatistics() {
		return statistics;
	}

	public GameController getController() {
		return controller;
	}

	public GameEngine getEngine() {
		return engine;
	}
	
}
