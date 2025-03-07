package com.capacitacion.listeners;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    Button boton1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button boton1 = (Button) findViewById(R.id.boton1);
        Button boton2 = (Button) findViewById(R.id.boton2);

        boton1.setOnClickListener(this);
        boton2.setOnClickListener(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();

        if(id == R.id.boton1)
        {
            Toast.makeText(getApplicationContext(), "picaste boton 1", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.boton2)
        {
            Toast.makeText(getApplicationContext(), "picaste boton 2", Toast.LENGTH_SHORT).show();
        }
    }
}