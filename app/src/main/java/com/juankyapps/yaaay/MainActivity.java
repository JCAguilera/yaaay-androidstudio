package com.juankyapps.yaaay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.juankyapps.yaaay.R;

public class MainActivity extends AppCompatActivity {
    public static TextView nombre;
    public static int VibDur = 2000;
    public static boolean Vib = true;
    public static boolean Spam = false;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        final SharedPreferences configs = getSharedPreferences("Preferencias", 0);
        String name = configs.getString("Nombre","");
        VibDur = configs.getInt("VibracionSec",2000);
        Vib = configs.getBoolean("Vibracion",true);
        Spam = configs.getBoolean("Spam",false);
        Boolean nameSet = configs.getBoolean("setName", false);
        final MediaPlayer yayMP = MediaPlayer.create(this, R.raw.yay);
        Button yayBtn = (Button) this.findViewById(R.id.yay_btn);
        ImageButton configBtn = (ImageButton) this.findViewById(R.id.config_btn);
        nombre = (TextView) this.findViewById(R.id.greet);
        final Vibrator vibr = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if(!name.equals("")){
            nombre.setText("Hola, " + name);
        }
        yayBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(Vib){
                    vibr.vibrate(VibDur);
                }
                if (yayMP.isPlaying()){
                    if(Spam) {
                        yayMP.seekTo(0);
                    }
                }else{
                    yayMP.start();
                }
            }
        });
        configBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent configIntent = new Intent(MainActivity.this, Config.class);
                startActivity(configIntent);
            }
        });
    }
}
