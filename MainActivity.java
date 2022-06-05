//Robert Bajan 04/05/22
package com.example.studytimerfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Declaring Widgets
    Button btnStart, btnStop, btnPause;
    EditText editTextTask;
    TextView txtTimeTaken;

    int seconds = 0, hours, minutes, sec;
    String SECONDS, RUNNING, HOUR, MIN, SEC;
    boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startTimer();

        //Saves Values
        if (savedInstanceState != null){
            seconds = savedInstanceState.getInt(SECONDS);
            running = savedInstanceState.getBoolean(RUNNING);
            hours = savedInstanceState.getInt(HOUR);
            minutes = savedInstanceState.getInt(MIN);
            sec = savedInstanceState.getInt(SEC);
        }

        //instantiating Widgets
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnPause = findViewById(R.id.btnPause);
        editTextTask = findViewById(R.id.editTextTask);
        txtTimeTaken = findViewById(R.id.txtTimeTaken);

        // On click events
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = true;
            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = false;
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = false;
                seconds = 0;
                SaveTask();
            }
        });
    }

    //Time Taken Text Function
    private void SaveTask(){
        String TaskEntered = editTextTask.getText().toString();
        txtTimeTaken.setText("You spent " + hours + " hours " + minutes + " minutes and " + sec + " seconds on " + TaskEntered + " last time!");
    }

    //SaveInstanceState
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(SECONDS, seconds);
        outState.putBoolean(RUNNING, running);
        outState.putInt(HOUR, hours);
        outState.putInt(MIN, minutes);
        outState.putInt(SEC, sec);
    }

    //Timer Function
    private void startTimer() {
        final TextView txtTimer = findViewById(R.id.txtTimer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                hours = seconds / 3600;
                minutes = (seconds % 3600) / 60;
                sec = seconds % 60;
                String time = String.format("%02d:%02d:%02d",hours,minutes,sec);
                txtTimer.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}