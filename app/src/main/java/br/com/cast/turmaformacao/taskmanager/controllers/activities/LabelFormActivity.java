package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
    //private Spinner spinnerColor;
    private Label label;
    private View viewColor;
    private EditText editTextName;
    private EditText editTextDescription;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_form);


        initLabel();
        bindEditTextName();
        bindEditTextDescription();
        bindViewColor();
    }

    private void bindViewColor() {
        viewColor = findViewById(R.id.spinnerColor);
        viewColor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LabelFormActivity.this);
                final ColorListAdapter adapter = new ColorListAdapter(LabelFormActivity.this);
                dialogBuilder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updateColor((Color) adapter.getItem(which));
                    }
                }).setTitle("Selecione a cor")
                        .setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();
            }
        });
    }

    private void updateColor(Color item) {

        label.setColor(item);
        int cor = android.graphics.Color.parseColor(item.getHex());

        viewColor.setBackgroundColor(cor);
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
    /*private void bindSpinnerColor() {
        //primeiro passado criar o adapter
        spinnerColor = (Spinner) findViewById(R.id.spinnerColor);
        Color [] values = Color.values();
        ColorListAdapter colorAdapter = new ColorListAdapter(LabelFormActivity.this, values);
        spinnerColor.setAdapter(colorAdapter);
    }*/

    //private Color getSpinnerColor(){
    //return (Color) spinnerColor.getSelectedItem();
    //}

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
        //Color cor = (Color) spinnerColor.getAdapter().getItem(spinnerColor.getSelectedItemPosition());
        //label.setColor(getSpinnerColor());

    }
}
