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

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    Button miBoton;
    Button botonSaludo;
    Button botonReiniciar;
    TextView textoSaludo;
    ImageView imagen;
    EditText editNombre;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        miBoton = (Button) findViewById(R.id.botoncito);
        botonSaludo = (Button) findViewById(R.id.botonSaludo);
        botonReiniciar = (Button) findViewById(R.id.botonReiniciar);
        textoSaludo = (TextView) findViewById(R.id.textoSaludo);
        imagen = (ImageView) findViewById(R.id.coffeeCat);
        editNombre = (EditText) findViewById(R.id.editNombre);

        miBoton.setVisibility(View.VISIBLE);
        botonSaludo.setVisibility(View.GONE);
        botonReiniciar.setVisibility(View.GONE);
        imagen.setVisibility(View.GONE);
        editNombre.setVisibility(View.GONE);

        miBoton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                textoSaludo.setText("Dame nombre");
                miBoton.setVisibility(View.GONE);
                botonSaludo.setVisibility(View.VISIBLE);
                botonReiniciar.setVisibility(View.VISIBLE);
                imagen.setVisibility(View.VISIBLE);
                editNombre.setVisibility(view.VISIBLE);

            }
        });

        botonSaludo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String nombre = editNombre.getText().toString();
                if(!nombre.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Tú " + nombre, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Dame nombre >:(", Toast.LENGTH_SHORT).show();
                }
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