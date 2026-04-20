package org.relax;

import javax.swing.*;

public class Frame extends JFrame {

    public Frame() {
        super("Relax - Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void centerAndShow() {
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

}
