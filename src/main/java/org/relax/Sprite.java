package org.relax;

import java.awt.*;

public class Sprite {

    private double x, y;
    private double speed = 500;
    //private final BufferedImage image;

    public boolean LEFT, RIGHT, UP, DOWN;

    public Sprite(int x, int y/*BufferedImage image*/) {
        this.x = x * 1000;
        this.y = y * 1000;
    }

    public void render(Graphics2D g2d) {
        System.out.println("render " + x);
        //g2d.drawImage(this.image, this.x, this.y, null);
        g2d.fillRect((int) x / 1000 ,(int) y / 1000, 100, 100);
    }

    public void update(double elapsedTime) {
        double deltaX =  speed;
        System.out.println(elapsedTime + " " + deltaX + " " + x);
        x += deltaX;
        if (x < 0) {
            x = 0;
            speed *= -1;
        } else if (x > 400 * 1000) {
            x = 400 * 1000;
            speed *= -1;
        }
        //tick += elapsedTime/1000;
        //double sin = Math.sin(tick);
        //x = 200 + sin * 200;
        //System.out.println(tick + " " + sin + " " + x + " " + y);
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
