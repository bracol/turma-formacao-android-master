package br.com.cast.turmaformacao.taskmanager.controllers.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entidade.Calculator;

/**
 * Created by Wanilton on 19/09/2015.
 */
public class CalculatorListAdapter extends BaseAdapter {
    private List<Calculator> calculatorList;
    private Activity context;

    public CalculatorListAdapter(List<Calculator> calculatorList, Activity context) {
        this.calculatorList = calculatorList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return calculatorList.size();
    }

    @Override
    public Calculator getItem(int position) {
        return calculatorList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Calculator calc = getItem(position);
        View calculatorListItem = context.getLayoutInflater().inflate(R.layout.list_item_calculator, parent, false);

        TextView textViewNumA = (TextView) calculatorListItem.findViewById(R.id.textViewNumA);
        textViewNumA.setText(String.valueOf(calc.getNumA()));

        TextView textViewNumB = (TextView) calculatorListItem.findViewById(R.id.textViewNumB);
        textViewNumB.setText(String.valueOf(calc.getNumB()));

        TextView textViewTotal = (TextView) calculatorListItem.findViewById(R.id.textViewTotal);
        textViewTotal.setText(String.valueOf(calc.getTotal()));

        return calculatorListItem;

    }
}
