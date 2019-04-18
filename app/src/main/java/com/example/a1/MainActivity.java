package com.example.a1;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.a1.Fragments.FragmentGame;
import com.example.a1.Fragments.FragmentSettings;

import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    public boolean isGoing;
    TextView textName, textEmail;
    private String name;
    private String email;
    FragmentSettings fgS;
    FragmentGame fgG;
    FragmentTransaction ftrans;

    boolean vibroCheck, scored;
    int time, value, score;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        /*
        Toast toast = Toast.makeText(getApplicationContext(),
                "Запуск", Toast.LENGTH_SHORT);
        toast.show();
        */

        Intent NickEmIntent = getIntent();

        name = NickEmIntent.getStringExtra("name");
        email = NickEmIntent.getStringExtra("email");






        View navHeader = navigationView.getHeaderView(0);
        textName = (TextView) navHeader.findViewById(R.id.textName1);
        textEmail = (TextView) navHeader.findViewById(R.id.textEmail1);
        textName.setText(name);
        textEmail.setText(email);





        time = 10;
        vibroCheck = false;



        fgS = new FragmentSettings();
        fgG = new FragmentGame();

        ftrans = getFragmentManager().beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putInt("time", time);
        bundle.putBoolean("vibro", vibroCheck);
        fgG.setArguments(bundle);

        ftrans.replace(R.id.container, fgG);
        ftrans.commit();
    }









    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();



        if (id == R.id.nav_manage) {
            ftrans = getFragmentManager().beginTransaction();

            ftrans.replace(R.id.container, fgS);
            ftrans.commit();


            /* Активити настроек
            Intent intent = new Intent(this, settings.class);
            startActivityForResult(intent, 1);
            */


        } else if (id == R.id.nav_send) {

            String msg = String.valueOf(name) + ", ваш счёт: " + String.valueOf(score);
            String recipientList = email;
            String[] recipients = recipientList.split(",");

            String subject = "The Game Score";
            String message = msg;

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL, recipients);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, message);

            intent.setType("message/rfc822");
            startActivity(Intent.createChooser(intent, "Выберите отправитель"));


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                vibroCheck=data.getBooleanExtra("check", false);
                value=data.getIntExtra("colors", 5);
                time=data.getIntExtra("time", 10);
                scored=data.getBooleanExtra("score", false);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }

    }//onActivityResult

    public void CloseSettingsFragment(){
        ftrans = getFragmentManager().beginTransaction();

        ftrans.remove(fgS);

        //Применение настроек ТУТ
        CheckBox chBox;
        chBox = fgS.getView().findViewById(R.id.checkBox2);
        if (chBox.isChecked()){
            vibroCheck = true;
        }
        else {
            vibroCheck = false;
        }

        RadioButton rBt10;
        RadioButton rBt30;
        RadioButton rBt60;
        rBt10 = fgS.getView().findViewById(R.id.radioButton10);
        rBt30 = fgS.getView().findViewById(R.id.radioButton30);
        rBt60 = fgS.getView().findViewById(R.id.radioButton60);



        if (rBt10.isChecked()){
            time = 10;
        } else if (rBt30.isChecked()){
            time = 30;
        } else if (rBt60.isChecked()){
            time = 60;
        }

        Bundle bundle = new Bundle();
        bundle.putInt("time", time);
        bundle.putBoolean("vibro", vibroCheck);
        fgG.setArguments(bundle);



        ftrans.replace(R.id.container, fgG);
        ftrans.commit();

        fgG.SetTimeVibro(time, vibroCheck);
    }


}
