import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public abstract class Control {
    private KeyEvent event;

    abstract public void function();

    public KeyEvent getEvent() {
        return event;
    }

    public void setEvent(KeyEvent event) {
        this.event = event;
    }

    final static class Factory {
        private static final Component comp = new JFrame();
        private final int keyCode;
        private int modifiers = 0;

        Factory(int keyCode) {
            this.keyCode = keyCode;
        }

        public KeyEvent getKeyEvent() {
            return new KeyEvent(comp, KeyEvent.KEY_PRESSED, 0, modifiers, keyCode, (char) keyCode);
        }

        public Factory setShift() {
            modifiers ^= InputEvent.SHIFT_DOWN_MASK;
            return this;
        }

        public Factory setControl() {
            modifiers ^= InputEvent.CTRL_DOWN_MASK;
            return this;
        }

        public Factory setAlt() {
            modifiers ^= InputEvent.ALT_DOWN_MASK;
            return this;
        }
    }
}
