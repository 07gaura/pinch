package com.example.textpinchzoom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    private TextView textView;
    float ratio = 1.0f;
    final static float move = 200;
    int basedistance;
    float baseratio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.text);
        textView.setTextSize(ratio+15);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getPointerCount()==2){
            int action = event.getAction();
            int mainaction = action&MotionEvent.ACTION_MASK;
            if(mainaction == MotionEvent.ACTION_POINTER_DOWN){
                basedistance = getdistance(event);
                baseratio = ratio;
            }else{
                float scale = (getdistance(event)-basedistance)/move;
                float factor = (float)Math.pow(2, scale);
                ratio = Math.min(1024.0f, Math.max(0.1f, baseratio*factor));
                textView.setTextSize(ratio+15);
            }
        }
        return true;
    }

    private int getdistance(MotionEvent event) {
        int dx = (int)(event.getX(0)-event.getX(1));
        int dy = (int)(event.getY(0)-event.getY(1));
        return (int)(Math.sqrt(dx*dx+dy*dy));
    }
}