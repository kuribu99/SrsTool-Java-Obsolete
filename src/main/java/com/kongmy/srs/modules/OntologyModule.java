/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kongmy.srs.modules;

import java.awt.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import com.kongmy.core.Application;
import com.kongmy.core.Configurable;
import com.kongmy.core.HasMenu;
import com.kongmy.core.Loadable;
import com.kongmy.core.Module;
import com.kongmy.ontology.OntologyHelper;
import com.kongmy.ontology.OntologyKey;
import com.kongmy.srs.modules.ui.ModifyDialog;

/**
 *
 * @author Kong My
 */
public class OntologyModule extends Module
        implements Configurable, Loadable, HasMenu {

    public static final String ONTOLOGY_FILE_PATH = "ontology_file_path";
    private OntologyHelper helper;

    public OntologyModule() {
        super();
    }

    @Override
    public Map<String, String> getConfiguration() {
        Map<String, String> configuration = new HashMap<>();
        configuration.put(ONTOLOGY_FILE_PATH, "ontology.owl");
        return configuration;
    }

    @Override
    public List<String> getDependencies() {
        return null;
    }

    @Override
    public void Load() {
        String filePath = Application.getInstance().getConfiguration().getSetting(ONTOLOGY_FILE_PATH);
        helper = new OntologyHelper(filePath);
        if (!helper.Load()) {
            helper.CreateDefaultOntologyFile();
            JOptionPane.showMessageDialog(null,
                    "Failed to load ontology file, loaded default ontology instead",
                    "Error loading ontology",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public Component getMenu(JFrame parent) {
        JMenu menu = new JMenu("Modify Ontology Attributes");

        JMenuItem menuItem = new JMenuItem("Create default ontology");
        menuItem.addActionListener((e) -> {
            if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(
                    parent,
                    "This will override existing ontology.\nAre you sure?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE)) {

                helper.CreateDefaultOntologyFile();
                JOptionPane.showMessageDialog(parent, "Created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                Load();
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Modify Domains");
        menuItem.addActionListener((e) -> {
            Load();
            ModifyDialog dlg = new ModifyDialog(parent, true, new DomainDialogListener());
            dlg.setVisible(true);
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Modify Modules");
        menuItem.addActionListener((e) -> {
            Load();
            ModifyDialog dlg = new ModifyDialog(parent, true, new ModuleDialogListener());
            dlg.setVisible(true);
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Modify Actors");
        menuItem.addActionListener((e) -> {
            Load();
            ModifyDialog dlg = new ModifyDialog(parent, true, new ActorDialogListener());
            dlg.setVisible(true);
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Modify Actions");
        menuItem.addActionListener((e) -> {
            Load();
            ModifyDialog dlg = new ModifyDialog(parent, true, new ActionDialogListener());
            dlg.setVisible(true);
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Modify Resource Constraints");
        menuItem.addActionListener((e) -> {
            Load();
            ModifyDialog dlg = new ModifyDialog(parent, true, new ResourceConstraintDialogListener());
            dlg.setVisible(true);
        });
        menu.add(menuItem);

        return menu;
    }

    private abstract class OntologyDialogListener implements ModifyDialog.DialogListener {

        @Override
        public void onEditButtonClicked(String oldValue, String newValue) {
            Rename(oldValue, newValue);
        }

        @Override
        public void onDeleteButtonClicked(List<String> selectedValue) {
            selectedValue.stream().forEach((val) -> helper.RemoveIndividual(val));
        }

        @Override
        public void onSaveButtonClicked() {
            Save();
        }

        @Override
        public void onCancelButtonClicked() {
            Load();
        }

    }

    private class DomainDialogListener extends OntologyDialogListener {

        @Override
        public void onAddButtonClicked(String newValue) {
            AddNewDomain(newValue);
        }

        @Override
        public List<String> getData() {
            return getAllDomains();
        }

        @Override
        public String getTitle() {
            return "Modify Domain";
        }

        @Override
        public String getBorderTitle() {
            return "Domains";
        }

    }

    private class ModuleDialogListener extends OntologyDialogListener {

        @Override
        public void onAddButtonClicked(String newValue) {
            AddNewModule(newValue);
        }

        @Override
        public void onDeleteButtonClicked(List<String> selectedValue) {
            selectedValue.stream().forEach((val) -> helper.RemoveIndividual(val));
        }

        @Override
        public List<String> getData() {
            return getAllModules();
        }

        @Override
        public String getTitle() {
            return "Modify Module";
        }

        @Override
        public String getBorderTitle() {
            return "Modules";
        }

    }

    private class ActorDialogListener extends OntologyDialogListener {

        @Override
        public void onAddButtonClicked(String newValue) {
            AddNewActor(newValue);
        }

        @Override
        public List<String> getData() {
            return getAllActors();
        }

        @Override
        public String getTitle() {
            return "Modify Actor";
        }

        @Override
        public String getBorderTitle() {
            return "Actors";
        }

    }

    private class ActionDialogListener extends OntologyDialogListener {

        @Override
        public void onAddButtonClicked(String newValue) {
            AddNewAction(newValue);
        }

        @Override
        public List<String> getData() {
            return getAllActions();
        }

        @Override
        public String getTitle() {
            return "Modify Action";
        }

        @Override
        public String getBorderTitle() {
            return "Actions";
        }

    }

    private class ResourceConstraintDialogListener extends OntologyDialogListener {

        @Override
        public void onAddButtonClicked(String newValue) {
            AddNewResourceConstraint(newValue);
        }

        @Override
        public List<String> getData() {
            return getAllResourceConstraintMetrics();
        }

        @Override
        public String getTitle() {
            return "Modify Resource Constraints";
        }

        @Override
        public String getBorderTitle() {
            return "Resource Constraints";
        }

    }

    public void setHelper(OntologyHelper helper) {
        this.helper = helper;
    }

    public void Save() {
        helper.Save();
    }

    public List<String> getAllDomains() {
        return helper.getClassIndividuals(OntologyKey.Class.DOMAIN);
    }

    public List<String> getAllModules() {
        return helper.getClassIndividuals(OntologyKey.Class.MODULE);
    }

    public List<String> getAllActors() {
        return helper.getClassIndividuals(OntologyKey.Class.ACTOR);
    }

    public List<String> getAllActions() {
        return helper.getClassIndividuals(OntologyKey.Class.ACTION);
    }

    public List<String> getModulesFrom(String sourceName) {
        return helper.getTargetIndividualsByObjectProperty(sourceName, OntologyKey.ObjectProperty.HAS_MODULE);
    }

    public List<String> getActorsFrom(String sourceName) {
        return helper.getTargetIndividualsByObjectProperty(sourceName, OntologyKey.ObjectProperty.HAS_ACTOR);
    }

    public List<String> getActionsFrom(String sourceName) {
        return helper.getTargetIndividualsByObjectProperty(sourceName, OntologyKey.ObjectProperty.HAS_ACTION);
    }

    public void AddModuleTo(String sourceName, String moduleName) {
        helper.AddObjectPropertyAssertion(sourceName, OntologyKey.ObjectProperty.HAS_MODULE, moduleName);
    }

    public void RemoveModuleFrom(String sourceName, String moduleName) {
        helper.RemoveObjectPropertyAssertion(sourceName, OntologyKey.ObjectProperty.HAS_MODULE, moduleName);
    }

    public void AddActorTo(String sourceName, String actorName) {
        helper.AddObjectPropertyAssertion(sourceName, OntologyKey.ObjectProperty.HAS_ACTOR, actorName);
    }

    public void RemoveActorFrom(String sourceName, String actorName) {
        helper.RemoveObjectPropertyAssertion(sourceName, OntologyKey.ObjectProperty.HAS_ACTOR, actorName);
    }

    public void AddActionTo(String sourceName, String actionName) {
        helper.AddObjectPropertyAssertion(sourceName, OntologyKey.ObjectProperty.HAS_ACTION, actionName);
    }

    public void RemoveActionFrom(String sourceName, String actionName) {
        helper.RemoveObjectPropertyAssertion(sourceName, OntologyKey.ObjectProperty.HAS_ACTION, actionName);
    }

    public void AddNewDomain(String domainName) {
        helper.AddIndividual(OntologyKey.Class.DOMAIN, domainName);
    }

    public void AddNewModule(String moduleName) {
        helper.AddIndividual(OntologyKey.Class.MODULE, moduleName);
    }

    public void AddNewActor(String actorName) {
        helper.AddIndividual(OntologyKey.Class.ACTOR, actorName);
    }

    public void AddNewAction(String actionName) {
        helper.AddIndividual(OntologyKey.Class.ACTION, actionName);
    }

    public void Rename(String oldName, String newName) {
        helper.Rename(oldName, newName);
    }

    public List<String> getAllResourceConstraintMetrics() {
        return helper.getTargetIndividualsByObjectProperty(
                OntologyKey.QualityCharacteristics.PERFORMANCE_EFFICIENCY,
                OntologyKey.ObjectProperty.HAS_METRIC);
    }

    public void AddNewResourceConstraint(String constraintName) {
        helper.AddIndividual(OntologyKey.Class.METRIC, constraintName);
        this.AddResourceConstraintTo(
                OntologyKey.QualityCharacteristics.PERFORMANCE_EFFICIENCY,
                constraintName);
    }
    
    public List<String> getResourceConstraintFrom(String sourceName) {
        return helper.getTargetIndividualsByObjectProperty(sourceName, OntologyKey.ObjectProperty.HAS_METRIC);
    }

    public void AddResourceConstraintTo(String sourceName, String constraintName) {
        helper.AddObjectPropertyAssertion(sourceName, OntologyKey.ObjectProperty.HAS_METRIC, constraintName);
    }

    public void RemoveResourceConstraintFrom(String sourceName, String constraintName) {
        helper.RemoveObjectPropertyAssertion(sourceName, OntologyKey.ObjectProperty.HAS_METRIC, constraintName);
    }

}
