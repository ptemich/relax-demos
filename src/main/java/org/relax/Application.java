package org.relax;

import javax.swing.SwingUtilities;

public class Application {

    // how many frames should be drawn in a second
    final int FRAMES_PER_SECOND = 30;
    // calculate how many nano seconds each frame should take for our target frames per second.
    final long TIME_BETWEEN_UPDATES = 1_000_000_000 / FRAMES_PER_SECOND;
    // if you're worried about visual hitches more than perfect timing, set this to 1. else 5 should be okay
    final int MAX_UPDATES_BETWEEN_RENDER = 1;

    private Frame frame;
    private Panel panel;
    private Game game;
    private Keyboard keyboard;
    private Thread gameLoop;

    public Application() {
        createAndShowUI();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Application::new);
    }

    /**
     * Here we will create our swing UI as well as initialise and setup our
     * sprites, scene, and game loop and other buttons etc
     */
    private void createAndShowUI() {
        game = new Game();
        frame = new Frame();
        panel = new Panel(game);
        keyboard = new Keyboard(game, panel);

        this.setupGameLoop();

        frame.add(panel);
        frame.centerAndShow();

        // after setting the frame visible we start the game loop, this could be done in a button or wherever you want
        game.setRunning(true);
        this.gameLoop.start();
    }

    /**
     * This method would actually create and setup the game loop The game loop
     * will always be encapsulated in a Thread, Timer or some sort of construct
     * that generates a separate thread in order to not block the UI
     */
    private void setupGameLoop() {
        // initialise the thread
        gameLoop = new Thread(() -> {
            // track number of frames
            int frameCount;

            // we will need the last update time.
            long lastUpdateTime = System.nanoTime();


            // store the time we started this will be used for updating map and charcter animations
            //long currTime = System.currentTimeMillis();

            long now;
            long elapsedTime;
            long prevLoopTime = System.nanoTime();
            long lastRendered = 0;

            while (game.isRunning()) {
                now = System.nanoTime();
                elapsedTime = now - prevLoopTime;
                prevLoopTime = now;
                // currTime += elapsedTime;
                lastRendered += elapsedTime;

                if (lastRendered > TIME_BETWEEN_UPDATES) {
                    game.update(elapsedTime); //Update the entity movements and collision checks etc (all has to do with updating the games status i.e  call move() on Enitites)
                    panel.repaint(); // draw call for rendering sprites etc
                    lastRendered = 0;
                }

                //int updateCount = 0;
                // do as many game updates as we need to, potentially playing catchup.
                //while (now - lastUpdateTime >= TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BETWEEN_RENDER) {
                //    lastUpdateTime += TIME_BETWEEN_UPDATES;
                //    updateCount++;
                //}

                // if for some reason an update takes forever, we don't want to do an insane number of catchups.
                // if you were doing some sort of game that needed to keep EXACT time, you would get rid of this.
//                if (now - lastUpdateTime >= TIME_BETWEEN_UPDATES) {
//                    lastUpdateTime = now - TIME_BETWEEN_UPDATES;
//                }


                //long lastRenderTime = now;

                // Yield until it has been at least the target time between renders. This saves the CPU from hogging.
                //while (now - lastRenderTime < TIME_BETWEEN_UPDATES && now - lastUpdateTime < TIME_BETWEEN_UPDATES) {
                   //Thread.yield();
//                    try {
//                        Thread.sleep(1);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                        //Thread.currentThread().interrupt();
//                    }
                    //now = System.nanoTime();
                //}
            }
        });
    }
}