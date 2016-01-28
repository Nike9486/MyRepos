package com.example.nike.testprojectwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by Nike on 28.01.2016.
 */
public class SecondActivity extends Activity {
    TextView data,presure,temp,wind,relwet,heat;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tow);

                data = (TextView)findViewById(R.id.DataText);
                presure = (TextView)findViewById(R.id.PressureText);
                temp = (TextView)findViewById(R.id.TempText);
                wind = (TextView)findViewById(R.id.WindText);
                relwet = (TextView)findViewById(R.id.RelwetText);
                heat = (TextView)findViewById(R.id.HeatText);
        Intent intent = getIntent();
        data.setText(intent.getStringExtra("Data"));
        presure.setText(presure.getText()+intent.getStringExtra("minPresure")+"-"+intent.getStringExtra("maxPresure")+" мм рт. ст.");
        temp.setText(temp.getText()+" "+intent.getStringExtra("minTemperature")+"..."+intent.getStringExtra("maxTemperature")+" °C");
        wind.setText(wind.getText()+intent.getStringExtra("minWind")+"-"+intent.getStringExtra("maxWind")+" м/с");
        relwet.setText(relwet.getText()+intent.getStringExtra("minRelwet")+"-"+intent.getStringExtra("maxRelwet")+" %");
        heat.setText(heat.getText()+" "+intent.getStringExtra("maxHeat")+"..."+intent.getStringExtra("minHeat")+" °C");
    }

}
