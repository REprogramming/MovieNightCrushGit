package com.filip.movienightcrush;

import android.os.Debug;
import android.util.Log;
import android.view.MotionEvent;

import com.filip.androidgames.framework.Input;
import com.filip.movienightcrush.OnSwipeTouchListener;
import com.filip.androidgames.framework.Graphics;
import com.filip.androidgames.framework.Pixmap;
import com.filip.androidgames.framework.impl.AndroidGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by RE1010 on 2017-10-10.
 */

public class FoodPiece {
    FoodType food;
    public int x;
    public int y;
    public int rowIndex;
    public int colIndex;
    public Pixmap image;
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

        //Input.TouchEvent.TOUCH_UP;

        /*image.setOnTouchListener(new OnSwipeTouchListener(AndroidGame.GetContext()) {
            public void onSwipeTop() {
                //Toast.makeText(MyActivity.this, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                // Toast.makeText(MyActivity.this, "right", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeLeft() {
                ///Toast.makeText(MyActivity.this, "left", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeBottom() {
                //Toast.makeText(MyActivity.this, "bottom", Toast.LENGTH_SHORT).show();
            }

        });*/

    }

    public void matchCheck() {

        boolean checking = true;
        int offset = 1;

            // start checking all sides, starting with RIGHT-side
            while (checking)
            {
                if (this.image == Grid.g[colIndex + offset][rowIndex].image) // I THINK THIS IS THE PROBLEM GRID.G IS NOT BEING REFRESHED AS IT IS STATIC AND THEREFORE IT NEVER CHANGES
                {
                    Log.d("myApp", "1st match!");
                    if (offset >= 3) {
                        for (int i = offset; i >= 0; i--)
                        {
                            Log.d("myApp", "3 matches!");
                            Grid.g[colIndex + offset][rowIndex].food = null;
                        }
                    }
                    offset++;
                }
                else
                {
                    Log.d("myApp", "No match (RIGHT-side)");
                    checking = false;
                    offset = 1;
                }
            }


       /* if( colIndex +1 < Grid.COLS)
        {
           checkRight();
        }
        if( rowIndex > 0)
        {
            checkBottom();
        }
        if( rowIndex +1 < Grid.ROWS)
        {
            checkTop();
        }*/


        }


    private void checkRight()
    {
        Log.d("myApp", "Have entered into checkRight()");
        boolean checking = true;
        int offset = 1;
        while(checking)
        {
            if(this.image == Grid.g[colIndex + offset][rowIndex].image)
            {
                Log.d("myApp", "Have entered into offset");
                offset++;
                //Grid.g[colIndex + offset][rowIndex].image = null;
                if(offset >= 3) {
                    for (int i = offset - 1; i >= 0; i--) {
                        Grid.g[colIndex + offset][rowIndex].image = null;
                        //Grid.g[colIndex + i][rowIndex].x = 100000;
                        //Grid.g[colIndex + i][rowIndex].food = FoodType.COUNT;
                    }
                }

            }
            else
            {
                Log.d("myApp", "Have exited into offset");
                checking = false;
            }
        }



    }

    private void checkLeft()
    {
        boolean flag = true;
        int offset = 0;
        while(flag)
        {
            offset++;
            if(colIndex - offset >= 0) {
                if (this.food == Grid.g[colIndex - offset][rowIndex].food) {
                    //Grid.g[colIndex - offset][rowIndex].x = 100000;
                }
                else
                {
                    flag = false;
                }
            }else
            {
                flag = false;
            }
        }
        if(offset >= 3) {
            for (int i = offset - 1; i >= 0; i--) {
                Grid.g[colIndex - i][rowIndex].x = 100000;
                Grid.g[colIndex - i][rowIndex].food = FoodType.COUNT;
            }
        }
    }

    private void checkBottom()
    {
        boolean flag = false;
        int offset = 1;
        while(!flag)
        {
            offset++;
            if(rowIndex + offset < Grid.ROWS) {
                if (this.food == Grid.g[colIndex][rowIndex + offset].food) {
                    //Grid.g[colIndex + offset][rowIndex].image = null;
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
        if(offset >= 3) {
            for (int i = offset - 1; i >= 0; i--) {
                Grid.g[colIndex][rowIndex + i].x = 100000;
                Grid.g[colIndex][rowIndex + i].food = FoodType.COUNT;
            }
        }

    }
    private void checkTop()
    {
        boolean flag = true;
        int offset = 0;
        while(flag)
        {
            offset++;
            if(rowIndex - offset >= 0) {
                if (this.food == Grid.g[colIndex][rowIndex - offset].food) {
                    //Grid.g[colIndex - offset][rowIndex].x = 100000;
                }
                else
                {
                    flag = false;
                }
            }else
            {
                flag = false;
            }
        }
        if(offset >= 3) {
            for (int i = offset - 1; i >= 0; i--) {
                Grid.g[colIndex][rowIndex - i].x = 100000;
                Grid.g[colIndex][rowIndex - i].food = FoodType.COUNT;
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
        /*else if(colIndex < Grid.COLS)
        {
            //check right
            if(Grid.g[colIndex+1][rowIndex].food == _foodType)
                isMatch = matchDepth(1, _foodType, iteration);
        }*/
        /*if(colIndex > 0)
        {
            //check top
            if(Grid.g[colIndex-1][rowIndex].food == _foodType) {
                iteration = 1;
                matchDepth = matchDepth(0, _foodType);
            }
            if(matchDepth > 2)
                return matchDepth;
            //
        }*/
        /*
        else if(rowIndex < Grid.ROWS)
        {
            //check botton
            if(Grid.g[colIndex][rowIndex+1].food == _foodType)
                isMatch = matchDepth(2, _foodType, iteration);
        }*/

        return matchDepth;
    }

    ///         ^ - 0
    ///     <-3     > - 1
    ///         _ - 2
    ///
    private int matchDepth(int direction, FoodType _foodType)
    {
        iteration++;
        switch(direction)
        {
            case 0:
                if(colIndex-iteration >= 0) {
                    if (Grid.g[colIndex - iteration][rowIndex].food == _foodType) {
                        matchDepth(direction, _foodType);
                    }
                }
                break;
            case 1:
                if(colIndex+iteration < Grid.COLS) {
                    if (Grid.g[colIndex + iteration][rowIndex].food == _foodType) {
                        matchDepth(direction, _foodType);
                    }
                }
                break;
            case 2:
                if(rowIndex+iteration < Grid.ROWS) {
                    if (Grid.g[colIndex][rowIndex + iteration].food == _foodType) {
                        matchDepth(direction, _foodType);
                    }
                }
                break;
            case 3:
                if(rowIndex-iteration >= 0) {
                    if (Grid.g[colIndex][rowIndex - iteration].food == _foodType) {
                        matchDepth(direction, _foodType);
                    }
                }
                break;
        }

        return iteration;
    }

    public void show(Graphics g)
    {
        g.drawPixmap(image,x, y);
    }


}
