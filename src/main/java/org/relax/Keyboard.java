package org.relax;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Keyboard {

    public Keyboard(Game game, Panel panel) {
        registerKeyActions(panel, KeyEvent.VK_LEFT, game::leftKeyDown, game::leftKeyUp);
        registerKeyActions(panel, KeyEvent.VK_RIGHT, game::rightKeyDown, game::rightKeyUp);
        registerKeyActions(panel, KeyEvent.VK_RIGHT, () -> {}, game::escKeyUp);
    }

    private void registerKeyActions(Panel panel, int keyCode, Runnable pressed, Runnable released) {
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyCode, 0, false), keyCode + "_pressed");
        panel.getActionMap().put(keyCode + "_pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressed.run();
            }
        });
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyCode, 0, true), keyCode + "_released");
        panel.getActionMap().put(keyCode + "_released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                released.run();
            }
        });
    }

}
