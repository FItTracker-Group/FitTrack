package com.codepath.fittrack.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.codepath.fittrack.R;

import java.util.Locale;

public class TimerFragment extends Fragment {

    public static final String TAG = "TimerFragment";
    //private static final long START_TIME_IN_MILLIS = 600000;
    private static final long START_TIME_IN_MILLIS = 0;
    private TextView tvCountDown;
    private AppCompatButton btStartPause;
    private AppCompatButton btReset;
    private AppCompatButton btPlus5;
    private AppCompatButton btMinus5;
    private AppCompatButton btPlus1;
    private AppCompatButton btMinus1;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    public TimerFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvCountDown = view.findViewById(R.id.tvCountdown);

        btStartPause = view.findViewById(R.id.btStartPause);
        btReset = view.findViewById(R.id.btReset);
        btPlus5 = view.findViewById(R.id.btPlus5);
        btPlus1 = view.findViewById(R.id.btPlus1);
        btMinus5 = view.findViewById(R.id.btMinus5);
        btMinus1 = view.findViewById(R.id.btMinus1);

        btStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTimerRunning){
                    pauseTimer();
                } else{
                    startTimer();
                }
            }
        });

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });

        btPlus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimeLeftInMillis += 300000;
                updateCountDownText();
            }
        });

        btPlus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimeLeftInMillis += 60000;
                updateCountDownText();
            }
        });

        btMinus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTimeLeftInMillis > 300000){
                    mTimeLeftInMillis -= 300000;
                    updateCountDownText();
                }
                else{
                    mTimeLeftInMillis = 0;
                    Toast.makeText(getActivity(), "You cannot decrease the timer any further", Toast.LENGTH_SHORT).show();
                    updateCountDownText();
                    btStartPause.setVisibility(View.INVISIBLE);
                    btReset.setVisibility(View.VISIBLE);
                }
            }
        });

        btMinus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTimeLeftInMillis > 60000){
                    mTimeLeftInMillis -= 60000;
                    updateCountDownText();
                }
                else{
                    mTimeLeftInMillis = 0;
                    Toast.makeText(getActivity(), "You cannot decrease the timer any further", Toast.LENGTH_SHORT).show();
                    updateCountDownText();
                    btStartPause.setVisibility(View.INVISIBLE);
                    btReset.setVisibility(View.VISIBLE);
                }
            }
        });

        updateCountDownText();
    }

    private void startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long l) {
                mTimeLeftInMillis = l;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                btStartPause.setText("Start");
                btStartPause.setVisibility(View.INVISIBLE);
                btReset.setVisibility(View.VISIBLE);
            }
        }.start();
        if(mTimeLeftInMillis > 0){
            mTimerRunning = true;
            btStartPause.setText("Pause");
            btReset.setVisibility(View.INVISIBLE);
            btPlus5.setVisibility(View.INVISIBLE);
            btPlus1.setVisibility(View.INVISIBLE);
            btMinus5.setVisibility(View.INVISIBLE);
            btMinus1.setVisibility(View.INVISIBLE);
        }
        else{
            Toast.makeText(getActivity(), "Increase the time", Toast.LENGTH_SHORT).show();
        }

    }

    private void pauseTimer(){
        mCountDownTimer.cancel();
        mTimerRunning = false;
        btStartPause.setText("Start");
        btReset.setVisibility(View.VISIBLE);
    }

    private void resetTimer(){
        mTimeLeftInMillis= START_TIME_IN_MILLIS;
        updateCountDownText();
        btReset.setVisibility(View.INVISIBLE);
        btStartPause.setVisibility(View.VISIBLE);
        btPlus5.setVisibility(View.VISIBLE);
        btPlus1.setVisibility(View.VISIBLE);
        btMinus5.setVisibility(View.VISIBLE);
        btMinus1.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText(){
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60; //divide by 1000 to turn into seconds then divide by 60 for minutes
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60; //remaining seconds

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds); //convert our minutes and seconds into a "time" string
        tvCountDown.setText(timeLeftFormatted);
    }
}
