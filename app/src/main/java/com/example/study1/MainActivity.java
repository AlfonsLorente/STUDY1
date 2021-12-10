package com.example.study1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.*;
import androidx.fragment.app.DialogFragment;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int buttonCounter = 0;
    private final int MAX_BUTTON_COUNTER = 50;
    private static final String KEY_INDEX = "counter";
    public static final String EXTRA_MESSAGE = "com.example.study1.MESSAGE";
    private Button clickMe;
    private EditText etClickMe;
    private Button sendButton;
    private TextView clickedTimes;
    private GestureDetector gestureDetector;
    private AppCompatButton fragmentButton;

    AppCompatButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            buttonCounter = savedInstanceState.getInt(KEY_INDEX,0);

        }
        sendButton = findViewById(R.id.send);
        RelativeLayout rl = findViewById(R.id.parentLayout);
        clickMe = findViewById(R.id.clickMe);
        etClickMe = findViewById(R.id.editTextClickMe);
        clickedTimes = findViewById(R.id.clickedTimes);
        clickedTimes.setText("" + buttonCounter);
        gestureDetector = new GestureDetector(this, new MyGestureListener());
        fragmentButton = findViewById(R.id.fragmentButton);
        button = findViewById(R.id.UselessButton);

        Log.d("debug", "hey");
       clickMe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(etClickMe.getVisibility() == View.INVISIBLE) {
                    MediaPlayer mediaPlayer;
                    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.ducksound);
                    etClickMe.setVisibility(View.VISIBLE);
                    sendButton.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                    buttonCounter++;
                    rl.setBackgroundResource(R.drawable.duck);
                    mediaPlayer.start();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    etClickMe.setVisibility(View.INVISIBLE);
                    button.setVisibility(View.INVISIBLE);
                    sendButton.setVisibility(View.INVISIBLE);
                    buttonCounter++;
                    rl.setBackgroundResource(R.drawable.background);
                }
                clickedTimes.setText("" + buttonCounter);
                Log.d("debug", "hey2");



            }


        });

       sendButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, ShowText.class);
               intent.putExtra(EXTRA_MESSAGE, etClickMe.getText().toString());
               startActivity(intent);
           }
       });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

    }

    private void openDialog() {
        DialogFragment dialogFragment = new DialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "DialogMessage");
    }


    public void FragmentButtonClick(View view){
        Log.d("Fragment button", "Holiiis");
        Toast.makeText(this, "Holiiis", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, buttonCounter);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener{
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent e) {
            Toast.makeText(MainActivity.this,"On down", Toast.LENGTH_SHORT).show();
            return true;

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d("E1", "" + (e1.getY() - e1.getX()));
            Log.d("E2", "" + (e2.getY() - e2.getX()));
            Log.d("sp", " ");

            if(distanceX > 230 && distanceX < 230);
            return true;
        }
    }
}