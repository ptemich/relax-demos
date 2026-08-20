package org.relax;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    private Game game;

    public Panel(Game game) {
        // we are using a game loop to repaint, so probably don't want swing randomly doing it for us
        this.setIgnoreRepaint(true);
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.BLACK);
        g2d.fillRect(0,0,game.windowWidth, game.windowHeight);
        game.render(g);
    }

    @Override
    public Dimension getPreferredSize() {
        // because no components are added to the JPanel, we will have a default sizxe of 0,0 so we instead force the JPanel to a size we want
        return new Dimension(game.windowWidth, game.getWindowHeight());
    }

}
