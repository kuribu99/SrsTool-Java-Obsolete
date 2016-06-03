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
import kongmy.srs.core.RequirementModule;
import kongmy.srs.modules.ui.AccessControlDialog;

/**
 *
 * @author Kong My
 */
public class AccessControlModule extends RequirementModule implements HasMenu {

    public AccessControlModule() {
        super();
    }

    @Override
    protected void UpdateGeneratedRequirements() {

    }

    @Override
    public List<String> getDependencies() {
        List<String> dependencies = new ArrayList<String>();
        dependencies.add(OntologyModule.class.getName());
        return dependencies;
    }

    @Override
    public Component getMenu(JFrame parent) {
        JMenuItem menuItem = new JMenuItem("Configure Access Control");
        menuItem.addActionListener((e) -> {
            AccessControlDialog dlg = new AccessControlDialog(parent, true, this);
            dlg.setVisible(true);
        });
        return menuItem;
    }

}
