package com.filip.movienightcrush;

import com.filip.androidgames.framework.Game;
import com.filip.androidgames.framework.Screen;
import com.filip.androidgames.framework.impl.AndroidGame;

/**
 * Created by RE1010 on 2017-10-02.
 */

public class MovieNightCrush extends AndroidGame {

    @Override
    public Screen getStartScreen(){

        return new MainMenuScreen(this);
    }
}
