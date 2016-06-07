/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kongmy.srs;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import com.kongmy.core.Application;
import com.kongmy.srs.core.Requirement;
import com.kongmy.srs.ui.MainFrame;

/**
 *
 * @author Kong My
 */
public class SrsApplication extends Application {

    protected final List<Requirement> generatedRequirements;

    public SrsApplication() {
        super(SrsConfiguration.ReadOrDefaultConfiguration());
        generatedRequirements = new ArrayList<>();
    }

    public List<Requirement> getGeneratedRequirements() {
        return this.generatedRequirements;
    }

    public static Application bootstrap() {
        instance = new SrsApplication();
        instance.LoadModules();
        return instance;
    }

    @Override
    public void run() {
        // Set UI Look and Feel
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        // Show warning if modules not found
        if (modulesNotFound.size() > 0) {
            StringBuilder msg = new StringBuilder();
            msg.append("The following modules are unable to be loaded:\n");
            modulesNotFound.forEach((moduleName) -> {
                msg.append(" - ").append(moduleName).append("\n");
            });
            msg.delete(msg.length() - 1, msg.length());
            JOptionPane.showMessageDialog(null, msg.toString(), "Warning", JOptionPane.WARNING_MESSAGE);
        }

        // Show main UI
        (new MainFrame()).setVisible(true);
    }
}
