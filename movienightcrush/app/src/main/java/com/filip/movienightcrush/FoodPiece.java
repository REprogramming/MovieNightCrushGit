package com.filip.movienightcrush;

import com.filip.androidgames.framework.Game;
import com.filip.androidgames.framework.Graphics;
import com.filip.androidgames.framework.Pixmap;

/**
 * Created by RE1010 on 2017-10-10.
 */

public class FoodPiece {
    FoodType foods;
    int x;
    int y;
    public Pixmap image;

    public FoodPiece(int _x, int _y, FoodType _foodType, Graphics g)
    {
        x=_x;
        y=_y;
        foods = _foodType;

        switch(_foodType)
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

    public void show(Graphics g)
    {
        g.drawPixmap(image,x, y);
    }
}
