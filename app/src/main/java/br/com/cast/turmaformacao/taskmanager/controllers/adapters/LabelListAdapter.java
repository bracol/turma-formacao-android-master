package br.com.cast.turmaformacao.taskmanager.controllers.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entidade.Color;
import br.com.cast.turmaformacao.taskmanager.model.entidade.Label;

/**
 * Created by Administrador on 17/09/2015.
 */
public class LabelListAdapter extends BaseAdapter {

    //primeiro passao extender baseAdapter e criar o contexto
    private Activity context;
    private List<Label> labels;

    public LabelListAdapter(Activity activity, List<Label> labels) {
        super();
        this.context = activity;
        this.labels = labels;
    }

    @Override
    public int getCount() {
        return this.labels.size();
    }

    @Override
    public Label getItem(int position) {
        return this.labels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Label label = getItem(position);
        //segundo passo criar o layout da lista
        //terceiro passo criar os itens da lista aqui

        View viewListLabel = context.getLayoutInflater().inflate(R.layout.list_item_label, parent, false);

        View viewColor = viewListLabel.findViewById(R.id.viewColorList);
        viewColor.setBackgroundColor(android.graphics.Color.parseColor(label.getColor().getHex()));

        TextView textViewName = (TextView) viewListLabel.findViewById(R.id.textViewNameList);
        textViewName.setText(label.getName());


        return viewListLabel;
    }

    public void setItens(List<Label> itens) {
        labels.clear();
        labels.addAll(itens);
    }
}
