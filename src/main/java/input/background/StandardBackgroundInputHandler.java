package input.background;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

public class StandardBackgroundInputHandler implements BackgroundInputHandler {

    OpenCommandListener openCommand;

    public StandardBackgroundInputHandler(OpenCommandListener onOpenCommand) {
        this.openCommand = onOpenCommand;
    }

    private boolean isCtrlShiftPressed(NativeKeyEvent e) {
        int modifier = e.getModifiers();
        return modifier == (NativeKeyEvent.CTRL_L_MASK | NativeKeyEvent.SHIFT_L_MASK);
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_P && isCtrlShiftPressed(e)) {
            openCommand.onOpenCommand();
        }
    }
}
