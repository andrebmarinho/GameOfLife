package domain;

//Singleton responsável pelas mudanças das imagens.

import com.example.gameoflife.R;


public class ImgSwitch {

	private static Integer[] imgArray = new Integer[100];
	
	private static ImgSwitch instance;
	
	public static ImgSwitch instance() {
		if(instance == null) {
			instance = new ImgSwitch();
		}
		return instance; 
	}
	
	//Início com mapa zerado.
	private ImgSwitch() {
		for(int i = 0; i < 100; i++)
			imgArray[i] = R.drawable.empty;
		
	}
	
	public void setImgArray(Status status, int position){
				
		switch(status){
			case Dead:
				imgArray[position] = R.drawable.dead;
				break;
			case Alive:
				imgArray[position] = R.drawable.alive;
				break;
			case Empty:
				imgArray[position] = R.drawable.empty;
				break;
		}
		
	}

	public Integer[] getImgArray() {
		return imgArray;
	}	
	
}
