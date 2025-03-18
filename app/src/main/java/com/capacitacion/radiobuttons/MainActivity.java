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
        final ToggleButton seleccionBtn = (ToggleButton) findViewById(R.id.seleccionbtn);

        //Agrupo los radiobuttons para más fácil acceso
        final RadioButton [] radioButtons = new RadioButton[]
        {
            rojoRB,
            azulRB,
            verdeRB,
            moradoRB
        };

        final View mainLayout = findViewById(R.id.main);

        titulo.setVisibility(View.VISIBLE);
        imagen.setVisibility(View.GONE);
        appetizerCheck.setVisibility(View.GONE);
        entreeCheck.setVisibility(View.GONE);
        dessertCheck.setVisibility(View.GONE);
        ordenarBtn.setVisibility(View.GONE);

        //Los ifs evitan poner comas al principio de la orden
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
                else
                {
                    int color = ContextCompat.getColor(getApplicationContext(), R.color.rojosangre);
                    showToast(getString(R.string.orden_vacia), color);
                }

                //Desmarca las casillas de verificación al hacer una orden
                for(CheckBox checkBox : checkBoxes)
                {
                    checkBox.setChecked(false);
                }
            }
        });

        //Cambia entre las pantallas de inicio y la de ordenes
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                String texto = "";
                int color = 0;


                if(isChecked)
                {
                    texto = getString(R.string.texto_toast_on);
                    color = ContextCompat.getColor(getApplicationContext(), R.color.azulchillon);

                    imagen.setVisibility(View.GONE);
                    appetizerCheck.setVisibility(View.VISIBLE);
                    entreeCheck.setVisibility(View.VISIBLE);
                    dessertCheck.setVisibility(View.VISIBLE);
                    ordenarBtn.setVisibility(View.VISIBLE);
                    seleccionBtn.setVisibility(View.GONE);

                    for(RadioButton rb : radioButtons)
                    {
                        rb.setVisibility(View.GONE);
                    }
                }
                else
                {
                    texto = getString(R.string.texto_toast_off);
                    color = ContextCompat.getColor(getApplicationContext(), R.color.azulchillon);

                    imagen.setVisibility(View.GONE);
                    appetizerCheck.setVisibility(View.GONE);
                    entreeCheck.setVisibility(View.GONE);
                    dessertCheck.setVisibility(View.GONE);
                    ordenarBtn.setVisibility(View.GONE);
                    seleccionBtn.setVisibility(View.VISIBLE);
                    seleccionBtn.setChecked(false);

                    for(CheckBox checkBox : checkBoxes)
                    {
                        checkBox.setChecked(false);
                    }

                    for(RadioButton rb : radioButtons)
                    {
                        rb.setVisibility(View.VISIBLE);
                        rb.setChecked(false);
                    }
                }

                showToast(texto, color);
            }
        });

        //Cambia la pantalla al color seleccionado en los radiobuttons o la regresa al color inicial
        seleccionBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                String texto = "";
                int color = 0;

                if(isChecked)
                {
                    if(rojoRB.isChecked())
                    {
                        texto = getString(R.string.texto_toast_rojo);
                        color = ContextCompat.getColor(getApplicationContext(), R.color.rojopastel);
                    }
                    else if(azulRB.isChecked())
                    {
                        texto = getString(R.string.texto_toast_azul);
                        color = ContextCompat.getColor(getApplicationContext(), R.color.azulpastel);
                    }
                    else if(verdeRB.isChecked())
                    {
                        texto = getString(R.string.texto_toast_verde);
                        color = ContextCompat.getColor(getApplicationContext(), R.color.verdepastel);
                    }
                    else if(moradoRB.isChecked())
                    {
                        texto = getString(R.string.texto_toast_morado);
                        color = ContextCompat.getColor(getApplicationContext(), R.color.moradopastel);
                    }

                    mainLayout.setBackgroundColor(color);

                    for(RadioButton rb : radioButtons)
                    {
                        rb.setVisibility(View.GONE);
                        rb.setChecked(false);
                    }
                }
                else
                {
                    texto = getString(R.string.texto_toast_blanco);
                    color = ContextCompat.getColor(getApplicationContext(), R.color.white);
                    mainLayout.setBackgroundColor(color);
                    color = ContextCompat.getColor(getApplicationContext(), R.color.black);

                    for(RadioButton rb : radioButtons)
                    {
                        rb.setVisibility(View.VISIBLE);
                    }
                }

                showToast(texto, color);
            }
        });
    }

    //Muestra un toast personalizado sin hacer cast de stringbuilder a string
    private void showToast(StringBuilder texto, int color)
    {

        showToast(texto.toString(), color);
    }

    //Muestra un toast personalizado
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