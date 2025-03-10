package com.capacitacion.radiobuttons;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.CheckBox;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
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

        final TextView titulo = (TextView) findViewById(R.id.titulo);
        final ToggleButton toggleButton = (ToggleButton) findViewById(R.id.togglebutton);
        final Button ordenarBtn = (Button) findViewById(R.id.ordenarbtn);
        final ImageView imagen = (ImageView) findViewById(R.id.chef);

        final CheckBox appetizerCheck = (CheckBox) findViewById(R.id.appetizercheck);
        final CheckBox entreeCheck = (CheckBox) findViewById(R.id.entreecheck);
        final CheckBox dessertCheck = (CheckBox) findViewById(R.id.dessertcheck);
        final CheckBox [] checkBoxes = new CheckBox[]
        {
            appetizerCheck,
            entreeCheck,
            dessertCheck
        };

        final RadioButton rojoRB = (RadioButton) findViewById(R.id.rojorb);
        final RadioButton azulRB = (RadioButton) findViewById(R.id.azulrb);
        final RadioButton verdeRB = (RadioButton) findViewById(R.id.verderb);
        final RadioButton moradoRB = (RadioButton) findViewById(R.id.moradorb);
        final Button seleccion = (Button) findViewById(R.id.seleccionbtn);

        titulo.setVisibility(View.VISIBLE);
        imagen.setVisibility(View.GONE);
        appetizerCheck.setVisibility(View.GONE);
        entreeCheck.setVisibility(View.GONE);
        dessertCheck.setVisibility(View.GONE);
        ordenarBtn.setVisibility(View.GONE);

        ordenarBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                StringBuilder orden = new StringBuilder();

                if(appetizerCheck.isChecked())
                {
                    orden.append(getString(R.string.aperitivo));
                }
                if(entreeCheck.isChecked())
                {
                    if(!orden.toString().isEmpty())
                    {
                        orden.append(", ");
                    }
                    orden.append(getString(R.string.entrada));
                }
                if(dessertCheck.isChecked())
                {
                    if(!orden.toString().isEmpty())
                    {
                        orden.append(", ");
                    }
                    orden.append(getString(R.string.postre));
                }

                if(!orden.toString().isEmpty())
                {
                    int color = ContextCompat.getColor(getApplicationContext(), R.color.dorado);
                    showToast(orden, color);
                }

                for(CheckBox checkBox : checkBoxes)
                {
                    checkBox.setChecked(false);
                }
            }
        });

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                String texto;
                int color;

                if(isChecked)
                {
                    texto = getString(R.string.texto_boton_on);
                    color = ContextCompat.getColor(getApplicationContext(), R.color.azulchillon);
                    showToast(texto, color);

                    imagen.setVisibility(View.GONE);
                    appetizerCheck.setVisibility(View.VISIBLE);
                    entreeCheck.setVisibility(View.VISIBLE);
                    dessertCheck.setVisibility(View.VISIBLE);
                    ordenarBtn.setVisibility(View.VISIBLE);
                }
                else
                {
                    texto = getString(R.string.texto_boton_off);
                    color = ContextCompat.getColor(getApplicationContext(), R.color.azulchillon);
                    showToast(texto, color);

                    imagen.setVisibility(View.VISIBLE);
                    appetizerCheck.setVisibility(View.GONE);
                    entreeCheck.setVisibility(View.GONE);
                    dessertCheck.setVisibility(View.GONE);
                    ordenarBtn.setVisibility(View.GONE);

                    for(CheckBox checkBox : checkBoxes)
                    {
                        checkBox.setChecked(false);
                    }
                }
            }
        });

        seleccion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String texto;
                int color;

                if(rojoRB.isChecked())
                {
                    texto = getString(R.string.texto_toast_rojo);
                    color = ContextCompat.getColor(getApplicationContext(), R.color.rojopastel);
                    showToast(texto, color);
                }
                else if(azulRB.isChecked())
                {
                    texto = getString(R.string.texto_toast_azul);
                    color = ContextCompat.getColor(getApplicationContext(), R.color.azulpastel);
                    showToast(texto, color);
                }
                else if(verdeRB.isChecked())
                {
                    texto = getString(R.string.texto_toast_verde);
                    color = ContextCompat.getColor(getApplicationContext(), R.color.verdepastel);
                    showToast(texto, color);
                }
                else if(moradoRB.isChecked())
                {
                    texto = getString(R.string.texto_toast_morado);
                    color = ContextCompat.getColor(getApplicationContext(), R.color.moradopastel);
                    showToast(texto, color);
                }
            }
        });
    }


    private void showToast(StringBuilder texto, int color)
    {

        showToast(texto.toString(), color);
    }
    private void showToast(String texto, int color)
    {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.main), false);
        Toast toast = new Toast(getApplicationContext());
        TextView contenido = layout.findViewById(R.id.textoToast);
        GradientDrawable burbuja = new GradientDrawable();

        burbuja.setColor(color);
        burbuja.setCornerRadius(25);
        layout.setBackground(burbuja);
        contenido.setText(texto);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }
}