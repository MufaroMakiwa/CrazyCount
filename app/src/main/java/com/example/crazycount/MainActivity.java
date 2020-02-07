package com.example.crazycount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button count_up, count_down, reset, change_color;
    private TextView total;
    private int[] colors = {R.color.FirstFive, R.color.FirstFour, R.color.FirstOne,
                            R.color.FirstThree, R.color.FirstTwo, R.color.FirstSix,
                            R.color.original};
    private int color_index = 0;
    private int current_color = R.color.original;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpUIViews();

        count_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(String.valueOf(total.getText()))<9999){
                    int number = Integer.parseInt(String.valueOf(total.getText())) + 1;
                    total.setText(Integer.toString(number));
                    count++;
                } else {
                    new CountDownTimer(1000, 1) {
                        public void onTick(long millisUntilFinished) {
                            total.setText(R.string.limit);
                        }

                        public void onFinish() {
                            total.setText(R.string.maximum);
                        }
                    }.start();

                }
            }
        });

        count_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(String.valueOf(total.getText()))>-9999){
                    int number = Integer.parseInt(String.valueOf(total.getText())) - 1;
                    total.setText(Integer.toString(number));
                    count--;
                } else {
                    new CountDownTimer(1000, 1) {
                        public void onTick(long millisUntilFinished) {
                            total.setText(R.string.limit);
                        }

                        public void onFinish() {
                            total.setText(R.string.minimum);
                        }
                    }.start();

                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total.setText(R.string.initial);
                findViewById(R.id.rootView).setBackgroundColor(
                        getResources().getColor(R.color.original));
                current_color = R.color.original;
                color_index = 0;
                count = 0;
            }
        });

        change_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (color_index<colors.length){
                    findViewById(R.id.rootView).setBackgroundColor(
                            getResources().getColor(colors[color_index]));
                } else {
                    color_index = 0;
                    findViewById(R.id.rootView).setBackgroundColor(
                            getResources().getColor(colors[color_index]));
                }
                current_color = colors[color_index];
                color_index++;
            }
        });

    }

    private void setUpUIViews(){
        count_down=findViewById(R.id.down);
        count_up=findViewById(R.id.up);
        reset=findViewById(R.id.reset);
        change_color=findViewById(R.id.color);
        total=findViewById(R.id.textView);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("total", count);
        outState.putInt("color", current_color);
        outState.putInt("index", color_index);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        count = savedInstanceState.getInt("total");
        current_color = savedInstanceState.getInt("color");
        total.setText(Integer.toString(count));
        findViewById(R.id.rootView).setBackgroundColor(
                getResources().getColor(current_color));
        color_index = savedInstanceState.getInt("index");

    }
}
