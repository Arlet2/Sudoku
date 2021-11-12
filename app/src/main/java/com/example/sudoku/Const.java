package com.example.sudoku;

import android.app.Activity;
import android.util.Log;

public final class Const {
    static public int cellSize;
    static public float kX,kY;
    static public int scrWidth,scrHeight;
    static public int mapX,mapY;
    static public final int COUNT_MARKED_NUMBERS = 40;

    public static void setScreenSize(Activity activity)
    {
        scrWidth = activity.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        scrHeight = activity.getApplicationContext().getResources().getDisplayMetrics().heightPixels;
        kX = scrHeight / 1454f;
        kY = scrWidth / 720f;
        cellSize = (int)(75 * kX);
        mapX = (int)(25 * kX);
        mapY = (int)(60 * kY);
        Log.d("SUDOKU","kX: "+kX+" kY: "+kY);
    }
}
