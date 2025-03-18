package com.capacitacion.listeners;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.Gravity;
import androidx.core.content.ContextCompat;
import android.graphics.drawable.GradientDrawable;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView titulo = (TextView) findViewById(R.id.titulo);
        Button boton1 = (Button) findViewById(R.id.boton1);
        Button boton2 = (Button) findViewById(R.id.boton2);

        boton1.setOnClickListener(this);
        boton2.setOnClickListener(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //Listeners agrupados en un solo método
    @Override
    public void onClick(View view)
    {
        int id = view.getId();
        String texto;
        int color;

        if(id == R.id.boton1)
        {
            texto = getString(R.string.textotoast1);
            color = ContextCompat.getColor(this, R.color.azulchillon);
            showToast(texto, color);
        }
        else if(id == R.id.boton2)
        {
            texto = getString(R.string.textotoast2);
            color = ContextCompat.getColor(this, R.color.rojochillon);
            showToast(texto, color);
        }
    }

    //Este método muestra un toast personalizado dependiendo del botón que piques
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