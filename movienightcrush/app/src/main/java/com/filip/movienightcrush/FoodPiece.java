package com.filip.movienightcrush;

import android.os.Debug;
import android.util.Log;
import android.view.MotionEvent;

import com.filip.androidgames.framework.Input;
import com.filip.movienightcrush.OnSwipeTouchListener;
import com.filip.androidgames.framework.Graphics;
import com.filip.androidgames.framework.Pixmap;
import com.filip.androidgames.framework.impl.AndroidGame;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by RE1010 on 2017-10-10.
 */

public class FoodPiece {
    FoodType food;
    int x;
    int y;
    int rowIndex;
    int colIndex;
    public Pixmap image;
    boolean isMatched = false;

    List<FoodType> randomList;
    int iteration;
    List<FoodPiece> tempMatches;

    public FoodPiece(int _x, int _y, FoodType _foodType, Graphics g, int _colIndex, int _rowIndex)
    {
        tempMatches = new ArrayList<FoodPiece>();

        x=_x;
        y=_y;
        colIndex = _colIndex;
        rowIndex = _rowIndex;
        food = _foodType;

        randomList = new ArrayList<FoodType>();

        setRandom(_foodType, randomList);


        switch(food)
        {
            case POPCORN:
                image = g.newPixmap("popcorn.png", Graphics.PixmapFormat.RGB565);
                break;
            case POPDRINK:
                image = g.newPixmap("popdrink.png", Graphics.PixmapFormat.RGB565);
                break;
            case NACHO:
                image = g.newPixmap("nacho.png", Graphics.PixmapFormat.RGB565);
                break;
            case CHOCOLATEBAR:
                image = g.newPixmap("chocolatebar.png", Graphics.PixmapFormat.RGB565);
                break;
            case BEER:
                image = g.newPixmap("beer.png", Graphics.PixmapFormat.RGB565);
                break;
            case TICKET:
                image = g.newPixmap("ticket.png", Graphics.PixmapFormat.RGB565);
                break;
            case HOTDOG:
                image = g.newPixmap("hotdog.png", Graphics.PixmapFormat.RGB565);
                break;
            case FRIES:
                image = g.newPixmap("fries.png", Graphics.PixmapFormat.RGB565);
                break;
        }

    }


    public void isMatch()
    {

       CheckHorizontal();

        if(Grid.horizontalMatches.size() >= 2)
        {
            for (int offset:Grid.horizontalMatches) {
                Grid.g[colIndex + offset][rowIndex].isMatched = true;
                Grid.g[colIndex + offset][rowIndex].x = 100000;
                Grid.g[colIndex + offset][rowIndex].food = FoodType.COUNT;
            }
            this.isMatched = true;
            this.x = 100000;
            this.food = FoodType.COUNT;
            //ShiftPieces();
        }

        CheckVertical();

        if(Grid.verticalMatches.size() >= 2)
        {
            for (int offset:Grid.verticalMatches) {
                Grid.g[colIndex][rowIndex + offset].isMatched = true;
                Grid.g[colIndex][rowIndex + offset].x = 100000;
                Grid.g[colIndex][rowIndex + offset].food = FoodType.COUNT;
            }
            this.isMatched = true;
            this.x = 100000;
            this.food = FoodType.COUNT;
        }

    }

    private void CheckHorizontal(){
        Grid.horizontalMatches.clear();
        boolean flag = false;
        int iterator = 0;

        //left
        while(!flag)
        {
            iterator++;
            if(colIndex - iterator >=0)
            {
                if(this.food == Grid.g[colIndex - iterator][rowIndex].food)
                {
                    Grid.horizontalMatches.add(-iterator);
                }
                else
                {
                    flag = true;
                }
            }
            else
            {
                flag = true;
            }
        }

        flag = false;
        iterator = 0;

        //right
        while(!flag)
        {
            iterator++;
            if(colIndex + iterator < Grid.COLS)
            {
                if(this.food == Grid.g[colIndex + iterator][rowIndex].food)
                {
                    Grid.horizontalMatches.add(iterator);
                }
                else
                {
                    flag = true;
                }
            }
            else
            {
                flag = true;
            }
        }
    }

    private void CheckVertical(){
        Grid.verticalMatches.clear();
        boolean flag = false;
        int iterator = 0;

        //top
        while(!flag)
        {
            iterator++;
            if(rowIndex - iterator >=0)
            {
                if(this.food == Grid.g[colIndex][rowIndex - iterator].food)
                {
                    Grid.verticalMatches.add(-iterator);
                }
                else
                {
                    flag = true;
                }
            }
            else
            {
                flag = true;
            }
        }

        flag = false;
        iterator = 0;

        //right
        while(!flag)
        {
            iterator++;
            if(rowIndex + iterator < Grid.ROWS)
            {
                if(this.food == Grid.g[colIndex][rowIndex+ iterator].food)
                {
                    Grid.verticalMatches.add(iterator);
                }
                else
                {
                    flag = true;
                }
            }
            else
            {
                flag = true;
            }
        }
    }

    private void setRandom(FoodType _foodType, List<FoodType> randomList)
    {
        int matchDepth = CheckForMatch(_foodType);



        if(matchDepth > 2)
        {
            if(randomList.size() == 0) {
                for (int i = 0; i < FoodType.COUNT.getValue(); i++) {
                    if (FoodType.values()[i] != _foodType) {
                        randomList.add(FoodType.values()[i]);
                    }
                }

            }else {
                for (int i = 0; i < randomList.size(); i++) {
                    if (randomList.get(i) == _foodType) {
                        randomList.remove(i);
                        break;
                    }
                }
            }



            Random rn = new Random();
            int answer = rn.nextInt(randomList.size()) + 0;

            setRandom(randomList.get(answer), randomList);

        }else
        {
            food = _foodType;
        }


    }

    public int CheckForMatch(FoodType _foodType)
    {
        int matchDepth = 0;

        if(rowIndex > 0)
        {
            //check left
            if(Grid.g[colIndex][rowIndex-1].food == _foodType) {
                iteration = 1;
                boolean flag = false;
                while(!flag)
                {
                    iteration++;
                    if(rowIndex - iteration >=0) {
                        if (Grid.g[colIndex][rowIndex - iteration].food != _foodType)
                            flag = true;
                    }else
                    {
                        flag = true;
                    }
                }
                matchDepth = iteration;
            }
            if(matchDepth > 2)
                return matchDepth;
        }

        if(colIndex > 0)
        {
            //check top
            if(Grid.g[colIndex-1][rowIndex].food == _foodType) {
                iteration = 1;
                boolean flag = false;
                while(!flag)
                {
                    iteration++;
                    if(colIndex - iteration >=0) {
                        if (Grid.g[colIndex-iteration][rowIndex].food != _foodType)
                            flag = true;
                    }else
                    {
                        flag = true;
                    }
                }
                matchDepth = iteration;
            }
            if(matchDepth > 2)
                return matchDepth;
        }

        return matchDepth;
    }

    public void show(Graphics g)
    {
        g.drawPixmap(image,x, y);
    }


}
