package com.capacitacion.stringresources;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
{
    TextView textoArriba;
    TextView textoAbajo;
    Button botonInicio;
    Button botonReinicio;
    ImageView imagenSaludo;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        textoArriba = (TextView) findViewById(R.id.textoArriba);
        textoAbajo = (TextView) findViewById(R.id.textoAbajo);
        botonInicio = (Button) findViewById(R.id.botonInicio);
        botonReinicio = (Button) findViewById(R.id.botonReinicio);
        imagenSaludo = (ImageView) findViewById(R.id.imagenSaludo);

        textoArriba.setText(R.string.instructions);
        textoAbajo.setText(R.string.meme);

        textoArriba.setVisibility(View.GONE);
        textoAbajo.setVisibility(View.GONE);
        botonInicio.setVisibility(View.VISIBLE);
        botonReinicio.setVisibility(View.GONE);
        imagenSaludo.setVisibility(View.VISIBLE);

        botonInicio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                textoArriba.setVisibility(View.VISIBLE);
                textoAbajo.setVisibility(View.VISIBLE);
                botonInicio.setVisibility(View.GONE);
                botonReinicio.setVisibility(View.VISIBLE);
                imagenSaludo.setVisibility(View.GONE);
            }
        });

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