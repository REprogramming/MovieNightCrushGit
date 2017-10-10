package com.filip.movienightcrush;

import com.filip.androidgames.framework.Game;
import com.filip.androidgames.framework.Graphics;
import com.filip.androidgames.framework.Input;
import com.filip.androidgames.framework.Pixmap;
import com.filip.androidgames.framework.Screen;

import java.util.List;

/**
 * Created by Emily on 10/4/2017.
 */

public class OptionsScreen extends Screen {
    private static Pixmap background;
    public static Pixmap quitButton;
    public static Pixmap mainMenuButton;

    private int quitXPos;
    private int quitYPos;
    private int mainMenuXPos;
    private int mainMenuYPos;

    public OptionsScreen(Game game){
        super(game);
        Graphics g = game.getGraphics();
        background = g.newPixmap("optionsBackground.png", Graphics.PixmapFormat.RGB565);
        quitButton = g.newPixmap("backButton.png", Graphics.PixmapFormat.RGB565);
        mainMenuButton = g.newPixmap("mainMenuButton.png", Graphics.PixmapFormat.RGB565);

        quitXPos = g.getWidth() / 2 - quitButton.getWidth() / 2;
        quitYPos = g.getHeight() - quitButton.getHeight() - 100;
        mainMenuXPos = g.getWidth() / 2 - mainMenuButton.getWidth() / 2;
        mainMenuYPos = quitYPos - 100;
    }

    @Override
    public void update(float deltaTime){
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_UP){
                if (inBounds(event, quitXPos, quitYPos, quitButton.getWidth(), quitButton.getHeight())){
                    game.setScreen(new GameScreen(game));
                    return;
                }
                if (inBounds(event, mainMenuXPos, mainMenuYPos, mainMenuButton.getWidth(), mainMenuButton.getHeight())){
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime){
        Graphics g = game.getGraphics();
        g.drawPixmap(background, 0, 0);
        g.drawPixmap(quitButton, quitXPos, quitYPos);
        g.drawPixmap(mainMenuButton, mainMenuXPos, mainMenuYPos);
    }

    @Override
    public void pause(){}

    @Override
    public void resume(){}

    @Override
    public void dispose(){}
}
