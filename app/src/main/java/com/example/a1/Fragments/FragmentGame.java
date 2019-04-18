package com.example.a1.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.a1.MainActivity;
import com.example.a1.R;

import java.util.Random;


public class FragmentGame extends Fragment {
    int[] colors;
    String[] names;

    Random random;
    Button start, btYes, btNo;
    int first, second, set, score;
    TextView leftText, rightText,  textScore;
    boolean scored;
    boolean vibroCheck;
    int value;
    int time;
    ProgressBar pb;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragment_game, container, false);

        textScore = (TextView) rootView.findViewById(R.id.textView3);

        time = this.getArguments().getInt("time");
        vibroCheck = this.getArguments().getBoolean("vibro");


        first = 0;
        second = 0;
        set = 0;
        score = 0;
        value = 5;

        rightText = (TextView) rootView.findViewById(R.id.textView2);
        leftText = (TextView) rootView.findViewById(R.id.textView1);
        pb = (ProgressBar) rootView.findViewById(R.id.progressBar);

        pb.setMax(time);


        start = (Button) rootView.findViewById(R.id.btStartStop);
        start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vibroCheck == true){
                    long mills = 200L;
                    Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
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
                            Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                            if (vibrator.hasVibrator()) {
                                vibrator.vibrate(mills);
                            }
                        }

                        textScore.setText("Ваш счёт: " + Integer.toString(score));
                    }
                }.start();
            }
        });

        btYes = (Button) rootView.findViewById(R.id.btYes);
        btYes.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });

        btNo = (Button) rootView.findViewById(R.id.btNo);
        btNo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

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
        });

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


        value = 5;


        second = random.nextInt(value);
        rightText.setTextColor(colors[second]);
        set = random.nextInt(value);
        rightText.setText(names[set]);

        first = random.nextInt(value);
        leftText.setText(names[first]);
        set = random.nextInt(value);
        leftText.setTextColor(colors[set]);




        return rootView;
    }



    public void SetTimeVibro(int t, boolean v){
        time = t;
        vibroCheck = v;

    }
}
