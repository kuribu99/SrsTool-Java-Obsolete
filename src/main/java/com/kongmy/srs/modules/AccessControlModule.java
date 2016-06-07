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
import com.kongmy.srs.core.Requirement;
import com.kongmy.srs.core.RequirementModule;
import com.kongmy.srs.modules.ui.AccessControlDialog;
import javax.swing.JOptionPane;

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
        this.generatedRequirements.clear();
        OntologyModule module = (OntologyModule) Application.getInstance().getModule(OntologyModule.class.getName());
        GenerateAccessControlRequirements(module);
        GenerateActionControlRequirements(module);
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
            OntologyModule module = (OntologyModule) Application.getInstance().getModule(OntologyModule.class.getName());
            if (module.getAllDomains().isEmpty()) {
                JOptionPane.showMessageDialog(
                        parent,
                        "There is no domain to configure, please add at least 1 domain by modifying domains",
                        "No domain found",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                AccessControlDialog dlg = new AccessControlDialog(parent, true, module);
                dlg.setVisible(true);
            }
        });
        return menuItem;
    }

    private void GenerateActionControlRequirements(OntologyModule module) {
        final String actorBoilerplate = "Only <actor> are allowed to access to <module>";

        module.getAllDomains().forEach((final String domain) -> {
            module.getModulesFrom(domain).forEach((final String mod) -> {
                String boilerplate = actorBoilerplate.replace("<module>", mod);
                List<String> actors = module.getActorsFrom(mod);
                String placeholderValue = null;
                if (actors.size() == 0) {
                    return;
                } else if (actors.size() == 1) {
                    placeholderValue = actors.get(0);
                } else if (actors.size() == 2) {
                    placeholderValue = actors.get(0) + " and " + actors.get(1);
                } else {
                    placeholderValue = String.join(", ", actors.subList(0, actors.size() - 1))
                            + " and "
                            + actors.get(actors.size() - 1);
                }
                generatedRequirements.add(new Requirement(mod, boilerplate.replace("<actor>", placeholderValue)));
            });
        });
    }

    private void GenerateAccessControlRequirements(OntologyModule module) {
        final String actorBoilerplate = "Only <actor> are allowed to access to <module>";

        module.getAllDomains().forEach((final String domain) -> {
            module.getModulesFrom(domain).forEach((final String mod) -> {
                String boilerplate = actorBoilerplate.replace("<module>", mod);
                List<String> actors = module.getActorsFrom(mod);
                String placeholderValue = null;
                if (actors.size() == 0) {
                    return;
                } else if (actors.size() == 1) {
                    placeholderValue = actors.get(0);
                } else if (actors.size() == 2) {
                    placeholderValue = actors.get(0) + " and " + actors.get(1);
                } else {
                    placeholderValue = String.join(", ", actors.subList(0, actors.size() - 1))
                            + " and "
                            + actors.get(actors.size() - 1);
                }
                generatedRequirements.add(new Requirement(mod, boilerplate.replace("<actor>", placeholderValue)));
            });
        });
    }

}
