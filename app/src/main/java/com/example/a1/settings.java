package com.example.a1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.ToggleButton;

public class settings extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    public CheckBox checkBox;
    public boolean Check;
    private Spinner spinner;
    private RadioGroup rdGroup;
    private RadioButton rdButton;
    private RadioButton radiobutton1;
    private ToggleButton tgButton;
    private String name;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        checkBox = findViewById(R.id.checkBox2);
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinData, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        rdGroup = findViewById(R.id.radiogroup1);

        radiobutton1 =(RadioButton)findViewById(R.id.radioButton4);
        radiobutton1.setChecked(true);
        tgButton = findViewById(R.id.toggleButton3);

        Intent intent1 = getIntent();
        name = intent1.getStringExtra("name");
        email = intent1.getStringExtra("email");


    }

    public void exitclick (View view){
        Intent returnIntent = new Intent();



        if (checkBox.isChecked()){
            returnIntent.putExtra("check",  true);
        }
        else {
            returnIntent.putExtra("check",  false);
        }

        String S = "";
        int value = 0;
        S = spinner.getSelectedItem().toString();
        switch(S){
            case "Пять цветов":
                value = 5;
                break;


            case "Шесть цветов":
                value = 6;
                break;


            case "Семь цветов":
                value = 7;
                break;


            case "Восемь цветов":
                value = 8;
                break;


            case "Девять цветов":
                value = 9;
                break;


            case "Десять цветов":
                value = 10;
                break;


            default :
                value = 5;
                break;
        }
        returnIntent.putExtra("colors", value);

        int radioId = rdGroup.getCheckedRadioButtonId();
        rdButton = findViewById(radioId);

        S = rdButton.getText().toString();
        int value1 = 0;
        switch (S){
            case "10 секунд":
                value1 = 10;
                break;

            case "30 секунд":
                value1 = 30;
                break;

            case "60 секунд":
                value1 = 60;
                break;

            default:
                value1 = 10;
                break;
        }
        returnIntent.putExtra("time", value1);

        if (tgButton.isChecked()){
            returnIntent.putExtra("score",  true);
        }
        else {
            returnIntent.putExtra("score",  false);
        }

        returnIntent.putExtra("name", name);
        returnIntent.putExtra("email", email);

        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        //Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
