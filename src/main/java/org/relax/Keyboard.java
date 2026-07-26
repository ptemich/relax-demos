package org.relax;

import javax.swing.*;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    private Game game;

    public Keyboard(Game game, Frame frame) {
        this.game = game;
//        registerKeyActions(panel, KeyEvent.VK_LEFT, game::leftKeyDown, game::leftKeyUp);
//        registerKeyActions(panel, KeyEvent.VK_RIGHT, game::rightKeyDown, game::rightKeyUp);
//        registerKeyActions(panel, KeyEvent.VK_RIGHT, () -> {}, game::escKeyUp);

        frame.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_LEFT -> game.leftKeyPressed();
            case KeyEvent.VK_RIGHT -> game.rightKeyPressed();
            case KeyEvent.VK_UP -> game.upKeyPressed();
            case KeyEvent.VK_DOWN -> game.downKeyPressed();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_LEFT -> game.leftKeyReleased();
            case KeyEvent.VK_RIGHT -> game.rightKeyReleased();
            case KeyEvent.VK_UP -> game.upKeyReleased();
            case KeyEvent.VK_DOWN -> game.downKeyReleased();

            case KeyEvent.VK_ESCAPE -> game.escKeyUp();
        }
    }

//    private void registerKeyActions(Panel panel, int keyCode, Runnable pressed, Runnable released) {
//        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyCode, 0, false), keyCode + "_pressed");
//        panel.getActionMap().put(keyCode + "_pressed", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                pressed.run();
//            }
//        });
//        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyCode, 0, true), keyCode + "_released");
//        panel.getActionMap().put(keyCode + "_released", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                released.run();
//            }
//        });
//    }

}
