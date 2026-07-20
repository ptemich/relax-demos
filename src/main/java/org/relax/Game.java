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

    public Game() {
        sprites.add(new Enemy(50, 50));
        player = new Player(450, 250);
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

    public void rightKeyDown() {
        player.RIGHT = true;
    }

    public void rightKeyUp() {
        player.RIGHT = false;
    }

    public void leftKeyDown() {
        player.LEFT = true;
    }

    public void leftKeyUp() {
        player.LEFT = false;
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
}
