package com.filip.movienightcrush;

/**
 * Created by RE1010 on 2017-10-02.
 */

import com.filip.androidgames.framework.Game;
import com.filip.androidgames.framework.Graphics;
import com.filip.androidgames.framework.Input.TouchEvent;
import com.filip.androidgames.framework.Pixmap;
import com.filip.androidgames.framework.Screen;
import java.util.List;

public class OptionsScreen extends Screen {

    private static Pixmap background;
    private static Pixmap playButton;

    public OptionsScreen(Game game){
        super(game);
        Graphics g = game.getGraphics();
        background = g.newPixmap("background.png", Graphics.PixmapFormat.RGB565);
        playButton = g.newPixmap("playButton.png", Graphics.PixmapFormat.ARGB4444);
    }

    @Override
    public void update(float someInt){}
    @Override
    public void present(float someInt){};
    @Override
    public void pause(){}
    @Override
    public void resume(){}
    @Override
    public void dispose(){}
}