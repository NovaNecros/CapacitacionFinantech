package com.capacitacion.drawableresources;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.graphics.drawable.Drawable;

public class MainActivity extends AppCompatActivity
{
    Button botonHexagonal;
    Button botonReinicio;
    ImageView imagenGato;
    Drawable drawableGato;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        botonHexagonal = (Button) findViewById(R.id.botonHexagonal);
        botonReinicio = (Button) findViewById(R.id.botonReinicio);
        imagenGato = (ImageView) findViewById(R.id.imagenGato);
        drawableGato = ContextCompat.getDrawable(this, R.drawable.crazycafinecat);

        botonHexagonal.setVisibility(View.VISIBLE);
        botonReinicio.setVisibility(View.GONE);
        imagenGato.setVisibility(View.GONE);

        //Botón que te lleva de la pantalla inicial a la pantalla final y es hexagonal :)
        botonHexagonal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                botonHexagonal.setVisibility(View.GONE);
                botonReinicio.setVisibility(View.VISIBLE);
                imagenGato.setVisibility(View.VISIBLE);
            }
        });

        //Botón que te lleva de la pantalla final a la pantalla inicial
        botonReinicio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                recreate();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}