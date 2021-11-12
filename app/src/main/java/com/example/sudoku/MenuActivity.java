package com.example.sudoku;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.Random;

public class MenuActivity extends AppCompatActivity {

    Button buttonStart,buttonExit,buttonRules;
    AlertDialog.Builder rulesDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();
        Const.setScreenSize(this);
        createButtonExit();
        createButtonRules();
        createButtonStart();
    }
    void createButtonStart()
    {
        buttonStart = new Button(this);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        buttonStart.setText("Старт");
        addContentView(buttonStart,new RelativeLayout.LayoutParams(Const.cellSize*3,Const.cellSize));
        buttonStart.setX(Const.scrWidth/2-Const.cellSize*1.5f);
        buttonStart.setY(Const.cellSize);
    }
    void createButtonExit()
    {
        buttonExit = new Button(this);
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        buttonExit.setText("Выход");
        addContentView(buttonExit,new RelativeLayout.LayoutParams(Const.cellSize*3,Const.cellSize));
        buttonExit.setX(Const.scrWidth/2-Const.cellSize*1.5f);
        buttonExit.setY(Const.cellSize*3);
    }
    void createButtonRules()
    {
        createRulesDialog();
        buttonRules = new Button(this);
        buttonRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rulesDialog.show();
            }
        });
        buttonRules.setText("Правила");
        addContentView(buttonRules,new RelativeLayout.LayoutParams(Const.cellSize*3,Const.cellSize));
        buttonRules.setX(Const.scrWidth/2-Const.cellSize*1.5f);
        buttonRules.setY(Const.cellSize*2);
    }
    void createRulesDialog()
    {
        rulesDialog = new AlertDialog.Builder(this);
        rulesDialog.setCancelable(true);
        rulesDialog.setTitle("Правила");
        rulesDialog.setMessage("Судоку - математическая головоломка. Ваша задача собрать ее таким образом, чтобы все цифры в выделенных блоках, горизонтальных и вертикальных рядах были уникальными. Попробуйте решить!");
    }
}
