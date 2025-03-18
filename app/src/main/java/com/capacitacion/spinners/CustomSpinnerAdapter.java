/* Clase para Spinners personalizados que alternan colores de fondo */

package com.capacitacion.spinners;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class CustomSpinnerAdapter extends ArrayAdapter<String>
{
    private Context context;
    private String [] items;

    public CustomSpinnerAdapter(@NonNull Context context, int resource, @NonNull String [] objects)
    {
        super(context, resource, objects);
        this.context = context;
        this.items = objects;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View view = super.getView(position, convertView, parent);
        setColorAlterno(position, view);
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View view = super.getDropDownView(position, convertView, parent);
        setColorAlterno(position, view);
        return view;
    }

    //Con este métdo se alternan los colores del dropdown
    private void setColorAlterno(int position, View view)
    {
        if(view instanceof TextView)
        {
            TextView textView = (TextView) view;
            int color = position == 0 ? R.color.plateado : (position % 2 == 0) ? R.color.brat : R.color.verdebosque;
            textView.setBackgroundColor(ContextCompat.getColor(context, color));
        }
    }
}
