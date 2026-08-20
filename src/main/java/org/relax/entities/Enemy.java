package org.relax.entities;

import org.relax.Game;
import org.relax.Sprite;

import java.awt.*;

public class Enemy extends Sprite {

    private double speed = 4;
    private double width;
    private double height;

    public Enemy(Game game, int x, int y, int width, int height) {
        super(game, x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fillRect((int) (x - width / 2), (int) (y - height / 2), (int) width, (int) height);
    }

    public void update(double elapsedTime) {
        double deltaX =  speed;
        x += deltaX;

        double rightBorder = x + width / 2;
        double leftBorder = x - width / 2;

        if (leftBorder < 0) {
            x = width / 2; // left border should stick to the left side of the screen
            speed *= -1;
        } else if (rightBorder > game.windowWidth) {
            x = game.windowWidth - width / 2; // right border should stick to the right side of the screen
            speed *= -1;
        }
    }

}
