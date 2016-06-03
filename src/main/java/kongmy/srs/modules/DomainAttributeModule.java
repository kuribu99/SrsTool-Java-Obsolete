/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kongmy.srs.modules;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import kongmy.core.HasMenu;
import kongmy.core.Module;
import kongmy.srs.modules.ui.DomainAttributeDialog;

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
            DomainAttributeDialog dlg = new DomainAttributeDialog(parent, true, this);
            dlg.setVisible(true);
        });
        return menuItem;
    }

}
