package org.relax.entities;

import org.relax.Game;
import org.relax.Sprite;

import java.awt.*;

public class Player extends Sprite {

    public boolean LEFT, RIGHT, UP, DOWN;

    private final int CENTER_CIRCLE_RADIUS = 40;
    private final int ROTATING_CIRCLE_RADIUS = 5;

    private final double VELOCITY = 0.4;

    private double circleRadius;
    private double angle = 0.0, angularVelocity; // prędkość kątowa - omega
    private double circleX, circleY;

    private double targetX, targetY;


    public Player(Game game, int x, int y, int circleRadius, double fullCircleDuration_s) {
        super(game, x, y);

        this.targetX = x;
        this.targetY = y;
        this.circleRadius = circleRadius;
        this.angularVelocity = 2 * Math.PI / fullCircleDuration_s;
    }


    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.drawString(targetX + " " + targetY, 20, 20);

        renderCircle(g2d, x, y, CENTER_CIRCLE_RADIUS);
        renderCircle(g2d, circleX, circleY, ROTATING_CIRCLE_RADIUS);
    }

    private void renderCircle(Graphics2D g2d, double x, double y, double radius) {
        g2d.fillOval((int) (x - radius), (int) (y - radius), (int) radius, (int) radius);
    }

    public void update(double elapsedTime_ns) {
        double elapsedTime_ms = elapsedTime_ns / 1_000_000;

        double deltaX = VELOCITY; // TODO use if we need to calculate distance as function of passed time
        double deltaY = VELOCITY; // TODO use if we need to calculate distance as function of passed time

        double TARGET_POSITION_THRESHOLD = 0.01;

        double targetDistanceX = targetX - x;
        if (Math.abs(targetDistanceX) > TARGET_POSITION_THRESHOLD) {
            if (targetDistanceX > 0) {
                x += deltaX;
            } else {
                x -= deltaX;
            }
        } else {
            if (LEFT) {
                x -= deltaX;
            } else if (RIGHT) {
                x += deltaX;
            }
            targetX = x; // this is required to "auto adjust" after manual move
        }

        double targetDistanceY = targetY - y;
        if (Math.abs(targetDistanceY) > TARGET_POSITION_THRESHOLD) {
            if (targetDistanceY > 0) {
                y += deltaY;
            } else {
                y -= deltaY;
            }
        } else {
            if (UP) {
                y -= deltaY;
            } else if (DOWN) {
                y += deltaY;
            }
            targetY = y;
        }


         // calculate satelite location
        angle = angle + angularVelocity * elapsedTime_ms; /** initial angel - + phi0**/;
        circleX = x + circleRadius * Math.cos(angle);
        circleY = y + circleRadius * Math.sin(angle);
    }

    public void setTarget(int x, int y) {
        targetX = x;
        targetY = y;
    }

}
