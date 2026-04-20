package org.relax;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    private int WIDTH = 600;
    private int HEIGHT = 600;

    private Game game;

    public Panel(Game game) {
        // we are using a game loop to repaint, so probably don't want swing randomly doing it for us
        this.setIgnoreRepaint(true);
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

    @Override
    public Dimension getPreferredSize() {
        // because no components are added to the JPanel, we will have a default sizxe of 0,0 so we instead force the JPanel to a size we want
        return new Dimension(WIDTH, HEIGHT);
    }

}
