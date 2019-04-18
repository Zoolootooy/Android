package com.example.a1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText name;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = findViewById(R.id.textName);
        email = findViewById(R.id.textEmail);
    }

    public void clickOK (View view) {
        /*
        Toast toast = Toast.makeText(getApplicationContext(),
                "Запуск главной", Toast.LENGTH_SHORT);
        toast.show();
        */
        Intent intent = new Intent(this, MainActivity.class);
        String S = "";
        S = name.getText().toString();
        intent.putExtra("name",  S);
        S = email.getText().toString();
        intent.putExtra("email",  S);

        startActivity(intent);

    }
}
