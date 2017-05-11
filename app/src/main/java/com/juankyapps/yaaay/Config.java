package com.juankyapps.yaaay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.juankyapps.yaaay.R;

public class Config extends AppCompatActivity {
    private static SharedPreferences configs;
    private static EditText name;
    private static SeekBar seekBar;
    private static TextView timeVibr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        configs = getSharedPreferences("Preferencias", 0);
        name = (EditText) this.findViewById(R.id.nameInput);
        name.setText(configs.getString("Nombre",""));
        seekBar = (SeekBar) this.findViewById(R.id.seekBar);
        seekBar.setMax(0);
        seekBar.setMax(2000);
        int seekBarProgress = configs.getInt("VibracionSec",2000);
        seekBar.setProgress(seekBarProgress);
        timeVibr = (TextView) this.findViewById(R.id.vib_dur);
        timeVibr.setText("Duración de la vibración: " + tiempoVibracion(seekBarProgress) + " segundos.");
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                timeVibr.setText("Duración de la vibración: " + tiempoVibracion(progress) + " segundos.");
                SharedPreferences.Editor editor = configs.edit();
                editor.putInt("VibracionSec", progress);
                editor.commit();
                MainActivity.VibDur = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        CheckBox vibCheck = (CheckBox) this.findViewById(R.id.vibCheckBox);
        vibCheck.setChecked(configs.getBoolean("Vibracion",true));
        vibCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = configs.edit();
                editor.putBoolean("Vibracion", isChecked);
                editor.commit();
                MainActivity.Vib = isChecked;
            }
        });
        CheckBox spamCheck = (CheckBox) this.findViewById(R.id.spamCheckBox);
        spamCheck.setChecked(configs.getBoolean("Spam",false));
        spamCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = configs.edit();
                editor.putBoolean("Spam", isChecked);
                editor.commit();
                MainActivity.Spam = isChecked;
            }
        });
        Button aboutBtns = (Button) this.findViewById(R.id.aboutBtn);
        aboutBtns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutIntent = new Intent(Config.this, AcercaDe.class);
                startActivity(aboutIntent);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public double tiempoVibracion(int progreso){
        double pp = progreso;
        double tempProgress = pp/1000;
        double roundOff = Math.round(tempProgress * 100.0) / 100.0;
        return roundOff;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId()==android.R.id.home) {
            saveSettings();
            finish();
            return true;
        }
        return false;
    }
    @Override
    public void onBackPressed(){
        saveSettings();
        finish();
    }
    private void saveSettings(){
        SharedPreferences.Editor editor = configs.edit();
        String nombre = name.getText().toString();
        editor.putString("Nombre", nombre);
        editor.putBoolean("setName", !nombre.equals(""));
        editor.commit();
        String nameF;
        if(!nombre.equals("")){
            nameF = "Hola, " + nombre;
        }else{
            nameF = "";
        }
        MainActivity.nombre.setText(nameF);
    }
}
