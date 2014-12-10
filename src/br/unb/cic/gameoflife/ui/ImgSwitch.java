package br.unb.cic.gameoflife.ui;

//Singleton responsável pelas mudanças das imagens.

import br.unb.cic.gameoflife.R;
import br.unb.cic.gameoflife.domain.Status;

public class ImgSwitch {
	private static int height;
	private static int width;
	private static Integer[] imgArray;
	private static ImgSwitch instance;

	public static synchronized ImgSwitch instance() {
		if (instance == null) {
			instance = new ImgSwitch();
		}
		return instance;
	}

	// Início com mapa zerado.
	private ImgSwitch() {
		height = 10;
		width = 10;
		imgArray = new Integer[height * width];
		startOrRestartArray();
	}

	public void startOrRestartArray() {
		for (int i = 0; i < height * width; i++)
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
		return (i * width + (j));
	}

	public int[] convertPositionToCoordinates(int pos) {
		int[] coord = new int[2];

		coord[0] = (int) (pos / height);
		coord[1] = pos % width;

		return coord;
	}
}