package domain;

//Singleton respons�vel pelas mudan�as das imagens.

import com.example.gameoflife.R;


public class ImgSwitch {

	private static Integer[] arrayDeImgs = new Integer[100];
	
	private static ImgSwitch instance;
	
	public static ImgSwitch instance() {
		if(instance == null) {
			instance = new ImgSwitch();
		}
		return instance; 
	}
	
	//In�cio com mapa zerado.
	private ImgSwitch() {
		for(int i = 0; i < 100; i++)
			arrayDeImgs[i] = R.drawable.alive;
		
	}
	
	//x e y pertencem a [0 ,9]
	public void setArrayDeImgs(Estados estado, int x, int y){
		int index = (x+1) + y * 10;
		
		switch(estado){
			case Morto:
				arrayDeImgs[index] = R.drawable.dead;
			case Vivo:
				arrayDeImgs[index] = R.drawable.alive;
			case Vazio:
				arrayDeImgs[index] = R.drawable.empty;
		
		}
		
	}

	public Integer[] getArrayDeImgs() {
		return arrayDeImgs;
	}	
	
}
