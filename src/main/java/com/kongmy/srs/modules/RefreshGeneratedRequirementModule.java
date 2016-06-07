/*
 */
package com.kongmy.srs.modules;

import com.kongmy.core.HasMenu;
import com.kongmy.core.Module;
import com.kongmy.srs.ui.MainFrame;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Kong My
 */
public class RefreshGeneratedRequirementModule extends Module implements HasMenu {

    @Override
    public Component getMenu(JFrame parent) {
        JMenuItem menuItem = new JMenuItem("Refresh Generated Requirements");
        menuItem.addActionListener((e) -> {
            MainFrame frame = (MainFrame) parent;
            frame.UpdateRequirements();

            JOptionPane.showMessageDialog(
                    parent,
                    "Requirements refreshed succesfully",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        return menuItem;
    }

}
