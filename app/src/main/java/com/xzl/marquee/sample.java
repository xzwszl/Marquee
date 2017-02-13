package com.xzl.marquee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xzl.marquee.library.MarqueeView;

public class sample extends AppCompatActivity {

    private MarqueeView marqueeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        marqueeView = (MarqueeView)findViewById(R.id.marquee);
        marqueeView.setText("This is a demo");
    }

    public void start(View view) {
        marqueeView.start();
    }

    public void pause(View view) {
        marqueeView.pause();
    }

    public void stop(View view) {
        marqueeView.stop();
    }
}
