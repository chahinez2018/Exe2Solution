package com.midterm.lasalle.ex2application;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView text1;
    int hour;
    ArrayList<Integer> alarmDays= new ArrayList<Integer>();

    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        text1 = (TextView)findViewById(R.id.text1);
        seekBar.setMax(23);



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {

                text1.setText(progress+":00");
                hour = progress;
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        Switch switchRepeat = (Switch)findViewById(R.id.switchRepeat);

        switchRepeat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    alarmDays.add(Calendar.SUNDAY);
                    alarmDays.add(Calendar.MONDAY);
                    alarmDays.add(Calendar.TUESDAY);
                    alarmDays.add(Calendar.WEDNESDAY);
                    alarmDays.add(Calendar.THURSDAY);
                    alarmDays.add(Calendar.FRIDAY);
                    alarmDays.add(Calendar.SATURDAY);

                }
                else {
                    alarmDays.clear();
                }
            }
        });
        Button alarmButton = findViewById(R.id.alarmButton);
        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlarm("Morning", hour, 30,alarmDays);
            }
        });

    }

    public void createAlarm(String message, int hour, int minutes,ArrayList<Integer> alarmDays) {




        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes)
                .putExtra(AlarmClock.EXTRA_DAYS,alarmDays);




        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
