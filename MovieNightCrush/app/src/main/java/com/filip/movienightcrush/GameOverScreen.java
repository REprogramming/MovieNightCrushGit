package com.filip.movienightcrush;

import com.filip.androidgames.framework.Game;
import com.filip.androidgames.framework.Graphics;
import com.filip.androidgames.framework.Input;
import com.filip.androidgames.framework.Pixmap;
import com.filip.androidgames.framework.Screen;

import java.util.List;

public class GameOverScreen extends Screen {
    private static Pixmap background;
    public static Pixmap playAgainButton;
    public static Pixmap mainMenuButton;

    private int playAgainXPos;
    private int playAgainYPos;
    private int mainMenuXPos;
    private int mainMenuYPos;

    public GameOverScreen(Game game){
        super(game);
        Graphics g = game.getGraphics();
        background = g.newPixmap("gameOverBackground.png", Graphics.PixmapFormat.RGB565);
        playAgainButton = g.newPixmap("playAgainButton.png", Graphics.PixmapFormat.RGB565);
        mainMenuButton = g.newPixmap("mainMenuButton.png", Graphics.PixmapFormat.RGB565);

        playAgainXPos = g.getWidth() / 2 - playAgainButton.getWidth() / 2;
        playAgainYPos = g.getHeight() / 2 - playAgainButton.getHeight() / 2;
        mainMenuXPos = g.getWidth() / 2 - mainMenuButton.getWidth() / 2;
        mainMenuYPos = playAgainYPos + 200;
    }

    @Override
    public void update(float deltaTime){
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_UP){
                if (inBounds(event, playAgainXPos, playAgainYPos, playAgainButton.getWidth(), playAgainButton.getHeight())){
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
        g.drawPixmap(playAgainButton, playAgainXPos, playAgainYPos);
        g.drawPixmap(mainMenuButton, mainMenuXPos, mainMenuYPos);
    }

    @Override
    public void pause(){}

    @Override
    public void resume(){}

    @Override
    public void dispose(){}
}
