import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ControlHandler extends Timer implements KeyListener {
    ArrayList<Control> controls, currentControls;

    public ControlHandler(int tickLength) {
        super("ControlHandler", true);
        controls = new ArrayList<>();
        currentControls = new ArrayList<>();
        this.scheduleAtFixedRate(new ControlExecutor(), 0, tickLength);
    }

    public void addControl(Control control) {
        controls.add(control);
    }

    public ArrayList<Control> getControls() {
        return controls;
    }

    public void stop() {
        this.cancel();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (Control control : controls) {
            if (e.getKeyCode() == control.getEvent().getKeyCode() && e.getModifiersEx() == control.getEvent().getModifiersEx()) {
                if (!currentControls.contains(control)) {
                    currentControls.add(control);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (Control control : controls) {
            if (e.getKeyCode() == control.getEvent().getKeyCode() && e.getModifiersEx() == control.getEvent().getModifiersEx()) {
                currentControls.remove(control);
            }
        }
    }

    protected class ControlExecutor extends TimerTask {
        @Override
        public void run() {
            @SuppressWarnings("unchecked")
            ArrayList<Control> controls = (ArrayList<Control>) currentControls.clone();
            for (Control control : controls) {
                control.function();
            }
        }
    }
}
