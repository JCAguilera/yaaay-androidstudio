package com.juankyapps.yaaay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.*;
import android.widget.TextView;


public class AcercaDe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView frase = (TextView) this.findViewById(R.id.fraseCelebre);
        String[] frases = frasecitas();
        int pos = 0 + (int)(Math.random() * (((frases.length-1) - 0) + 1));;
        frase.setText("\"" + frases[pos] + "\"");
    }
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId()==android.R.id.home) {
            finish();
            return true;
        }
        return false;
    }
    public String[] frasecitas(){
        String[] lista = {"La vida del Juanky es dura,\n pero más dura la verdura",
                "La capacidad de hablar no te hace inteligente",
                "No te tomes la vida demasiado en serio.\nNo saldrás de ella con vida.",
                "Listoooooooooooo"};
        return lista;
    }
}
