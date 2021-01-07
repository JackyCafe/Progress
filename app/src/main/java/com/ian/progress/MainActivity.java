package com.ian.progress;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ProgressBar bar;
    private TextView tv;
    private int progress_num = 0;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = findViewById(R.id.p_bar);
        tv = findViewById(R.id.tv);
        bar.setVisibility(View.GONE);
    }

    public void btn(View view) {
        progress_num = 0;
        bar.setProgress(0);
        bar.setVisibility(View.VISIBLE);
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (progress_num<100)
                        {
                            progress_num += 1;
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    tv.setText(progress_num+"/"+bar.getMax());
                                    bar.incrementProgressBy(1);
                                }
                            });

                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if (progress_num == 100)
                        {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    bar.setVisibility(View.GONE);
                                    tv.setText("Done");
                                }
                            });
                        }

                    }
                }
        ).start();
    }
}