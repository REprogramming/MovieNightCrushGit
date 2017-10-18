package com.filip.movienightcrush;

/**
 * Created by RE1010 on 2017-10-10.
 */

import android.util.Log;
import com.filip.androidgames.framework.Graphics;
import com.filip.androidgames.framework.Pixmap;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid {

    private static final String TAG = GameScreen.class.getSimpleName();
    Random rn = new Random();

    static FoodPiece lastTouch;
    static List<Integer> horizontalMatches = new ArrayList<Integer>();
    static List<Integer> verticalMatches = new ArrayList<Integer>();

    static List<FoodPiece> horizontalMatchesObj = new ArrayList<FoodPiece>();
    static List<FoodPiece> verticalMatchesObj = new ArrayList<FoodPiece>();

    static List<FoodPiece> topHolder = new ArrayList<FoodPiece>();

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
            if(((food.colIndex == lastTouch.colIndex-1 || food.colIndex == lastTouch.colIndex+1 )&&(food.rowIndex == lastTouch.rowIndex))
                    || ((food.rowIndex == lastTouch.rowIndex-1 ||food.rowIndex == lastTouch.rowIndex+1)&& (food.colIndex == lastTouch.colIndex))) {

                //temp variables
                FoodType tempType = food.food;
                Pixmap tempPix = food.image;

                //swapping
                g[food.colIndex][food.rowIndex].food = g[lastTouch.colIndex][lastTouch.rowIndex].food;
                g[lastTouch.colIndex][lastTouch.rowIndex].food = tempType;

                g[food.colIndex][food.rowIndex].setFoodImage(g[lastTouch.colIndex][lastTouch.rowIndex].image);
                g[lastTouch.colIndex][lastTouch.rowIndex].setFoodImage(tempPix);

                //Check for matches
                g[lastTouch.colIndex][lastTouch.rowIndex].isMatch();
                g[food.colIndex][food.rowIndex].isMatch();
            }

            lastTouch = null;

        }
    }

    public static void CheckMatches()
    {
        for (int i = 0; i <  COLS; i++) {
            for (int j = 0; j < ROWS; j++) {
                g[i][j].isMatch();
            }
        }
    }

    public static void  moveUp(FoodPiece A, FoodPiece B)
    {
        FoodType tempType = A.food;
        Pixmap tempPix = A.image;
        Boolean tempMatch = A.isMatched;

        A.image = B.image;
        A.food = B.food;
        A.isMatched = B.isMatched;

        B.image = tempPix;
        B.food = tempType;
        B.isMatched = tempMatch;

        // Approach A...
        // FoodPiece self-refresh
        A.selfRefresh();
        B.selfRefresh();


        // ...or Approach B
        // Grid refresh
        if ((A.rowIndex == 0 && A.isMatched == true) || (B.rowIndex == 0 && B.isMatched == true )){
            //refreshGrid(A,B);
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


    public static void refreshGrid(FoodPiece topFoodPieceA, FoodPiece topFoodPieceB){

            Random rand = new Random();

            topFoodPieceA.food = FoodType.values()[rand.nextInt(6)+0];
            topFoodPieceA.image = topFoodPieceA.getFoodImage(); // TODO: IMAGE IS NOT CHANGING
            topFoodPieceA.isMatched = false;
            Log.d("my", "New FoodTypeA is: " + topFoodPieceA.food);

            topFoodPieceB.food = FoodType.values()[rand.nextInt(6)+0];
            topFoodPieceB.image = topFoodPieceB.getFoodImage(); // TODO: IMAGE IS NOT CHANGING
            topFoodPieceB.isMatched = false;
            Log.d("my", "New FoodTypeB is: " + topFoodPieceB.food);

    }

}
