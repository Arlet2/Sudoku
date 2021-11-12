package com.example.sudoku;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

public final class GameEngine {
    static Map map;
    static DigitPanel digitPanel;
    static InfoPanel infoPanel;
    static AlertDialog.Builder winDialog;
    static AlertDialog.Builder defeatDialog;
    static int fault=0;
    static public void start(Activity activity)
    {
        fault = 0;
        Const.setScreenSize(activity);
        map = new Map(activity,Const.mapX,Const.mapY);
        digitPanel = new DigitPanel(activity,map);
        infoPanel = new InfoPanel(activity);
        createWinDialog(activity);
        createDefeatDialog(activity);
        map.createMap();
    }
    static private void createWinDialog(final Activity activity)
    {
        winDialog = new AlertDialog.Builder(activity);
        winDialog.setTitle("Победа!");
        winDialog.setPositiveButton("Ура!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(activity, MenuActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }
        });
        winDialog.setCancelable(false);
    }
    static private void createDefeatDialog(final Activity activity)
    {
        defeatDialog = new AlertDialog.Builder(activity);
        defeatDialog.setTitle("Поражение");
        defeatDialog.setPositiveButton("Вернуться в меню", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(activity, MenuActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }
        });
        defeatDialog.setMessage("Вы допустили 3 ошибки");
        defeatDialog.setCancelable(false);
    }
    static public void checkWinAndDefeat()
    {
        if(map.checkValidMap()&&map.checkFullMap())
            win();
        else if(fault>=3)
            defeat();
    }
    static public void increaseFault()
    {
        fault++;
        infoPanel.updateFaults();
    }
    static private void win()
    {
        winDialog.setMessage("Победа! Вы решили судоку за "+infoPanel.getTimerText().getText());
        winDialog.show();
    }
    static private void defeat()
    {
        defeatDialog.show();
    }
}
