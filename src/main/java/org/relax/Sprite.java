package org.relax;

import java.awt.*;

public abstract class Sprite {

    protected static final int SIM_TO_RENDER_SCALE = 1000;

    protected double x, y;

    public Sprite(int x_rdr, int y_rdr) {
        this.x = x_rdr * SIM_TO_RENDER_SCALE;
        this.y = y_rdr * SIM_TO_RENDER_SCALE;
    }

    protected int getRenderX() {
        return (int) x / SIM_TO_RENDER_SCALE;
    }

    protected int getRenderY() {
        return (int) y / SIM_TO_RENDER_SCALE;
    }

    protected static int toRenderCoordinate(double simeCoordinateValue) {
        return (int) simeCoordinateValue / SIM_TO_RENDER_SCALE;
    }

    protected static double toSimCoordinate(int renderCoordinateValue) {
        return renderCoordinateValue * SIM_TO_RENDER_SCALE;
    }

    public abstract void render(Graphics2D g2d);
    public abstract void update(double elapsedTime_ns);


}
