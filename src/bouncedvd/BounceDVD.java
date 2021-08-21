package bouncedvd;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class BounceDVD {

    public static final void addKeyBinding(JComponent c, String key, final Action action) {
        c.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key), key);
        c.getActionMap().put(key, action);
        c.setFocusable(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Panel panel = new Panel();

        frame.setTitle("Bouncing DVD logo simulator");
        frame.setPreferredSize(new Dimension(600, 600));
        frame.setLocation(10, 10);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("images/icon.png"));

        addKeyBinding(frame.getRootPane(), "F11", new FullscreenToggleAction(frame));
    }

}