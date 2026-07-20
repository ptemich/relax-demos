package org.relax;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class VectorDrawer extends JFrame {

    private final DrawingPanel panel;
    private final List<Vector> vectors = new ArrayList<>();

    public VectorDrawer() {
        setTitle("Rysowanie Wektorów - Java Swing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        panel = new DrawingPanel();
        add(panel, BorderLayout.CENTER);

        // Panel sterowania
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField tfX = new JTextField("3", 5);
        JTextField tfY = new JTextField("2", 5);
        JButton btnAdd = new JButton("Dodaj wektor");

        gbc.gridx = 0; gbc.gridy = 0;
        controlPanel.add(new JLabel("X:"), gbc);
        gbc.gridx = 1;
        controlPanel.add(tfX, gbc);

        gbc.gridx = 2;
        controlPanel.add(new JLabel("Y:"), gbc);
        gbc.gridx = 3;
        controlPanel.add(tfY, gbc);

        gbc.gridx = 4;
        controlPanel.add(btnAdd, gbc);

        add(controlPanel, BorderLayout.SOUTH);

        // Akcja dodawania wektora
        btnAdd.addActionListener(e -> {
            try {
                double x = Double.parseDouble(tfX.getText());
                double y = Double.parseDouble(tfY.getText());
                vectors.add(new Vector(x, y, getRandomColor()));
                panel.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Podaj poprawne liczby!", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Przykładowe wektory
        vectors.add(new Vector(4, 2, Color.RED));
        vectors.add(new Vector(-3, 4, Color.BLUE));
        vectors.add(new Vector(2, -3, Color.GREEN));
    }

    private Color getRandomColor() {
        return new Color((int)(Math.random() * 200) + 55,
                (int)(Math.random() * 200) + 55,
                (int)(Math.random() * 200) + 55);
    }

    // Klasa reprezentująca wektor
    static class Vector {
        double x, y;
        Color color;

        Vector(double x, double y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }

    // Panel rysujący
    class DrawingPanel extends JPanel {
        private final int ORIGIN_X = 400;
        private final int ORIGIN_Y = 300;
        private final double SCALE = 40; // pikseli na jednostkę

        public DrawingPanel() {
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Rysowanie siatki i osi
            drawGrid(g2d);
            drawAxes(g2d);

            // Rysowanie wektorów
            for (Vector v : vectors) {
                drawVector(g2d, v);
            }

            // Informacje
            g2d.setColor(Color.BLACK);
            g2d.drawString("Kliknij 'Dodaj wektor' aby dodać nowy", 20, 30);
        }

        private void drawGrid(Graphics2D g2d) {
            g2d.setColor(new Color(240, 240, 240));
            for (int x = -10; x <= 10; x++) {
                int px = ORIGIN_X + (int)(x * SCALE);
                g2d.drawLine(px, 0, px, getHeight());
            }
            for (int y = -10; y <= 10; y++) {
                int py = ORIGIN_Y + (int)(y * SCALE);
                g2d.drawLine(0, py, getWidth(), py);
            }
        }

        private void drawAxes(Graphics2D g2d) {
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));

            // Oś X
            g2d.drawLine(50, ORIGIN_Y, getWidth() - 50, ORIGIN_Y);
            // Oś Y
            g2d.drawLine(ORIGIN_X, 50, ORIGIN_X, getHeight() - 50);

            // Strzałki na osiach
            drawArrowHead(g2d, getWidth() - 50, ORIGIN_Y, 0);     // strzałka X
            drawArrowHead(g2d, ORIGIN_X, 50, Math.PI / 2);       // strzałka Y (w górę)

            g2d.drawString("X", getWidth() - 40, ORIGIN_Y + 20);
            g2d.drawString("Y", ORIGIN_X + 15, 40);
        }

        private void drawVector(Graphics2D g2d, Vector v) {
            g2d.setColor(v.color);
            g2d.setStroke(new BasicStroke(3));

            int startX = ORIGIN_X;
            int startY = ORIGIN_Y;
            int endX = ORIGIN_X + (int)(v.x * SCALE);
            int endY = ORIGIN_Y - (int)(v.y * SCALE); // minus bo y rośnie w dół

            // Linia wektora
            g2d.drawLine(startX, startY, endX, endY);

            // Strzałka na końcu
            drawArrowHead(g2d, endX, endY, Math.atan2(-v.y, v.x));

            // Etykieta
            g2d.setColor(Color.BLACK);
            String label = String.format("(%.1f, %.1f)", v.x, v.y);
            g2d.drawString(label, endX + 10, endY - 10);
        }

        private void drawArrowHead(Graphics2D g2d, int x, int y, double angle) {
            int arrowSize = 12;
            AffineTransform tx = g2d.getTransform();
            g2d.translate(x, y);
            g2d.rotate(angle);

            int[] xPoints = {0, -arrowSize, -arrowSize};
            int[] yPoints = {0, -arrowSize/2, arrowSize/2};
            g2d.fillPolygon(xPoints, yPoints, 3);

            g2d.setTransform(tx);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VectorDrawer().setVisible(true);
        });
    }
}
