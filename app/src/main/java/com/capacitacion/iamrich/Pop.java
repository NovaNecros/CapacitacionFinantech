/* Clase que controla la forma y el tamaño de la venta Pop como una actividad */

package com.capacitacion.iamrich;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;


public class Pop extends Activity
{
    protected void onCreate(Bundle SavedInstanceState)
    {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.popwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //aquí se ajustan las dimensiones de la ventana
        getWindow().setLayout((int)(width*0.6),(int)(height*0.3));
    }
}

