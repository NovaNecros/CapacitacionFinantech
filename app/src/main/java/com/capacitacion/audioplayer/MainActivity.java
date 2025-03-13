package com.capacitacion.audioplayer;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity
{

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final Button btnPlay = (Button) findViewById(R.id.btn_play);
        final Button btnPause = (Button) findViewById(R.id.btn_pause);
        final Button btnStop = (Button) findViewById(R.id.btn_stop);

        mp = MediaPlayer.create(this, R.raw.audio);

        btnPlay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(!mp.isPlaying())
                {
                    mp.start();
                    Toast.makeText(getApplicationContext(), "@string/btn_play", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(mp.isPlaying())
                {
                    mp.pause();
                    Toast.makeText(getApplicationContext(), "@string/btn_pause", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(mp.isPlaying())
                {
                    mp.stop();
                    mp.prepareAsync();
                    Toast.makeText(getApplicationContext(), "@string/btn_stop", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if(mp != null)
        {
            mp.release();
            mp = null;
        }
    }
}