package domain;

public class Main {
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
	}
	
	public void restartGame() {
		Statistics.instance().setStatistics(0, 0);
		Statistics.instance().setCreatedCells(0);
		controller = null;
		engine = null;
		setUp();
		ImgSwitch.instance().startOrRestartArray();
	}

	public void setUp() {
		controller = new GameController();		
		engine = new GameEngine(10, 10);
		controller.setEngine(engine);
	}

	public GameController getController() {
		return controller;
	}

	public GameEngine getEngine() {
		return engine;
	}
	
}
