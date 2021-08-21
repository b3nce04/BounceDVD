package bouncedvd;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;

class FullscreenToggleAction extends AbstractAction {

    private JFrame frame;

    public FullscreenToggleAction(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int state = frame.getExtendedState();
        boolean undecorated = frame.isUndecorated();

        frame.dispose();

        frame.setUndecorated(!undecorated);
        frame.setExtendedState(state == JFrame.NORMAL ? JFrame.MAXIMIZED_BOTH : JFrame.NORMAL);

        frame.setVisible(true);
        frame.repaint();

        if (state == JFrame.MAXIMIZED_BOTH) {
            frame.setSize(frame.getPreferredSize());
            frame.setLocation(10, 10);
        }
    }
}