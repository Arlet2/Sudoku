package com.example.sudoku;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public final class Cell {
    private ImageView image;
    private ImageView number;
    private ImageView playerInteraction;
    private boolean chosen;
    private boolean correct=true;
    private int value;
    private Map map;
    Cell(Activity activity, int x, int y, String type,final Map map)
    {
        this.map = map;
        image = new ImageView(activity);
        number = new ImageView(activity);
        playerInteraction = new ImageView(activity);
        switch(type)
        {
            case "upLeftCorner":
                image.setImageResource(R.drawable.upleftcorner);
                break;
            case "upRightCorner":
                image.setImageResource(R.drawable.uprightcorner);
                break;
            case "downRightCorner":
                image.setImageResource(R.drawable.downrightcorner);
                break;
            case "downLeftCorner":
                image.setImageResource(R.drawable.downleftcorner);
                break;
            case "left":
                image.setImageResource(R.drawable.left);
                break;
            case "right":
                image.setImageResource(R.drawable.right);
                break;
            case "down":
                image.setImageResource(R.drawable.down);
                break;
            case "up":
                image.setImageResource(R.drawable.up);
                break;
            case "plain":
                image.setImageResource(R.drawable.plain);
                break;
            default:
                break;
        }
        image.setX(x);
        image.setY(y);
        playerInteraction.setX(x);
        playerInteraction.setY(y);
        activity.addContentView(image,new RelativeLayout.LayoutParams(Const.cellSize,Const.cellSize));
        activity.addContentView(number,new RelativeLayout.LayoutParams(Const.cellSize,Const.cellSize));
        activity.addContentView(playerInteraction,new RelativeLayout.LayoutParams(Const.cellSize,Const.cellSize));

        playerInteraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.gMap.resetAllCellsToNotChosen();
                chooseCell();
            }
        });

        number.setX(x);
        number.setY(y);

        number.bringToFront();
        playerInteraction.bringToFront();
        defaultPlayerInteraction();
        changeNumber();
    }
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        changeNumber();
        if(!correct && map.checkValidMap())
            defaultPlayerInteraction();
        if(!map.checkValidMap()||(!correct&&!map.checkValidMap()))
        {
            faultCell();
            GameEngine.increaseFault();
        }
        else
            defaultPlayerInteraction();

    }
    public void setMarkedValue(int value)
    {
        this.value = value;
        changeMarkedNumber();
    }
    private void changeNumber()
    {
        switch(value) {
            case 1:
                number.setImageResource(R.drawable.digit1);
                break;
            case 2:
                number.setImageResource(R.drawable.digit2);
                break;
            case 3:
                number.setImageResource(R.drawable.digit3);
                break;
            case 4:
                number.setImageResource(R.drawable.digit4);
                break;
            case 5:
                number.setImageResource(R.drawable.digit5);
                break;
            case 6:
                number.setImageResource(R.drawable.digit6);
                break;
            case 7:
                number.setImageResource(R.drawable.digit7);
                break;
            case 8:
                number.setImageResource(R.drawable.digit8);
                break;
            case 9:
                number.setImageResource(R.drawable.digit9);
                break;
            default:
                number.setImageResource(R.drawable.none);
                break;
        }
    }
    private void changeMarkedNumber() {
        switch (value) {
            case 1:
                number.setImageResource(R.drawable.mdigit1);
                break;
            case 2:
                number.setImageResource(R.drawable.mdigit2);
                break;
            case 3:
                number.setImageResource(R.drawable.mdigit3);
                break;
            case 4:
                number.setImageResource(R.drawable.mdigit4);
                break;
            case 5:
                number.setImageResource(R.drawable.mdigit5);
                break;
            case 6:
                number.setImageResource(R.drawable.mdigit6);
                break;
            case 7:
                number.setImageResource(R.drawable.mdigit7);
                break;
            case 8:
                number.setImageResource(R.drawable.mdigit8);
                break;
            case 9:
                number.setImageResource(R.drawable.mdigit9);
                break;
            default:
                number.setImageResource(R.drawable.none);
                break;
        }
    }
    public void faultCell()
    {
        correct=false;
        playerInteraction.setImageResource(R.drawable.fault);
    }
    public void chooseCell()
    {
        if(map.checkValidMap() || !correct) {
            chosen = true;
            playerInteraction.setImageResource(R.drawable.choosed);
        }
    }
    public void defaultPlayerInteraction()
    {
        chosen=false;
        playerInteraction.setImageResource(R.drawable.none);
    }
    public boolean getChosen()
    {
        return chosen;
    }
    public void setChosen(boolean value)
    {
        chosen = value;
    }
    public boolean getCorrect()
    {
        return correct;
    }
}
