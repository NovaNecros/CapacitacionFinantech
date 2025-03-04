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
        botonReiniciar = (Button) findViewById(R.id.botonReiniciar);
        textoSaludo = (TextView) findViewById(R.id.textoSaludo);
        imagen = (ImageView) findViewById(R.id.coffeeCat);
        editNombre = (EditText) findViewById(R.id.editNombre);

        miBoton.setVisibility(View.VISIBLE);
        botonReiniciar.setVisibility(View.GONE);
        imagen.setVisibility(View.GONE);
        editNombre.setVisibility(View.GONE);

        miBoton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String nombre = editNombre.getText().toString();
                if(!nombre.isEmpty())
                {
                    textoSaludo.setText("Miau " + nombre);
                    Toast.makeText(getApplicationContext(), "Presionaste el Botón" + nombre, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Debes darme tu nombre!", Toast.LENGTH_SHORT).show();
                }

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