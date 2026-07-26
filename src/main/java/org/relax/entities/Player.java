package org.relax.entities;

import org.relax.Sprite;

import java.awt.*;

public class Player extends Sprite {

    private double velocity = 400;

    private double circleRadius;
    private double angle = 0.0, angularVelocity; // prędkość kątowa - omega
    private double circleX, circleY;

    private double targetX, targetY;

    public boolean LEFT, RIGHT, UP, DOWN;

    public Player(int x_rdr, int y_rdr, int circleRadius_rdr, double fullCircleDuration_s) {
        super(x_rdr, y_rdr);

        this.targetX = x;
        this.targetY = y;
        this.circleRadius = toSimCoordinate(circleRadius_rdr);
        this.angularVelocity = 2 * Math.PI / fullCircleDuration_s;
    }


    @Override
    public void render(Graphics2D g2d) {
        int Center_Circle_Radius = 10;
        g2d.setColor(Color.WHITE);
        renderCircle(g2d, x,y, Center_Circle_Radius);

        int ROTATING_CIRCLE_RADIUS = 5;
        renderCircle(g2d, circleX, circleY, ROTATING_CIRCLE_RADIUS);

        g2d.drawString(targetX + " " + targetY, 20, 20);
    }

    private void renderCircle(Graphics2D g2d, double x, double y, int radius) {
        g2d.fillOval(toRenderCoordinate(x) - radius, toRenderCoordinate(y) - radius, radius, radius);
    }

    public void update(double elapsedTime_ns) {
        double deltaX = velocity;
        if (LEFT) {
            x -= deltaX;
        }
        if (RIGHT) {
            x += deltaX;
        }
        if (UP) {
            this.y -= velocity;
        }
        if (DOWN) {
            this.y += velocity;
        }

        int TARGET_POSITION_THRESHOLD = 10;

        double targetDistanceX = targetX - x;
        if (Math.abs(targetDistanceX) > TARGET_POSITION_THRESHOLD) {
            if (targetDistanceX > 0) {
                x += velocity;
            } else {
                x -= velocity;
            }
        }

        double targetDistanceY = targetY - y;
        if (Math.abs(targetDistanceY) > TARGET_POSITION_THRESHOLD) {
            if (targetDistanceY > 0) {
                y += velocity;
            } else {
                y -= velocity;
            }
        }

        double elapsedTime_ms = elapsedTime_ns / 1_000_000;
        angle = angle + angularVelocity * elapsedTime_ms; /** initial angel - + phi0**/;
        circleX = x + circleRadius * Math.cos(angle);
        circleY = y + circleRadius * Math.sin(angle);
    }

    public void setTarget(int x_rdr, int y_rdr) {
        targetX = toSimCoordinate(x_rdr);
        targetY = toSimCoordinate(y_rdr);
    }

}
