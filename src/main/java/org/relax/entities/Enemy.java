package org.relax.entities;

import org.relax.Sprite;

import java.awt.*;

public class Enemy extends Sprite {

    public Enemy(int x_rdr, int y_rdr) {
        super(x_rdr, y_rdr);
    }

    private double speed = 4000;

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fillRect(getRenderX() ,getRenderY(), 100, 100);
    }

    public void update(double elapsedTime) {
        double deltaX =  speed;
        x += deltaX;
        if (x < 0) {
            x = 0;
            speed *= -1;
        } else if (x > 500 * 1000) {
            x = 500 * 1000;
            speed *= -1;
        }
    }

}
