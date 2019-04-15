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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.a1.Fragments.FragmentSettings;

import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    private int first, second, set, score;
    private int[] colors;
    private String[] names;
    private Random random;
    private TextView leftText, rightText, textName, textEmail, textScore;
    private ProgressBar pb;
    private Button btYes, btNo;
    public boolean vibroCheck, scored;
    public boolean isGoing;
    private Button start;
    private int value;
    private int time;
    private String name;
    private String email;


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
        Toast toast = Toast.makeText(getApplicationContext(),
                "Запуск", Toast.LENGTH_SHORT);
        toast.show();
        //

        Intent NickEmIntent = getIntent();

        name = NickEmIntent.getStringExtra("name");
        email = NickEmIntent.getStringExtra("email");






        View navHeader = navigationView.getHeaderView(0);
        textName = (TextView) navHeader.findViewById(R.id.textName1);
        textEmail = (TextView) navHeader.findViewById(R.id.textEmail1);
        textName.setText(name);
        textEmail.setText(email);
        textScore = findViewById(R.id.textView3);


        first = 0;
        second = 0;
        set = 0;
        score = 0;
        vibroCheck = false;
        value = 5;
        start = findViewById(R.id.btStartStop);

        rightText = findViewById(R.id.textView2);
        leftText = findViewById(R.id.textView1);
        pb = findViewById(R.id.progressBar);
        btYes = findViewById(R.id.btYes);
        btNo = findViewById(R.id.btNo);

        btYes.setEnabled(false);
        btNo.setEnabled(false);

        colors = new int[10];

        colors[0] = 0xFF000000; //black
        colors[1] = 0xFFFF0000; //red
        colors[2] = 0xFF0000FF; //blue
        colors[3] = 0xFF008000; //green
        colors[4] = 0xFFFFFF00; //yellow

        colors[5] = 0xFF808080; //gray
        colors[6] = 0xFFFFC0CB; //pink
        colors[7] = 0xFFA52A2A; //brown
        colors[8] = 0xFFFFA500; //orange
        colors[9] = 0xFFBA2BE2; //violet

        names = new String[10];

        names[0] = "Чёрный";
        names[1] = "Красный";
        names[2] = "Синий";
        names[3] = "Зелёный";
        names[4] = "Жёлтый";

        names[5] = "Серый";
        names[6] = "Розовый";
        names[7] = "Коричневый";
        names[8] = "Оранжевый";
        names[9] = "Фиолетовый";

        random = new Random();

        Intent intent = getIntent();
        value = 5;
        time  = 10;
        pb.setMax(time);

        second = random.nextInt(value);
        rightText.setTextColor(colors[second]);
        set = random.nextInt(value);
        rightText.setText(names[set]);

        first = random.nextInt(value);
        leftText.setText(names[first]);
        set = random.nextInt(value);
        leftText.setTextColor(colors[set]);

    }


    public void clickbtYes(View view){
        if (first == second){
            score ++;
        }

        second = random.nextInt(value);
        rightText.setTextColor(colors[second]);
        set = random.nextInt(value);
        rightText.setText(names[set]);

        first = random.nextInt(value);
        leftText.setText(names[first]);
        set = random.nextInt(value);
        leftText.setTextColor(colors[set]);
    }

    public void clickbtNo(View view){

        if (first != second){
            score++;
        }

        second = random.nextInt(value);
        rightText.setTextColor(colors[second]);
        set = random.nextInt(value);
        rightText.setText(names[set]);

        first = random.nextInt(value);
        leftText.setText(names[first]);
        set = random.nextInt(value);
        leftText.setTextColor(colors[set]);
    }

    public void clickbtStartStop (View view){

        if (vibroCheck == true){
            long mills = 200L;
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator.hasVibrator()) {
                vibrator.vibrate(mills);
            }
        }

        textScore.setText("");


        start.setEnabled(false);
        btYes.setEnabled(true);
        btNo.setEnabled(true);

        if (!scored){
            score = 0;
        }



        new CountDownTimer(time*1000, 1000){
            @Override
            public void onTick(long l){
                pb.setProgress((int)(l/1000));
            }

            @Override
            public void onFinish(){
                btYes.setEnabled(false);
                btNo.setEnabled(false);
                start.setEnabled(true);

                if (vibroCheck == true){
                    long mills = 1000L;
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    if (vibrator.hasVibrator()) {
                        vibrator.vibrate(mills);
                    }
                }

                textScore.setText("Ваш счёт: " + Integer.toString(score));
            }
        }.start();
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

        FragmentTransaction ftrans = getFragmentManager().beginTransaction();

        if (id == R.id.nav_manage) {

        Intent intent = new Intent(this, settings.class);
        startActivityForResult(intent, 1);


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


        } ftrans.commit();

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
}
