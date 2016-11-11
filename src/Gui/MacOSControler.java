package Gui;

import com.apple.eawt.*;

import javax.swing.*;

/**
 * Created by Yuri on 08.11.16.
 */
public class MacOSControler implements AboutHandler, QuitHandler, PreferencesHandler {
    @Override
    public void handleAbout(AppEvent.AboutEvent aboutEvent) {
        JOptionPane.showMessageDialog(null, "About", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void handlePreferences(AppEvent.PreferencesEvent preferencesEvent) {

    }

    @Override
    public void handleQuitRequestWith(AppEvent.QuitEvent quitEvent, QuitResponse quitResponse) {

    }
}
