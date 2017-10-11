package com.filip.movienightcrush;

/**
 * Created by RE1010 on 2017-10-10.
 */

import android.util.Log;
import com.filip.androidgames.framework.Graphics;


import java.util.Random;

public class Grid {

    private static final String TAG = GameScreen.class.getSimpleName();
    Random rn = new Random();

    static final int ROWS = 9;
    static final int COLS = 9;
    static final int TILE_WIDTH = 72;
    static final int TILE_HEIGHT = 72;

    int centerWtoScreen = 80;
    int centerHtoScreen = 260;

    FoodPiece[][] g = new FoodPiece[9][9];

    public Grid(Graphics graphics)
    {
        for (int i = 0; i <  COLS; i++)
        {
            for(int j=0; j < ROWS; j++)
            {
                int answer = rn.nextInt(8) + 0;
                g[i][j] = new FoodPiece(i * TILE_WIDTH + centerWtoScreen, j * TILE_HEIGHT + centerHtoScreen, FoodType.values()[answer], graphics );
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
