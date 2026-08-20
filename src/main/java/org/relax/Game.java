package org.relax;

import org.relax.entities.Enemy;
import org.relax.entities.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private boolean isRunning = false;

    private Player player;
    private List<Sprite> sprites = new ArrayList<>();

    public final int windowWidth;
    public final int windowHeight;

    public Game(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        sprites.add(new Enemy(this, 50, 50, 100, 70));
        player = new Player(this, 50, 50, 60, 600);
        sprites.add(player);
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        sprites.forEach(sprite -> sprite.render(g2d));
        Toolkit.getDefaultToolkit().sync();
    }

    public void update(long elapsedTime) {
        sprites.forEach(sprite -> sprite.update(elapsedTime));
    }

    public void rightKeyPressed() {
        player.RIGHT = true;
    }

    public void rightKeyReleased() {
        player.RIGHT = false;
    }

    public void leftKeyPressed() {
        player.LEFT = true;
    }

    public void leftKeyReleased() {
        player.LEFT = false;
    }

    public void upKeyPressed() {
        player.UP = true;
    }

    public void upKeyReleased() {
        player.UP = false;
    }

    public void downKeyPressed() {
        player.DOWN = true;
    }

    public void downKeyReleased() {
        player.DOWN = false;
    }


    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void escKeyUp() {
        isRunning = false;
    }

    public void mouseClicked(int x, int y) {
        player.setTarget(x, y);
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }
}
