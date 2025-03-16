package com.capacitacion.imagen;

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
    EditText editNombre;
    ImageView imagen;




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
        editNombre = (EditText) findViewById(R.id.editNombre);
        imagen = (ImageView) findViewById(R.id.coffeeCat);

        miBoton.setVisibility(View.VISIBLE);
        botonSaludo.setVisibility(View.GONE);
        botonReiniciar.setVisibility(View.GONE);
        editNombre.setVisibility(View.GONE);
        imagen.setVisibility(View.GONE);

        miBoton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                textoSaludo.setText("dame nombre");
                miBoton.setVisibility(View.GONE);
                botonSaludo.setVisibility(View.VISIBLE);
                botonReiniciar.setVisibility(View.VISIBLE);
                editNombre.setVisibility(view.VISIBLE);
                imagen.setVisibility(View.VISIBLE);

            }
        });

        botonSaludo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String nombre = editNombre.getText().toString();
                if(!nombre.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "tú " + nombre, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "dame nombre >:(", Toast.LENGTH_SHORT).show();
                }
            }
        });

        botonReiniciar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                recreate();
                editNombre.setText("");
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