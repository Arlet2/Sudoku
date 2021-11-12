package com.example.sudoku;

import android.app.Activity;

public final class GMap {
    private Cell[][] cells = new Cell[9][9];
    /*
    Типы клеток:
    upLeftCorner,upRightCorner,downRightCorner,downLeftCorner,left,right,down,up,plain
    */
    GMap(Activity activity, Map map, int x, int y)
    {
        String type;
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++) {
                if((j==0 || j == 3 || j == 6)&&(i == 0 || i ==3 || i==6))
                    type="upLeftCorner";
                else if((j==1 || j == 4 || j == 7)&&(i == 0 || i ==3 || i==6))
                    type="up";
                else if((j==2 || j == 5 || j == 8)&&(i == 0 || i ==3 || i==6))
                    type="upRightCorner";
                else if((i==1||i==4||i==7) && (j==2||j==5||j==8))
                    type="right";
                else if((i==1||i==4||i==7) && (j==0||j==3||j==6))
                    type="left";
                else if((i==2||i==5||i==8) && (j==0||j==3||j==6))
                    type="downLeftCorner";
                else if((i==2||i==5||i==8) && (j==1||j==4||j==7))
                    type="down";
                else if(i == 2 || i == 5 || i == 8)
                    type = "downRightCorner";
                else
                    type="plain";
                cells[i][j] = new Cell(activity,j*Const.cellSize +x,i*Const.cellSize +y,type,map);
            }
        }
    }
    public void printSpecificNumber(int i, int j, int[][] map)
    {
        cells[i][j].setValue(map[i][j]);
    }
    public void printSpecificMarkedNumber(int i, int j, int[][] map)
    {
        cells[i][j].setMarkedValue(map[i][j]);
    }
    public Cell getCell(int i,int j)
    {
        return cells[i][j];
    }
    public void resetAllCellsToNotChosen()
    {
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                if(cells[i][j].getCorrect())
                    cells[i][j].defaultPlayerInteraction();
                else
                    cells[i][j].setChosen(false);
            }
        }
    }
}
