package org.relax.entities;

import org.relax.Game;
import org.relax.Sprite;

import java.awt.*;

public class Player extends Sprite {

    public boolean LEFT, RIGHT, UP, DOWN;

    private final int MASTER_CIRCLE_RADIUS = 20;
    private final int ROTATING_CIRCLE_RADIUS = 5;

    private final double VELOCITY = 0.4;

    private double satelliteCircleRadius;
    private double angle = 0.0;
    private double satelliteAngularVelocity; // prędkość kątowa - omega
    private double satelliteX, satelliteY;

    private double targetX, targetY;


    public Player(Game game, int x, int y, int satelliteCircleRadius, double fullCircleDuration_s) {
        super(game, x, y);

        this.targetX = x;
        this.targetY = y;
        this.satelliteCircleRadius = satelliteCircleRadius;
        this.satelliteAngularVelocity = 2 * Math.PI / fullCircleDuration_s;
    }


    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.drawString(targetX + " || " + targetY, 20, 20);

        // draw "master" circle
        g2d.fillOval((int) (x - MASTER_CIRCLE_RADIUS), (int) (y - MASTER_CIRCLE_RADIUS), (int) 2 * MASTER_CIRCLE_RADIUS, (int) 2 * MASTER_CIRCLE_RADIUS);

        // draw "satellite"
        g2d.fillOval((int) (satelliteX - ROTATING_CIRCLE_RADIUS), (int) (satelliteY - ROTATING_CIRCLE_RADIUS), (int) 2 * ROTATING_CIRCLE_RADIUS, (int) 2 * ROTATING_CIRCLE_RADIUS);
    }

    public void update(double elapsedTime_ns) {
        double elapsedTime_ms = elapsedTime_ns / 1_000_000;

        double deltaX = VELOCITY; // TODO use if we need to calculate distance as function of passed time
        double deltaY = VELOCITY; // TODO use if we need to calculate distance as function of passed time

        double TARGET_POSITION_THRESHOLD = VELOCITY; // this prevents from jumping back and forth

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

         // calculate satellite location
        angle = angle + satelliteAngularVelocity * elapsedTime_ms; /** initial angel - + phi0**/;

        /**
         * function getPosition(cx, cy, r, T, t, phi0 = 0) {
         *   const omega = (2 * Math.PI) / T; // prędkość kątowa
         *   const angle = omega * t + phi0;
         *   const x = cx + r * Math.cos(angle);
         *   const y = cy + r * Math.sin(angle);
         *   return { x, y };
         * }
         */

        // rotates on distance of two MASTER_CIRCLE_RADIUS distance
        satelliteX = (x) + 2 * MASTER_CIRCLE_RADIUS * Math.cos(angle);
        satelliteY = (y) + 2 * MASTER_CIRCLE_RADIUS * Math.sin(angle);
    }

    public void setTarget(int x, int y) {
        targetX = x;
        targetY = y;
    }

}
