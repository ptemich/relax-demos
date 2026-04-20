package org.relax;

import java.awt.*;

public class Sprite {

    private double tick = 0, x = 200, y = 50, speed = 5;
    //private final BufferedImage image;

    public boolean LEFT, RIGHT, UP, DOWN;

    public Sprite(int x, int y/*BufferedImage image*/) {
        this.x = x;
        this.y = y;
    }

    public void render(Graphics2D g2d) {
        //g2d.drawImage(this.image, this.x, this.y, null);
        g2d.fillRect((int) x,(int) y, 100, 100);
    }

    public void update(double elapsedTime) {
        tick += elapsedTime/1000;
        x = 200 + Math.sin(tick) * 200;
//        if (LEFT) {
//            this.x -= this.speed;
//        }
//        if (RIGHT) {
//            this.x += this.speed;
//        }
//        if (UP) {
//            this.y -= this.speed;
//        }
//        if (DOWN) {
//            this.y += this.speed;
//        }
    }
}
