package input.background;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

public class StandardBackgroundInputHandler implements BackgroundInputHandler {

    BackgroundCommand openCommand, closeCommand, onUpCommand, onDownCommand, onDeleteSearchCommand;
    SpecialBackgroundCommand onConfirmSearchCommand;

    public StandardBackgroundInputHandler(BackgroundCommand onOpenCommand, BackgroundCommand onCloseCommand, SpecialBackgroundCommand onConfirmSearchCommand, BackgroundCommand onUpCommand, BackgroundCommand onDownCommand) {
        this.openCommand = onOpenCommand;
        this.closeCommand = onCloseCommand;
        this.onConfirmSearchCommand = onConfirmSearchCommand;
        this.onUpCommand = onUpCommand;
        this.onDownCommand = onDownCommand;
    }

    private boolean isCtrlShiftPressed(NativeKeyEvent e) {
        int modifier = e.getModifiers();
        return modifier == (NativeKeyEvent.CTRL_L_MASK | NativeKeyEvent.SHIFT_L_MASK);
    }
    private boolean isCtrlPressed(NativeKeyEvent e) {
        int modifier = e.getModifiers();
        return modifier == (NativeKeyEvent.CTRL_L_MASK);
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            closeCommand.trigger();
        }
        if (e.getKeyCode() == NativeKeyEvent.VC_ENTER) {
            onConfirmSearchCommand.trigger(isCtrlPressed(e));
        }
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_P && isCtrlShiftPressed(e)) {
            openCommand.trigger();
        }
        if (e.getKeyCode() == NativeKeyEvent.VC_UP) {
            onUpCommand.trigger();
        }
        if (e.getKeyCode() == NativeKeyEvent.VC_DOWN) {
            onDownCommand.trigger();
        }
    }

}
