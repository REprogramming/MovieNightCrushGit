package com.filip.movienightcrush;

import com.filip.androidgames.framework.Screen;
import com.filip.androidgames.framework.impl.AndroidGame;

/**
 * Created by Emily on 10/4/2017.
 */

public class MovieNightCrush extends AndroidGame {
    @Override
    public Screen getStartScreen() {
        return new MainMenuScreen(this);
    }


}
