package edu.louisville.cis490.memorygame;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by Kevin Le on 12/14/2014.
 */
public class GameCountDownTimer extends CountDownTimer
{

    private long timeElapsed;
    public long startTime;
    public long interval;
    public long timeRemain;

    public GameCountDownTimer(long startTime, long interval)
    {
        super(startTime, interval);
    }

    @Override
    public void onFinish()
    {


    }

    @Override
    public void onTick(long millisUntilFinished)
    {


    }
}
