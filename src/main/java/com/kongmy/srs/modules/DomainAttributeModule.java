/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kongmy.srs.modules;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import com.kongmy.core.Application;
import com.kongmy.core.HasMenu;
import com.kongmy.core.Module;
import com.kongmy.srs.modules.ui.DomainAttributeDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Kong My
 */
public class DomainAttributeModule extends Module implements HasMenu {

    @Override
    public List<String> getDependencies() {
        List<String> dependencies = new ArrayList<>();
        dependencies.add(OntologyModule.class.getName());
        return dependencies;
    }

    @Override
    public Component getMenu(JFrame parent) {
        JMenuItem menuItem = new JMenuItem("Configure Domain Attributes");
        menuItem.addActionListener((e) -> {
            OntologyModule module = (OntologyModule) Application.getInstance().getModule(OntologyModule.class.getName());
            if (module.getAllDomains().isEmpty()) {
                JOptionPane.showMessageDialog(
                        parent,
                        "There is no domain to configure, please add at least 1 domain by modifying domains",
                        "No domain found",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                DomainAttributeDialog dlg = new DomainAttributeDialog(parent, true, module);
                dlg.setVisible(true);
            }
        });
        return menuItem;
    }

}
