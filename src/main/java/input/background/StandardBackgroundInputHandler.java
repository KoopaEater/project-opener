package input.background;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

public class StandardBackgroundInputHandler implements BackgroundInputHandler {

    BackgroundCommand openCommand;
    BackgroundCommand closeCommand;

    public StandardBackgroundInputHandler(BackgroundCommand onOpenCommand, BackgroundCommand onCloseCommand) {
        this.openCommand = onOpenCommand;
        this.closeCommand = onCloseCommand;
    }

    private boolean isCtrlShiftPressed(NativeKeyEvent e) {
        int modifier = e.getModifiers();
        return modifier == (NativeKeyEvent.CTRL_L_MASK | NativeKeyEvent.SHIFT_L_MASK);
    }

    public void nativeKeyReleased(NativeKeyEvent e) {

        if (e.getKeyCode() == NativeKeyEvent.VC_P && isCtrlShiftPressed(e)) {
            openCommand.trigger();
        }

        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            closeCommand.trigger();
        }

    }
}
