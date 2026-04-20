package org.relax;

import java.awt.*;

public class Game {

    private boolean isRunning = false;

    private Sprite player1;
    private Sprite player2;

    public Game() {
        player1 = new Sprite(50, 50);
        player2 = new Sprite(50, 250);
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        player1.render(g2d);
        player2.render(g2d);
    }

    public void update(long elapsedTime) {
        player1.update(elapsedTime);
        player2.update(elapsedTime);
    }

    public void rightKeyDown() {
        player1.RIGHT = true;
    }

    public void rightKeyUp() {
        player1.RIGHT = false;
    }

    public void leftKeyDown() {
        player1.LEFT = true;
    }

    public void leftKeyUp() {
        player1.LEFT = false;
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
