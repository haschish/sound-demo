package com.example.sounddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SoundPool sp;

    int idSound1 = -1;
    int idSound2 = -1;
    int idSound3 = -1;
    int nowPlaying = -1;

    float volume = .1f;
    int repeats = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSound1 = (Button) findViewById(R.id.btnSound1);
        btnSound1.setOnClickListener(this);

        Button btnSound2 = (Button) findViewById(R.id.btnSound2);
        btnSound2.setOnClickListener(this);

        Button btnSound3 = (Button) findViewById(R.id.btnSound3);
        btnSound3.setOnClickListener(this);

        Button btnStop = (Button) findViewById(R.id.btnStop);
        btnStop.setOnClickListener(this);
    }
}
