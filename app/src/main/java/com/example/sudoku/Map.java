package com.example.sudoku;

import android.app.Activity;

import java.util.Random;

public final class Map {
    private int[][] map = new int[9][9]; // сама карта
    private final int RESHUFFLES_COUNT = 200;
    private boolean[][] marks = new boolean[9][9]; // карта меток (метка - системная установка числа)
    GMap gMap; // графика карты
    Map(Activity activity,int x,int y)
    {
        gMap = new GMap(activity,this,x,y);
    }
    public void createMap()
    {
        createSolvedSudoku();
        Random rnd = new Random();
        int state;
        int[] answer;
        for(int i=0;i<RESHUFFLES_COUNT;i++)
        {
            state = rnd.nextInt(5);
            switch(state)
            {
                case 0:
                    globalReshuffle();
                    break;
                case 1:
                    answer = randomForInGroup();
                    inGroupRowReshuffle(answer[0],answer[1]);
                    break;
                case 2:
                    answer = randomForInGroup();
                    inGroupCollReshuffle(answer[0],answer[1]);
                    break;
                case 3:
                    answer = randomForGroups();
                    groupsRowReshuffle(answer[0],answer[1]);
                    break;
                case 4:
                    answer = randomForGroups();
                    groupsCollReshuffle(answer[0],answer[1]);
                    break;
                default:
                    break;
            }
        }
        deleteNumbers();
        printMap();
    }
    private void deMark(int i,int j)
    {
        marks[i][j] = false;
    }
    private void deleteNumbers()
    {
        Random rnd = new Random();
        int counter=0,i,j;
        while(81 - counter > Const.COUNT_MARKED_NUMBERS)
        {
            i = rnd.nextInt(9);
            j = rnd.nextInt(9);
            if(map[i][j]!=0) {
                map[i][j]=0;
                counter++;
            }
        }
    }

    private int[] randomForInGroup()
    {
        int[] answer = new int[2];
        Random rnd = new Random();
        answer[0]  = rnd.nextInt(8);
        if(answer[0]<=2)
        {
            answer[1] = answer[0]+1>2?answer[0]-1:answer[0]+1;
        }
        else if(answer[0]<=5)
        {
            answer[1] = answer[0]+1>5?answer[0]-1:answer[0]+1;
        }
        else
        {
            answer[1] = answer[0]+1>8?answer[0]-1:answer[0]+1;
        }
        return answer;
    }
    private int[] randomForGroups()
    {
        int[] answer = new int[2];
        Random rnd = new Random();
        switch(rnd.nextInt(3))
        {
            case 0:
                answer[0] = 0;
                answer[1] = (rnd.nextInt(2)+1)*3;
                break;
            case 1:
                answer[0] = 3;
                answer[1] = rnd.nextBoolean()?0:6;
                break;
            case 2:
                answer[0] = 6;
                answer[1] = 6-(rnd.nextInt(2)+1)*3;
                break;
            default:
                break;
        }
        return answer;
    }
    private void createSolvedSudoku()
    {
        map[0][0] = 1;
        map[0][1] = 2;
        map[0][2] = 3;

        map[0][3] = 4;
        map[0][4] = 5;
        map[0][5] = 6;

        map[0][6] = 7;
        map[0][7] = 8;
        map[0][8] = 9;

        map[1][0] = 4;
        map[1][1] = 5;
        map[1][2] = 6;

        map[1][3] = 7;
        map[1][4] = 8;
        map[1][5] = 9;

        map[1][6] = 1;
        map[1][7] = 2;
        map[1][8] = 3;

        map[2][0] = 7;
        map[2][1] = 8;
        map[2][2] = 9;

        map[2][3] = 1;
        map[2][4] = 2;
        map[2][5] = 3;

        map[2][6] = 4;
        map[2][7] = 5;
        map[2][8] = 6;

        map[3][0] = 2;
        map[3][1] = 3;
        map[3][2] = 4;

        map[3][3] = 5;
        map[3][4] = 6;
        map[3][5] = 7;

        map[3][6] = 8;
        map[3][7] = 9;
        map[3][8] = 1;

        map[4][0] = 5;
        map[4][1] = 6;
        map[4][2] = 7;

        map[4][3] = 8;
        map[4][4] = 9;
        map[4][5] = 1;

        map[4][6] = 2;
        map[4][7] = 3;
        map[4][8] = 4;

        map[5][0] = 8;
        map[5][1] = 9;
        map[5][2] = 1;

        map[5][3] = 2;
        map[5][4] = 3;
        map[5][5] = 4;

        map[5][6] = 5;
        map[5][7] = 6;
        map[5][8] = 7;

        map[6][0] = 3;
        map[6][1] = 4;
        map[6][2] = 5;

        map[6][3] = 6;
        map[6][4] = 7;
        map[6][5] = 8;

        map[6][6] = 9;
        map[6][7] = 1;
        map[6][8] = 2;

        map[7][0] = 6;
        map[7][1] = 7;
        map[7][2] = 8;

        map[7][3] = 9;
        map[7][4] = 1;
        map[7][5] = 2;

        map[7][6] = 3;
        map[7][7] = 4;
        map[7][8] = 5;

        map[8][0] = 9;
        map[8][1] = 1;
        map[8][2] = 2;

        map[8][3] = 3;
        map[8][4] = 4;
        map[8][5] = 5;

        map[8][6] = 6;
        map[8][7] = 7;
        map[8][8] = 8;
    }
    private void inGroupRowReshuffle(int firstRow,int secondRow)
    {
        int[] firstGroup = new int[9];
        for(int j=0;j<9;j++)
            firstGroup[j] = map[firstRow][j];
        for(int j=0;j<9;j++)
        {
            map[firstRow][j] = map[secondRow][j];
            map[secondRow][j] = firstGroup[j];
        }
    }
    private void inGroupCollReshuffle(int firstColl,int secondColl)
    {
        int[] firstGroup = new int[9];
        for(int i=0;i<9;i++)
            firstGroup[i] = map[i][firstColl];
        for(int i=0;i<9;i++)
        {
            map[i][firstColl] = map[i][secondColl];
            map[i][secondColl] = firstGroup[i];
        }
    }
    private void groupsRowReshuffle(int firstGroupI,int secondGroupI)
    {
        int[][] firstGroup = new int[3][9];
        for(int i=0;i<firstGroup.length;i++){
            for (int j = 0; j < firstGroup[i].length; j++) {
                firstGroup[i][j] = map[i + firstGroupI][j];
            }
        }
        for(int i=0;i<firstGroup.length;i++)
        {
            for(int j=0;j<firstGroup[i].length;j++)
            {
                map[i+firstGroupI][j] = map[i+secondGroupI][j];
                map[i+secondGroupI][j] = firstGroup[i][j];
            }
        }

    }
    private void groupsCollReshuffle(int firstGroupI,int secondGroupI)
    {
        int[][] firstGroup = new int[9][3];
        for(int i=0;i<firstGroup.length;i++){
            for (int j = 0; j < firstGroup[i].length; j++) {
                firstGroup[i][j] = map[i][j+firstGroupI];
            }
        }
        for(int i=0;i<firstGroup.length;i++)
        {
            for(int j=0;j<firstGroup[i].length;j++)
            {
                map[i][j+firstGroupI] = map[i][j+secondGroupI];
                map[i][j+secondGroupI] = firstGroup[i][j];
            }
        }
    }
    private void globalReshuffle()
    {
        int[][] copyMap = new int[9][9];
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                copyMap[i][j] = map[i][j];
            }
        }
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                map[i][j] = copyMap[j][i];
            }
        }
    }
    private void printMap()
    {
        for(int i=0;i<9;i++)
            for (int j = 0; j < 9; j++) {
                setMarkedNumber(i, j, map[i][j]);
                if(map[i][j] == 0)
                    deMark(i,j);
            }
    }

    public void setNumber(int i,int j,int value) // установить число в карте
    {
        if(!marks[i][j]) {
            map[i][j] = value;
            gMap.printSpecificNumber(i,j,map);
        }
        GameEngine.checkWinAndDefeat();
    }
    public boolean checkFullMap()
    {
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                if(gMap.getCell(i,j).getValue()==0)
                    return false;
            }
        }
        return true;
    }
    public boolean checkValidMap() // Проверить карту на правила
    {
        return checkBlocks() && checkHorizontalLines() && checkVerticalLines();
    }
    public void setMarkedNumber(int i,int j,int value) // Установить системное число
    {
        map[i][j] = value;
        gMap.printSpecificMarkedNumber(i,j,map);
        marks[i][j] = true;
    }
    private boolean checkBlocks() // проверка блоков
    {
        int[] block = new int[9];
        for(int z=0,i=0,j=0;z<9;z++)
        {
            if(j==9) {
                i += 3;
                j=0;
            }
            goOnBlock(i,j,block);
            if(!checkUnique(block))
                return false;
            j+=3;
        }
        return true;
    }
    private void goOnBlock(int startI, int startJ, int[] block) // создание блоков
    {
        for(int i=startI,j=startJ,z=0;z<block.length;z++)
        {
            if(j-3 == startJ) {
                i++;
                j = startJ;
            }
            block[z] = map[i][j++];
        }
    }
    private boolean checkHorizontalLines() // проверка горизонтальных линий
    {
        int[] line = new int[9];
        for(int i=0;i<line.length;i++)
        {
            for(int j=0;j<line.length;j++)
            {
                line[j] = map[i][j];
            }
            if(!checkUnique(line))
                return false;
        }
        return true;
    }
    private boolean checkVerticalLines() // проверка вертикальных линий
    {
        int[] line = new int[9];
        for(int i=0;i<line.length;i++)
        {
            for(int j=0;j<line.length;j++)
            {
                line[j] = map[j][i];
            }
            if(!checkUnique(line))
                return false;
        }
        return true;
    }
    private boolean checkUnique(int[] numbers) // проверка уникальности последовательности чисел
    {
        int[] uniqueNumbers = new int[9];
        for(int i=0;i<uniqueNumbers.length;i++)
        {
            if(numbers[i]!=0)
                uniqueNumbers[numbers[i]-1]++;
        }
        for (int uniqueNumber : uniqueNumbers) {
            if (uniqueNumber > 1)
                return false;
        }
        return true;
    }
}
