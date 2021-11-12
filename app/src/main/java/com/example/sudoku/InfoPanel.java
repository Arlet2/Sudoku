package com.example.sudoku;

import android.app.Activity;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

public final class InfoPanel {
    private int fault=0;
    private TextView faultText;
    private TextView timerText;
    private long timeFromStart;
    InfoPanel(Activity activity)
    {
        InfoPanelTimer infoPanelTimer = new InfoPanelTimer(Long.MAX_VALUE,1000);
        createFaultText(activity);
        createTimerText(activity);
        infoPanelTimer.start();
    }
    private void createFaultText(Activity activity)
    {
        faultText = new TextView(activity);
        faultText.setText("Кол-во ошибок: "+fault+"/3");
        faultText.setX(Const.mapX);
        faultText.setY(25*Const.kY);

        activity.addContentView(faultText,new RelativeLayout.LayoutParams(Const.cellSize*5,Const.cellSize));
    }
    private void createTimerText(Activity activity)
    {
        timerText = new TextView(activity);
        timerText.setX(Const.mapX+(Const.cellSize*8));
        timerText.setY(25*Const.kY);

        activity.addContentView(timerText,new RelativeLayout.LayoutParams((int)(Const.cellSize*1.5),Const.cellSize));
    }
    public void updateFaults()
    {
        fault++;
        faultText.setText("Кол-во ошибок: "+fault+"/3");
        GameEngine.checkWinAndDefeat();
    }
    private void updateTimer()
    {
        long min = timeFromStart/1000/60;
        long sec = timeFromStart/1000%60;
        String strMin = min<1?"00":min<10?"0"+min:""+min;
        String strSec = sec<1?"00":sec<10?"0"+sec:""+sec;
        timerText.setText(strMin+":"+strSec);
    }
    public TextView getTimerText()
    {
        return timerText;
    }
    class InfoPanelTimer extends CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public InfoPanelTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            timeFromStart = Long.MAX_VALUE - millisUntilFinished;
            updateTimer();
        }

        @Override
        public void onFinish() {

        }
    }
}
