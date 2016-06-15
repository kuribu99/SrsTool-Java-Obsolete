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
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Kong My
 */
public class AccessControlModule extends RequirementModule implements HasMenu {

    public static class AccessControlData implements Serializable {

        private String domainName;
        private Map<String, List<String>> moduleAccessMap;
        private Map<String, List<String>> actionControlMap;

        public AccessControlData(String domainName) {
            this.domainName = domainName;
            this.moduleAccessMap = new HashMap<>();
            this.actionControlMap = new HashMap<>();
        }

        public String getDomainName() {
            return domainName;
        }

        public Map<String, List<String>> getModuleAccessMap() {
            return moduleAccessMap;
        }

        public Map<String, List<String>> getActionControlMap() {
            return actionControlMap;
        }

        @Override
        public String toString() {
            return String.format(
                    "AccessControlData = { domainName = %s, moduleAccessMap = %s, actionControlMap = %s",
                    this.domainName,
                    this.moduleAccessMap.toString(),
                    this.actionControlMap.toString());
        }

        @Override
        public AccessControlData clone() {
            AccessControlData clone = new AccessControlData(domainName);
            this.moduleAccessMap.forEach((key, val) -> {
                clone.moduleAccessMap.put(key, new ArrayList<>(val));
            });
            this.actionControlMap.forEach((key, val) -> {
                
                clone.actionControlMap.put(key, new ArrayList<>(val));
            });
            return clone;
        }

    }

    private static final String MODULE_NAME = "Access Control";
    public static String DATA_ACCESS_CONTROL = "accessControlData";

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
        List<String> dependencies = new ArrayList<>();
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

        Map<String, AccessControlData> dataMap = (Map<String, AccessControlData>) Application.getInstance()
                .getDataContext().getData().get(DATA_ACCESS_CONTROL);

        if (dataMap != null) {
            dataMap.forEach((domain, domainData) -> {
                domainData.getModuleAccessMap().forEach((moduleName, allowedActors) -> {
                    List<String> restrictedActors = module.getActorsFrom(domain);
                    restrictedActors.removeAll(allowedActors);

                    if (!allowedActors.isEmpty()) {
                        generatedRequirements.add(new Requirement(MODULE_NAME,
                                Sentences.FormatAsSentence(
                                        allowedBoilerplate
                                        .replace("<module>", moduleName)
                                        .replace("<actor>", Sentences.JoinArray(allowedActors)))));
                    }

                    if (!restrictedActors.isEmpty()) {
                        generatedRequirements.add(new Requirement(MODULE_NAME,
                                Sentences.FormatAsSentence(
                                        restrictedBoilerplate
                                        .replace("<module>", moduleName)
                                        .replace("<actor>", Sentences.JoinArray(restrictedActors)))));
                    }
                });
            });
        }
    }

    private void GenerateAccessControlRequirements(OntologyModule module) {
        final String allowedBoilerplate = "<actor> are allowed to <actions>";
        final String restrictedBoilerplate = "<actor> are not allowed to <actions>";

        Map<String, AccessControlData> dataMap = (Map<String, AccessControlData>) Application.getInstance()
                .getDataContext().getData().get(DATA_ACCESS_CONTROL);

        if (dataMap != null) {
            dataMap.forEach((domain, domainData) -> {
                domainData.getActionControlMap().forEach((actor, allowedActions) -> {
                    List<String> restrictedActions = module.getActionsFrom(domain);
                    restrictedActions.removeAll(allowedActions);

                    if (!allowedActions.isEmpty()) {
                        generatedRequirements.add(new Requirement(MODULE_NAME,
                                Sentences.FormatAsSentence(
                                        allowedBoilerplate
                                        .replace("<actor>", actor)
                                        .replace("<actions>", Sentences.JoinArray(allowedActions)))));
                    }

                    if (!restrictedActions.isEmpty()) {
                        generatedRequirements.add(new Requirement(MODULE_NAME,
                                Sentences.FormatAsSentence(
                                        restrictedBoilerplate
                                        .replace("<actor>", actor)
                                        .replace("<actions>", Sentences.JoinArray(restrictedActions)))));
                    }
                });
            });
        }
    }

}
