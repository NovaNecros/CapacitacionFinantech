package com.capacitacion.togglebuttons;

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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

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

        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.togglebutton);
        //TextView textView = (TextView) findViewById(R.id.texto);

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
                }
                else
                {
                    texto = getString(R.string.textoToastOff);
                    color = ContextCompat.getColor(getApplicationContext(), R.color.rojochillon);
                    showToast(texto,color);
                }
            }
        });
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