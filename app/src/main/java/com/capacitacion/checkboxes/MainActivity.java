package com.capacitacion.checkboxes;

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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.CheckBox;
import android.widget.Button;

import org.w3c.dom.Text;

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
        final CheckBox appetizerCheck = (CheckBox) findViewById(R.id.appetizercheck);
        final CheckBox entreeCheck = (CheckBox) findViewById(R.id.entreecheck);
        final CheckBox dessertCheck = (CheckBox) findViewById(R.id.dessertcheck);
        final Button ordenarBtn = (Button) findViewById(R.id.ordenarbtn);
        final ImageView imagen = (ImageView) findViewById(R.id.chef);

        final CheckBox [] checkBoxes = new CheckBox[]
        {
            appetizerCheck,
            entreeCheck,
            dessertCheck
        };

        titulo.setVisibility(View.VISIBLE);
        imagen.setVisibility(View.VISIBLE);
        appetizerCheck.setVisibility(View.GONE);
        entreeCheck.setVisibility(View.GONE);
        dessertCheck.setVisibility(View.GONE);
        ordenarBtn.setVisibility(View.GONE);

        ordenarBtn.setOnClickListener(new View.OnClickListener()
        {
            //Los ifs evitan comas al principio de la orden
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
                    int color = ContextCompat.getColor(getApplicationContext(), R.color.rojochillon);
                    showToast(getString(R.string.ordenVacia), color);
                }

                for(CheckBox checkBox : checkBoxes)
                {
                    checkBox.setChecked(false);
                }
            }
        });

        //Este botón alterna entre la pantalla inicial y menú
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                String texto;
                int color;

                if(isChecked)
                {
                    texto = getString(R.string.textoToastOn);
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
                    texto = getString(R.string.textoToastOff);
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
    }

    //Este método llama al toast personalizado con un stringbuilder en vez de un string
    //para no tener que hacer el cast manualmente
    private void showToast(StringBuilder texto, int color)
    {
        showToast(texto.toString(), color);
    }

    //Este método muestra un toast personalizado
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