package org.relax;

import java.awt.*;

public abstract class Sprite {

    protected static final int SIM_TO_RENDER_MULTIPLAYER = 1000;

    protected double x, y;

    public Sprite(int x, int y) {
        this.x = x * SIM_TO_RENDER_MULTIPLAYER;
        this.y = y * SIM_TO_RENDER_MULTIPLAYER;
    }

    protected int getRenderX() {
        return (int) x / SIM_TO_RENDER_MULTIPLAYER;
    }

    protected int getRenderY() {
        return (int) y / SIM_TO_RENDER_MULTIPLAYER;
    }

    public abstract void render(Graphics2D g2d);
    public abstract void update(double elapsedTime);
}
