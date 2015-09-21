package br.com.cast.turmaformacao.taskmanager.controllers.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entidade.Color;

/**
 * Created by Wanilton on 19/09/2015.
 */
public class ColorListAdapter extends BaseAdapter {

    private Activity context;
    private Color[] colors;

    public ColorListAdapter(Activity context) {
        super();
        this.context = context;
        this.colors = Color.values();
    }

    @Override
    public int getCount() {
        return colors.length;
    }

    @Override
    public Object getItem(int position) {
        return colors[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Color color = (Color) getItem(position);

        View colorListItem = context.getLayoutInflater().inflate(R.layout.list_item_color, parent, false);

        int cor = android.graphics.Color.parseColor(color.getHex());
        colorListItem.findViewById(R.id.spinnerItemColor);
        colorListItem.setBackgroundColor(cor);
        return colorListItem;

    }
}
