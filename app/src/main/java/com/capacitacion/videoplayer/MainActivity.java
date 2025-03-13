package com.capacitacion.videoplayer;

import java.net.URI;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import android.widget.Toast;

import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity
{
    private VideoView vv;

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

        vv = (VideoView) findViewById(R.id.vv);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video;
        Uri uri = Uri.parse(videoPath);
        vv.setVideoURI(uri);
        vv.setMediaController(new MediaController(this));
        vv.requestFocus();
        vv.start();

        btnPlayPause.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked && !vv.isPlaying())
                {
                        vv.start();
                        Toast.makeText(getApplicationContext(), getString(R.string.btn_play), Toast.LENGTH_SHORT).show();
                }
                else if(!isChecked && vv.isPlaying())
                {
                    vv.pause();
                    Toast.makeText(getApplicationContext(), getString(R.string.btn_pause), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if(vv != null)
        {
            vv = null;
        }
    }
}