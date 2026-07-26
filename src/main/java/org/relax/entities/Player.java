package org.relax.entities;

import org.relax.Sprite;

import java.awt.*;

public class Player extends Sprite {

    private double velocity = 400;

    private double circleRadius;
    private double angle = 0.0, angularVelocity; // prędkość kątowa - omega
    private double circleX, circleY;

    public boolean LEFT, RIGHT, UP, DOWN;

    public Player(int x, int y, int circleRadius, double fullCircleDuration_s) {
        super(x, y);

        this.circleRadius = toSimCoordinate(circleRadius);
        this.angularVelocity = 2 * Math.PI / fullCircleDuration_s;
    }


    @Override
    public void render(Graphics2D g2d) {
        int Center_Circle_Radius = 10;
        g2d.setColor(Color.WHITE);
        renderCircle(g2d, x,y, Center_Circle_Radius);

        int ROTATING_CIRCLE_RADIUS = 5;
        renderCircle(g2d, circleX, circleY, ROTATING_CIRCLE_RADIUS);
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

        double elapsedTime_ms = elapsedTime_ns / 1_000_000;
        angle = angle + angularVelocity * elapsedTime_ms; /** initial angel - + phi0**/;
        circleX = x + circleRadius * Math.cos(angle);
        circleY = y + circleRadius * Math.sin(angle);
    }


}
