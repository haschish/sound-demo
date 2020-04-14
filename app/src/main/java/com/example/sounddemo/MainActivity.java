package com.example.sounddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SoundPool sp;

    int shotSoundID = -1;
    int reloadSoundID = -1;
    int explosionSoundID = -1;
    int streamID = -1;

    float volume = .5f;
    int repeats = 0;


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

        Button btnPause = (Button) findViewById(R.id.btnPause);
        btnPause.setOnClickListener(this);

        Button btnStop = (Button) findViewById(R.id.btnStop);
        btnStop.setOnClickListener(this);

        Button btnResume = (Button) findViewById(R.id.btnResume);
        btnResume.setOnClickListener(this);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                volume = progress / 10f;
                sp.setVolume(streamID, volume, volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

            sp = new SoundPool.Builder()
                .setMaxStreams(5)
                .setAudioAttributes(audioAttributes)
                .build();
        } else {
            sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }


        try {
            AssetManager assetManager = getAssets();
            AssetFileDescriptor descriptor;

            descriptor = assetManager.openFd("reload.mp3");
            reloadSoundID = sp.load(descriptor, 0);

            shotSoundID = sp.load(assetManager.openFd("shot.mp3"), 0);
            explosionSoundID = sp.load(this, R.raw.explosion, 0);

        } catch (Exception e) {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSound1:
                sp.stop(streamID);
                streamID = sp.play(reloadSoundID, volume, volume, 0, repeats, 1);
                break;
            case R.id.btnSound2:
                sp.stop(streamID);
                streamID = sp.play(shotSoundID, volume, volume, 0, repeats, 1);
                break;
            case R.id.btnSound3:
                sp.stop(streamID);
                streamID = sp.play(explosionSoundID, volume, volume, 0, repeats, 1);
                break;
            case R.id.btnPause:
                sp.pause(streamID);
                break;
            case R.id.btnStop:
                sp.stop(streamID);
                break;
            case R.id.btnResume:
                sp.resume(streamID);
                break;
        }
    }
}
