package org.relax;

import java.awt.*;

public abstract class Sprite {

    protected Game game;
    protected double x;
    protected double y;

    public Sprite(Game game, int x, int y) {
        this.game = game;
        this.x = x;
        this.y = y;
    }

    public abstract void render(Graphics2D g2d);
    public abstract void update(double elapsedTime_ns);

}
