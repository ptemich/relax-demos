package org.relax.entities;

import org.relax.Sprite;

import java.awt.*;

public class Player extends Sprite {

    public Player(int x, int y) {
        super(x, y);
    }

    private double speed = 400;
    public boolean LEFT, RIGHT, UP, DOWN;

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillOval(getRenderX() - 10 ,getRenderY() - 10, 10, 10);
    }

    public void update(double elapsedTime) {
        double deltaX = speed ;
        if (LEFT) {
            x -= deltaX;
        }
        if (RIGHT) {
            x += deltaX;
        }
//        if (UP) {
//            this.y -= this.speed;
//        }
//        if (DOWN) {
//            this.y += this.speed;
//        }
    }

}
