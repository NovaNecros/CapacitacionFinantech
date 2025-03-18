package com.capacitacion.audioplayer;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
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

        final ToggleButton btnPlayPause = (ToggleButton) findViewById(R.id.btn_play_pause);
        final Button btnStop = (Button) findViewById(R.id.btn_stop);
        btnStop.setVisibility(View.GONE);

        mp = MediaPlayer.create(this, R.raw.twist);

        //El botón se alterna entre pausa y play
        btnPlayPause.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked && !mp.isPlaying())
                {
                        mp.start();
                        Toast.makeText(getApplicationContext(), getString(R.string.btn_play), Toast.LENGTH_SHORT).show();
                        btnStop.setVisibility(View.VISIBLE);
                }
                else if(!isChecked && mp.isPlaying())
                {
                    mp.pause();
                    Toast.makeText(getApplicationContext(), getString(R.string.btn_pause), Toast.LENGTH_SHORT).show();
                    btnStop.setVisibility(View.VISIBLE);
                }
            }
        });

        //Detiene y reinicia el audio pero solamente aparece si había comenzado la reproducción
        btnStop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mp.stop();
                mp.prepareAsync();
                btnStop.setVisibility(View.GONE);
                btnPlayPause.setChecked(false);
                Toast.makeText(getApplicationContext(), getString(R.string.btn_stop), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Reinicia el audio si se destruye la actividad
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