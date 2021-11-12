package com.example.sudoku;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public final class DigitPanel {
    ImageView[] digits = new ImageView[9];
    ImageView deleteDigit;
    private float x=Const.mapX,y=Const.mapY*9+(250*Const.kY);
    private Map map;
    DigitPanel(Activity activity,Map map)
    {
        this.map = map;
        for(int i=0;i<9;i++)
        {
            digits[i] = new ImageView(activity);
            printDigit(digits[i],i+1);
            digits[i].setX(x+i*Const.cellSize);
            digits[i].setY(y);
            activity.addContentView(digits[i],new RelativeLayout.LayoutParams(Const.cellSize,Const.cellSize));
            final int a = i;
            digits[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkSomethingChosen())
                        fillChosenCell(a+1);
                }
            });
        }
        deleteDigit = new ImageView(activity);
        deleteDigit.setImageResource(R.drawable.lastik);
        deleteDigit.setX(x);
        deleteDigit.setY(y+Const.mapY+(10*Const.kY));
        deleteDigit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkSomethingChosen())
                    fillChosenCell(0);
            }
        });
        activity.addContentView(deleteDigit,new RelativeLayout.LayoutParams(Const.cellSize,Const.cellSize));

    }
    private void printDigit(ImageView digit,int value)
    {
        switch(value)
        {
            case 1:
                digit.setImageResource(R.drawable.digit1);
                break;
            case 2:
                digit.setImageResource(R.drawable.digit2);
                break;
            case 3:
                digit.setImageResource(R.drawable.digit3);
                break;
            case 4:
                digit.setImageResource(R.drawable.digit4);
                break;
            case 5:
                digit.setImageResource(R.drawable.digit5);
                break;
            case 6:
                digit.setImageResource(R.drawable.digit6);
                break;
            case 7:
                digit.setImageResource(R.drawable.digit7);
                break;
            case 8:
                digit.setImageResource(R.drawable.digit8);
                break;
            case 9:
                digit.setImageResource(R.drawable.digit9);
                break;
            default:
                digit.setImageResource(R.drawable.none);
                break;
        }
    }
    private boolean checkSomethingChosen()
    {
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                if(map.gMap.getCell(i,j).getChosen())
                    return true;
            }
        }
        return false;
    }
    private void fillChosenCell(final int value)
    {
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                if(map.gMap.getCell(i,j).getChosen())
                    map.setNumber(i,j,value);
            }
        }
    }
}
