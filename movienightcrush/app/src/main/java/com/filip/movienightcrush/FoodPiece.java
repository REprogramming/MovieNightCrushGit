package com.filip.movienightcrush;

import android.os.Debug;
import android.util.Log;

import com.filip.androidgames.framework.Input;
import com.filip.movienightcrush.OnSwipeTouchListener;
import com.filip.androidgames.framework.Graphics;
import com.filip.androidgames.framework.Pixmap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    Pixmap image;
    boolean isMatched = false;

    List<FoodType> randomList;
    int iteration;

    // constructor
    public FoodPiece(int _x, int _y, FoodType _foodType, Graphics g, int _colIndex, int _rowIndex)
    {
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
            case EMPTY:
                image = g.newPixmap("empty.png", Graphics.PixmapFormat.RGB565);
                break;
        }
    }

    private void ShiftPieces()
    {
        if(Grid.verticalMatchesObj.size() >= 2) {
            Grid.verticalMatchesObj.add(this);
        }
        else
        {
            Grid.horizontalMatchesObj.add(this);
        }

        for (FoodPiece food: Grid.horizontalMatchesObj) {
            boolean flag = true;
            int iterator = 0;
            while(flag)
            {
                iterator++;
                if(food.rowIndex - iterator >= 0) {
                    //swap
                    Grid.moveUp(Grid.g[food.colIndex][food.rowIndex - iterator + 1], Grid.g[food.colIndex][food.rowIndex - iterator]);
                    //Grid.refreshGrid(Grid.g[food.colIndex][food.rowIndex - iterator + 1], Grid.g[food.colIndex][food.rowIndex - iterator]);

                }
                else
                {
                    flag = false;
                }
            }
        }


        Collections.sort(Grid.verticalMatchesObj, new Comparator<FoodPiece>()
        {
            @Override
            public int compare(FoodPiece lhs, FoodPiece rhs) {

                return Integer.valueOf(lhs.rowIndex).compareTo(rhs.rowIndex);
            }
        });


        for (FoodPiece food: Grid.verticalMatchesObj) {
            boolean flag = true;
            int iterator = 0;
            while(flag)
            {
                iterator++;
                if(food.rowIndex - iterator >= 0)
                {
                    //swap
                    Grid.moveUp(Grid.g[food.colIndex][food.rowIndex - iterator + 1], Grid.g[food.colIndex][food.rowIndex - iterator]);

                }
                else
                {
                    flag = false;
                }
            }
        }

        Grid.CheckMatches();

    }


    public void isMatch()
    {
        if(this.food == FoodType.COUNT)
            return;

       CheckHorizontal();

        if(Grid.horizontalMatchesObj.size() >= 2)
        {
            for (FoodPiece collectedFood : Grid.horizontalMatchesObj)
            {
                collectedFood.isMatched = true;
                //collectedFood.x = 100000;
                collectedFood.food = FoodType.COUNT;
            }

        }

        CheckVertical();

        if(Grid.verticalMatchesObj.size() >= 2)
        {

            for (FoodPiece collectedFood : Grid.verticalMatchesObj)
            {
                collectedFood.isMatched = true;
                //collectedFood.x = 100000;
                collectedFood.food = FoodType.COUNT;
            }

        }

        if(Grid.verticalMatchesObj.size() >= 2 || Grid.horizontalMatchesObj.size() >= 2) {
            this.isMatched = true;
            //this.x = 100000;
            this.food = FoodType.COUNT;
            ShiftPieces();
        }

    }

    private void CheckHorizontal(){
        //Grid.horizontalMatches.clear();
        Grid.horizontalMatchesObj.clear();
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
                    //Grid.horizontalMatches.add(-iterator);
                    Grid.horizontalMatchesObj.add(Grid.g[colIndex - iterator][rowIndex]);
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
                    //Grid.horizontalMatches.add(iterator);
                    Grid.horizontalMatchesObj.add(Grid.g[colIndex + iterator][rowIndex]);
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
        // Grid.verticalMatches.clear();
        Grid.verticalMatchesObj.clear();
        boolean flag = false;
        int iterator = 0;

        //bottom
        while(!flag)
        {
            iterator++;
            if(rowIndex + iterator < Grid.ROWS)
            {
                if(this.food == Grid.g[colIndex][rowIndex + iterator].food)
                {
                    Grid.verticalMatchesObj.add(Grid.g[colIndex][rowIndex+ iterator]);
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

        //top
        while(!flag)
        {
            iterator++;
            if(rowIndex - iterator >=0)
            {
                if(this.food == Grid.g[colIndex][rowIndex - iterator].food)
                {
                    //Grid.verticalMatches.add(-iterator);
                    Grid.verticalMatchesObj.add(Grid.g[colIndex][rowIndex - iterator]);
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
            }
            else
            {
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

        }
        else
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
        if(!isMatched)
            g.drawPixmap(image,x, y);
    }

    public void setFoodImage(Pixmap someImage){

       image = someImage;
    }

    public Pixmap getFoodImage(){

        return image;
    }

    public FoodPiece selfRefresh(){

        if (this.rowIndex == 0 && this.isMatched == true)
            {
                Random rand = new Random();
                this.food = FoodType.values()[rand.nextInt(7)];
                this.image = this.getFoodImage(); // TODO: IMAGE IS NOT CHANGING
                this.isMatched = false;
                Log.d("my", "Self-Refreshed tile is now: " + this.food);
            }

    return this;

    }


}
