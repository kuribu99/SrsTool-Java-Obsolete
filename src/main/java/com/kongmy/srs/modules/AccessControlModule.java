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
import com.kongmy.util.Sentences;
import javax.swing.JOptionPane;

/**
 *
 * @author Kong My
 */
public class AccessControlModule extends RequirementModule implements HasMenu {

    private static final String MODULE_NAME = "Access Control";
    
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
        final String allowedBoilerplate = "<actor> are allowed to access to <module> module";
        final String restrictedBoilerplate = "<actor> are restricted from accessing to <module> module";

        module.getAllDomains().forEach((final String domain) -> {
            module.getModulesFrom(domain).forEach((final String mod) -> {
                List<String> allowedActors = module.getActorsFrom(mod);
                List<String> restrictedActors = module.getActorsFrom(domain);
                restrictedActors.removeAll(allowedActors);

                if (!allowedActors.isEmpty()) {
                    generatedRequirements.add(new Requirement(MODULE_NAME,
                            Sentences.asSentence(
                                    allowedBoilerplate
                                    .replace("<module>", mod)
                                    .replace("<actor>", Sentences.JoinArrayAsSentence(allowedActors)))));
                }

                if (!restrictedActors.isEmpty()) {
                    generatedRequirements.add(new Requirement(MODULE_NAME,
                            Sentences.asSentence(
                                    restrictedBoilerplate
                                    .replace("<module>", mod)
                                    .replace("<actor>", Sentences.JoinArrayAsSentence(restrictedActors)))));
                }
            });
        });
    }

    private void GenerateAccessControlRequirements(OntologyModule module) {
        final String allowedBoilerplate = "<actor> are allowed to <actions>";
        final String restrictedBoilerplate = "<actor> are not allowed to <actions>";

        module.getAllDomains().forEach((final String domain) -> {
            module.getActorsFrom(domain).forEach((final String actor) -> {
                List<String> allowedActions = module.getActionsFrom(actor);
                List<String> restrictedActions = module.getActionsFrom(domain);
                restrictedActions.removeAll(allowedActions);

                if (!allowedActions.isEmpty()) {
                    generatedRequirements.add(new Requirement(MODULE_NAME,
                            Sentences.asSentence(
                                    allowedBoilerplate
                                    .replace("<actor>", actor)
                                    .replace("<actions>", Sentences.JoinArrayAsSentence(allowedActions)))));
                }

                if (!restrictedActions.isEmpty()) {
                    generatedRequirements.add(new Requirement(MODULE_NAME,
                            Sentences.asSentence(
                                    restrictedBoilerplate
                                    .replace("<actor>", actor)
                                    .replace("<actions>", Sentences.JoinArrayAsSentence(restrictedActions)))));
                }
            });
        });
    }

}
