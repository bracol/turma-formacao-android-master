package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.controllers.adapters.ColorListAdapter;
import br.com.cast.turmaformacao.taskmanager.controllers.adapters.LabelListAdapter;
import br.com.cast.turmaformacao.taskmanager.model.entidade.Color;
import br.com.cast.turmaformacao.taskmanager.model.entidade.Label;
import br.com.cast.turmaformacao.taskmanager.model.servicos.LabelBusinessServices;

/**
 * Created by Administrador on 17/09/2015.
 */
public class LabelFormActivity extends AppCompatActivity {
    private Spinner spinnerColor;
    private Label label;
    private ListView listColorView;
    private EditText editTextName;
    private EditText editTextDescription;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_form);


        initLabel();
        bindEditTextName();
        bindEditTextDescription();
        bindSpinnerColor();
        bindListColorView();

    }

    private void bindListColorView() {
        listColorView = (ListView) findViewById(R.id.listViewTaskList);
    }

    //inicia a label, caso não exista cria uma nova
    private void initLabel() {
        this.label = this.label == null ? new Label() : this.label;
    }

    //monta o edittext na tela baseado no seu id
    private void bindEditTextName() {
        editTextName = (EditText) findViewById(R.id.editTextName);
    }

    private void bindEditTextDescription() {
        editTextDescription = (EditText) findViewById(R.id.editTextName);
    }

    //monta o spinnerColor
    private void bindSpinnerColor() {
        //primeiro passado criar o adapter
        spinnerColor = (Spinner) findViewById(R.id.spinnerColor);
        Color [] list = Color.values();
        ColorListAdapter colorAdapter = new ColorListAdapter(LabelFormActivity.this, list);
        spinnerColor.setAdapter(colorAdapter);

    }

    private void updateLabelList() {
        List<Label> values = LabelBusinessServices.findAll();
        spinnerColor.setAdapter(new LabelListAdapter(this, values));

        LabelListAdapter adapter = (LabelListAdapter) spinnerColor.getAdapter();

        adapter.notifyDataSetInvalidated();
    }

    //método na classe pai que sera sobrescrito
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //menuinflater serve pra pegar o layout do meno correspondente e adicionar todos os itens
        getMenuInflater().inflate(R.menu.menu_label_form, menu);
        //criar o menu
        return super.onCreateOptionsMenu(menu);
    }

    //método para quando um item do menu for selecionado ele ira verificar qual foi o item e irá invocar o layout respectivo
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.menu_OK):
                onMenuOK();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //método que vai ser chamador caso o item do menu seja o OK
    private void onMenuOK() {
        binLabel();
        LabelBusinessServices.save(label);
        //Toast serve para exibir uma mensagem na tela do android como uma popup
        //necessita de um contexto(activity), a mensagem e o tipo da duração que será exibido(long, short) e um .show() para de fato aparecer na tela
        Toast.makeText(LabelFormActivity.this, label.toString(), Toast.LENGTH_LONG).show();
    }

    //criado a label passando os parametros de acordo com os campos do editText passado
    private void binLabel() {
        //seta a label com o edittext.getText que irá pegar o texto do componente, porém é necessário dar um toString()
        label.setName(editTextName.getText().toString());
        label.setDescription(editTextDescription.getText().toString());
        Color cor = (Color) spinnerColor.getAdapter().getItem(spinnerColor.getSelectedItemPosition());
        label.setColor(cor);

    }
}
