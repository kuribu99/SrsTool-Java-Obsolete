/*
 */
package com.kongmy.srs.modules;

import com.kongmy.core.Application;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import com.kongmy.core.HasMenu;
import com.kongmy.srs.core.RequirementModule;
import com.kongmy.srs.modules.ui.ActionResourceContraintDialog;

/**
 *
 * @author Kong My
 */
public class ActionResourceConstraintModule extends RequirementModule implements HasMenu {

    @Override
    public List<String> getDependencies() {
        List<String> dependencies = new ArrayList<>();
        dependencies.add(OntologyModule.class.getName());
        return dependencies;
    }

    @Override
    public Component getMenu(JFrame parent) {
        JMenuItem menuItem = new JMenuItem("Configure Action Resource Constraints");
        menuItem.addActionListener((e) -> {
            OntologyModule module = (OntologyModule) Application.getInstance().getModule(OntologyModule.class.getName());
            ActionResourceContraintDialog dlg = new ActionResourceContraintDialog(parent, true, module);
            dlg.setVisible(true);
        });
        return menuItem;
    }

    @Override
    protected void UpdateGeneratedRequirements() {
    }

}
