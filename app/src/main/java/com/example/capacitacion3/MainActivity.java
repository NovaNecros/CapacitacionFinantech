package com.example.capacitacion3;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    Button miBoton;
    Button botonReiniciar;
    TextView textoSaludo;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        miBoton = (Button)findViewById(R.id.botoncito);
        botonReiniciar = (Button)findViewById(R.id.botonReiniciar);
        textoSaludo = (TextView)findViewById(R.id.textoSaludo);

        miBoton.setVisibility(View.VISIBLE);
        botonReiniciar.setVisibility(View.GONE);

        textoSaludo.setText("Hola mundo");
        miBoton.setText("Presióname");
        botonReiniciar.setText("Reiniciar")


        miBoton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                textoSaludo.setText("Me apachurraron");
                miBoton.setVisibility(View.GONE);
                botonReiniciar.setVisibility(View.VISIBLE);
            }
        });

        botonReiniciar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                recreate();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}