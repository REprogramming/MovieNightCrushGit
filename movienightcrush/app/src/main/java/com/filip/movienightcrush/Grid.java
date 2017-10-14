package com.filip.movienightcrush;

/**
 * Created by RE1010 on 2017-10-10.
 */

import android.util.Log;
import com.filip.androidgames.framework.Graphics;
import com.filip.androidgames.framework.Pixmap;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid {

    private static final String TAG = GameScreen.class.getSimpleName();
    Random rn = new Random();

    static FoodPiece lastTouch;
    static List<Integer> horizontalMatches = new ArrayList<Integer>();
    static List<Integer> verticalMatches = new ArrayList<Integer>();

    static final int ROWS = 9;
    static final int COLS = 9;
    static final int TILE_WIDTH = 72;
    static final int TILE_HEIGHT = 72;

    int centerWtoScreen = 80;
    int centerHtoScreen = 260;

    static FoodPiece[][] g = new FoodPiece[COLS][ROWS];

    public void swap(FoodPiece food)
    {
        if(lastTouch == null)
        {
            lastTouch = food;
        }else
        {

            if(((food.colIndex == lastTouch.colIndex-1 ||food.colIndex == lastTouch.colIndex+1 )&&(food.rowIndex == lastTouch.rowIndex))
                    || ((food.rowIndex == lastTouch.rowIndex-1 ||food.rowIndex == lastTouch.rowIndex+1)&& (food.colIndex == lastTouch.colIndex))) {

                //temp variables
                FoodType tempType = food.food;
                Pixmap tempPix = food.image;

                //swapping
                g[food.colIndex][food.rowIndex].image = g[lastTouch.colIndex][lastTouch.rowIndex].image;
                g[food.colIndex][food.rowIndex].food = g[lastTouch.colIndex][lastTouch.rowIndex].food;
                g[lastTouch.colIndex][lastTouch.rowIndex].image = tempPix;
                g[lastTouch.colIndex][lastTouch.rowIndex].food = tempType;


                //Check for matches
                g[lastTouch.colIndex][lastTouch.rowIndex].isMatch();
                g[food.colIndex][food.rowIndex].isMatch();

            }

            lastTouch = null;

        }
    }

    public Grid(Graphics graphics)
    {
        for (int i = 0; i <  COLS; i++)
        {
            for(int j=0; j < ROWS; j++)
            {
                int answer = rn.nextInt(8) + 0;
                g[i][j] = new FoodPiece(i * TILE_WIDTH + centerWtoScreen, j * TILE_HEIGHT + centerHtoScreen, FoodType.values()[answer], graphics, i, j);
                Log.d(TAG, "Grid: " + i + " " + j + "Foodtype: " + FoodType.values()[answer]);
            }
        }

    }

    public void show(Graphics graphic)
    {
        for (int i = 0; i <  ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                g[i][j].show(graphic);
            }
        }
    }

}
