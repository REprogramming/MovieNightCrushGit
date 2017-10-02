package com.filip.movienightcrush;

/**
 * Created by RE1010 on 2017-10-02.
 */

import android.widget.Button;

import com.filip.androidgames.framework.Game;
import com.filip.androidgames.framework.Graphics;
import com.filip.androidgames.framework.Input.TouchEvent;
import com.filip.androidgames.framework.Pixmap;
import com.filip.androidgames.framework.Screen;
import java.util.List;

public class MainMenuScreen extends Screen {

    private static Pixmap background;
    private static Pixmap playButton;
    private static Pixmap instructionsButton;
    private static Pixmap settingsButton;

    private int playXPos;
    private int playYPos;

    public MainMenuScreen(Game game){
        super(game);
        Graphics g = game.getGraphics();
        background = g.newPixmap("background.png", Graphics.PixmapFormat.RGB565);
        playButton = g.newPixmap("playButton.png", Graphics.PixmapFormat.ARGB4444);

        playXPos = g.getWidth() / 2 - playButton.getWidth() / 2;
        playYPos = g.getHeight() / 2 - playButton.getHeight() / 2;
    }

    @Override
    public void update(float deltaTime){
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for(int i = 0; i < len; i++){
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP){
                if (inBounds(event, playXPos, playYPos, playButton.getWidth(), playButton.getHeight())){
                    game.setScreen(new GameScreen(game));
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime){
        Graphics g = game.getGraphics();
        g.drawPixmap(background, 0, 0);
        g.drawPixmap(playButton, playXPos, playYPos);
    }

    @Override
    public void pause(){}
    @Override
    public void resume(){}
    @Override
    public void dispose(){}
}
