package com.filip.movienightcrush;

import com.filip.androidgames.framework.Game;
import com.filip.androidgames.framework.Graphics;
import com.filip.androidgames.framework.Input.TouchEvent;
import com.filip.androidgames.framework.Pixmap;
import com.filip.androidgames.framework.Screen;

import java.util.List;

public class GameScreen extends Screen {
    private static Pixmap background;
    public static Pixmap optionsButton;
    public static Pixmap gameOverButton;

    private int optionXPos;
    private int optionYPos;
    private int gameOverXPos;
    private int gameOverYPos;

    public GameScreen(Game game){
        super(game);
        Graphics g = game.getGraphics();
        background = g.newPixmap("gameBackground.png", Graphics.PixmapFormat.RGB565);
        optionsButton = g.newPixmap("optionsButton.png", Graphics.PixmapFormat.RGB565);
        gameOverButton = g.newPixmap("gameOverButton.png", Graphics.PixmapFormat.RGB565);

        optionXPos = g.getWidth() / 2 - optionsButton.getWidth() / 2;
        optionYPos = g.getHeight() - optionsButton.getHeight();
        gameOverXPos = 0;
        gameOverYPos = optionYPos;
    }

    @Override
    public void update(float deltaTime){
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP){
                if (inBounds(event, optionXPos, optionYPos, optionsButton.getWidth(), optionsButton.getHeight())){
                    game.setScreen(new OptionsScreen(game));
                    return;
                }
                if (inBounds(event, gameOverXPos, gameOverYPos, gameOverButton.getWidth(), gameOverButton.getHeight())){
                    game.setScreen(new GameOverScreen(game));
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime){
        Graphics g = game.getGraphics();
        g.drawPixmap(background, 20, -10);
        g.drawPixmap(optionsButton, optionXPos, optionYPos);
        g.drawPixmap(gameOverButton, gameOverXPos, gameOverYPos);
    }

    @Override
    public void pause(){}

    @Override
    public void resume(){}

    @Override
    public void dispose(){}
}
