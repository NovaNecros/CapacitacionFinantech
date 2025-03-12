package com.capacitacion.spinners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    List<String> family;

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

        Spinner familySpinner = (Spinner) findViewById(R.id.family_spinner);
        family = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.family)));
        family.add(0, getResources().getString(R.string.spinner_default));

        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, R.layout.spinner_item, family.toArray(new String[0]));
        adapter.setDropDownViewResource(R.layout.dropdown_item);

        familySpinner.setAdapter(adapter);
        familySpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        if(position != 0)
        {
            Toast.makeText(getApplicationContext(), "Elegiste a " + family.get(position), Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), ">:(", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {
        Toast.makeText(getApplicationContext(), ">:(", Toast.LENGTH_SHORT).show();

    }
}