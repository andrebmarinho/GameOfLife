package domain;

//Singleton respons�vel pelas mudan�as das imagens.

import com.example.gameoflife.R;

public class ImgSwitch {
	private static Integer[] imgArray = new Integer[100];
	private static ImgSwitch instance;

	public static synchronized ImgSwitch instance() {
		if (instance == null) {
			instance = new ImgSwitch();
		}
		return instance;
	}

	// Início com mapa zerado.
	private ImgSwitch() {
		startOrRestartArray();
	}

	public void startOrRestartArray() {
		for (int i = 0; i < 100; i++)
			imgArray[i] = R.drawable.dead;
	}

	public void setImgArray(Status status, int position) {
		switch (status) {
		case Dead:
			imgArray[position] = R.drawable.dead;
			break;
		case Alive:
			imgArray[position] = R.drawable.alive;
			break;
		}
	}

	public Integer[] getImgArray() {
		return imgArray;
	}

	public int convertCoordinatesToPosition(int i, int j) {
		return (i * 10 + (j));
	}

	public int[] convertPositionToCoordinates(int pos) {
		int[] coord = new int[2];

		coord[0] = (int) (pos / 10);
		coord[1] = pos % 10;

		return coord;
	}
}