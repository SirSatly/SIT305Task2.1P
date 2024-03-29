package com.example.unitconverter;

import android.os.Bundle;
import android.view.*;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView outputText;
    Spinner sourceSpinner;
    Spinner destSpinner;
    EditText inputField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        outputText = findViewById(R.id.textResult);
        sourceSpinner = findViewById(R.id.spinnerSource);
        destSpinner = findViewById(R.id.spinnerDest);
        inputField = findViewById(R.id.editTextInput);

        Button submit = findViewById(R.id.button);
        submit.setOnClickListener(this::onSubmit);

        ArrayAdapter<String> lengthAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.unitsLength));
        ArrayAdapter<String> weightAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.unitsWeight));
        ArrayAdapter<String> temperatureAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.unitsTemperature));
        ArrayAdapter<?>[] spinnerAdapters = {lengthAdapter, weightAdapter, temperatureAdapter};

        Spinner types = findViewById(R.id.spinnerType);
        types.setOnItemSelectedListener(new onChangeType(spinnerAdapters));
    }

    public void onSubmit(View v) {
        double inputValue = Double.parseDouble(inputField.getText().toString());
        String inputType = (String) sourceSpinner.getSelectedItem();
        String outputType = (String) destSpinner.getSelectedItem();

        outputText.setText(ConversionHelper.Convert(inputValue, inputType, outputType) + " " + outputType);
    }

    private class onChangeType implements AdapterView.OnItemSelectedListener {
        Spinner sourceSpinner;
        Spinner destSpinner;
        ArrayAdapter<?>[] spinnerAdapters;

        public onChangeType(ArrayAdapter<?>[] spinnerAdapters)
        {
            this.spinnerAdapters = spinnerAdapters;

            sourceSpinner = findViewById(R.id.spinnerSource);
            destSpinner = findViewById(R.id.spinnerDest);
        }

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            sourceSpinner.setAdapter(spinnerAdapters[position]);
            destSpinner.setAdapter(spinnerAdapters[position]);
        }

        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}