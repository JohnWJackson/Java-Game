package com.mictl.game;

import com.mictl.game.graphics.Screen;
import com.mictl.game.input.Keyboard;
import com.mictl.game.level.Level;
import com.mictl.game.level.RandomLevel;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable {

    public static int width = 300;
    public static int height = width / 16 * 9;  //16:9 resolution
    public static int scale = 3;
    public static String title = "Game";  //Title of game

    private Thread thread;
    private JFrame frame;
    private Keyboard key;
    private Level level;
    private boolean running = false;

    Screen screen;


    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        screen = new Screen(width, height);
        frame = new JFrame();
        key = new Keyboard();
        level = new RandomLevel(screen.MAP_SIZE, screen.MAP_SIZE);

        frame.addKeyListener(key);
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime(); //time in nano seconds
        long timer = System.currentTimeMillis();
        final double ns = 1_000_000_000.0 / 60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frame.setTitle(title + "  |  " + updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }

        stop();
    }

    int x=0, y=0;
    public void update() {
        frame.requestFocusInWindow();

        key.update();
        if (key.up)
            y--;
        if (key.down)
            y++;
        if (key.right)
            x++;
        if (key.left)
            x--;
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();
        level.render(x, y, screen);

/*        pixels = Arrays.copyOf(screen.pixels, pixels.length);*/
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);
        game.frame.setFocusable(true);
        game.frame.requestFocusInWindow();

        game.start();
    }

}
