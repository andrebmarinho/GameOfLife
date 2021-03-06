package br.unb.cic.gameoflife.ui;

//Implementação baseada no código disponível em:
// http://developer.android.com/guide/topics/ui/layout/gridview.html

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImgAdapter extends BaseAdapter {
	private Context context;
	private Integer[] arrayDeImgs = ImgSwitch.instance().getImgArray();

	public ImgAdapter(Context context) {
		this.context = context;
	}

	// cria 1 ImageView para cada item que o adaptador chama.
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) {
			imageView = new ImageView(context);
			imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
		} else {
			imageView = (ImageView) convertView;
		}

		imageView.setImageResource(arrayDeImgs[position]);
		return imageView;
	}

	@Override
	public Object getItem(int position) {
		return arrayDeImgs[position];
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public int getCount() {
		return arrayDeImgs.length;
	}
}