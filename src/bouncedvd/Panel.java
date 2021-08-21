package bouncedvd;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements ActionListener {

    private Timer timer;
    private Random random = new Random();

    private boolean runningState = false;
    private BufferedImage image;
    private int x;
    private int y;
    private int xSpeed = Math.random() > 0.5 ? 4 : -4;
    private int ySpeed = Math.random() > 0.5 ? 4 : -4;

    private int cornerTimes;

    public Panel() {
        setBackground(Color.BLACK);
        start();
    }

    private void start() {
        loadImage(random.nextInt(25));
        x = random.nextInt(getPanelWidth() - image.getWidth());
        y = random.nextInt(getPanelHeight() - image.getHeight());
        timer = new Timer(5, this);
        timer.start();
        runningState = true;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        if (runningState) {
            g.drawImage(image, x, y, this);

            if (cornerTimes > 0) {
                g.setColor(new Color(255, 255, 255, 50));
                g.drawString("Courner: " + cornerTimes + "x", 10, 20);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (runningState) {
            if ((x + image.getWidth()) > getPanelWidth()) {
                xSpeed *= -1;
                x = getPanelWidth() - image.getWidth();
                touch();
            } else if (x < 0) {
                xSpeed *= -1;
                x = 0;
                touch();
            }

            if ((y + image.getHeight()) > getPanelHeight()) {
                ySpeed *= -1;
                y = getPanelHeight() - image.getHeight();
                touch();
            } else if (y < 0) {
                ySpeed *= -1;
                y = 0;
                touch();
            }

            x += xSpeed;
            y += ySpeed;
            repaint();
        }
    }

    private void touch() {
        if (runningState) {
            loadImage(random.nextInt(25));
            if ((x == 0 && y == 0) || (x == getPanelWidth() - image.getWidth() && y == 0) || (x == 0 && y == getPanelWidth() - image.getHeight()) || (x == getPanelWidth() - image.getWidth() && y == getPanelHeight() - image.getHeight())) {
                cornerTimes++;
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    private void loadImage(int imageID) {
        try {
            image = ImageIO.read(new File("images/dvd" + imageID + ".png"));
        } catch (IOException ex) {
            System.out.println("Error: Picture cannot load: " + "dvd" + imageID + ".png");
            System.exit(0);
        }
    }

    private int getPanelWidth() {
        int result = this.getWidth();
        if (result == 0) {
            result = 600;
        }
        return result;
    }

    private int getPanelHeight() {
        int result = this.getHeight();
        if (result == 0) {
            result = 600;
        }
        return result;
    }
}