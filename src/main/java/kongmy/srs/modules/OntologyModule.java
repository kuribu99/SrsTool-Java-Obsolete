/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kongmy.srs.modules;

import java.awt.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import kongmy.core.Application;
import kongmy.core.Configurable;
import kongmy.core.HasMenu;
import kongmy.core.Loadable;
import kongmy.core.Module;
import kongmy.ontology.OntologyHelper;
import kongmy.ontology.OntologyKey;
import kongmy.srs.modules.ui.ModifyActionDialog;
import kongmy.srs.modules.ui.ModifyActorDialog;
import kongmy.srs.modules.ui.ModifyDomainDialog;
import kongmy.srs.modules.ui.ModifyModuleDialog;

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
        if(!helper.Load()) {
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
        
        JMenuItem menuItem = new JMenuItem("Modify Domains");
        menuItem.addActionListener((e) -> new ModifyDomainDialog(parent, true, this).setVisible(true));
        menu.add(menuItem);
                
        menuItem = new JMenuItem("Modify Modules");
        menuItem.addActionListener((e) -> new ModifyModuleDialog(parent, true, this).setVisible(true));
        menu.add(menuItem);
                
        menuItem = new JMenuItem("Modify Actors");
        menuItem.addActionListener((e) -> new ModifyActorDialog(parent, true, this).setVisible(true));
        menu.add(menuItem);
                
        menuItem = new JMenuItem("Modify Actions");
        menuItem.addActionListener((e) -> new ModifyActionDialog(parent, true, this).setVisible(true));
        menu.add(menuItem);
        
        return menu;
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

    public List<String> getModulesFromDomain(String domainName) {
        return helper.getTargetIndividualsByObjectProperty(domainName, OntologyKey.ObjectProperty.HAS_MODULE);
    }

    public List<String> getActorsFromDomain(String domainName) {
        return helper.getTargetIndividualsByObjectProperty(domainName, OntologyKey.ObjectProperty.HAS_ACTOR);
    }

    public List<String> getActionsFromDomain(String domainName) {
        return helper.getTargetIndividualsByObjectProperty(domainName, OntologyKey.ObjectProperty.HAS_ACTION);
    }

    public List<String> getActorsFromModule(String moduleName) {
        return helper.getTargetIndividualsByObjectProperty(moduleName, OntologyKey.ObjectProperty.HAS_ACTOR);
    }

    public void AddModuleToDomain(String domainName, String moduleName) {
        helper.AddObjectPropertyAssertion(domainName, OntologyKey.ObjectProperty.HAS_MODULE, moduleName);
    }

    public void RemoveModuleFromDomain(String domainName, String moduleName) {
        helper.RemoveObjectPropertyAssertion(domainName, OntologyKey.ObjectProperty.HAS_MODULE, moduleName);
    }

    public void AddActorToDomain(String domainName, String actorName) {
        helper.AddObjectPropertyAssertion(domainName, OntologyKey.ObjectProperty.HAS_ACTOR, actorName);
    }

    public void RemoveActorFromDomain(String domainName, String actorName) {
        helper.RemoveObjectPropertyAssertion(domainName, OntologyKey.ObjectProperty.HAS_ACTOR, actorName);
    }

    public void AddActionToDomain(String domainName, String actionName) {
        helper.AddObjectPropertyAssertion(domainName, OntologyKey.ObjectProperty.HAS_ACTION, actionName);
    }

    public void RemoveActionFromDomain(String domainName, String actionName) {
        helper.RemoveObjectPropertyAssertion(domainName, OntologyKey.ObjectProperty.HAS_ACTION, actionName);
    }
    
    public void AddActorToModule(String moduleName, String actorName) {
        helper.AddObjectPropertyAssertion(moduleName, OntologyKey.ObjectProperty.HAS_ACTOR, actorName);
    }

    public void RemoveActorFromModule(String moduleName, String actorName) {
        helper.RemoveObjectPropertyAssertion(moduleName, OntologyKey.ObjectProperty.HAS_ACTOR, actorName);
    }
    
    public void AddActionToModule(String moduleName, String actionName) {
        helper.AddObjectPropertyAssertion(moduleName, OntologyKey.ObjectProperty.HAS_ACTION, actionName);
    }

    public void RemoveActionFromModule(String moduleName, String actionName) {
        helper.RemoveObjectPropertyAssertion(moduleName, OntologyKey.ObjectProperty.HAS_ACTION, actionName);
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
    
    public void RemoveDomain(String domainName) {
        helper.RemoveIndividual(OntologyKey.Class.DOMAIN, domainName);
    }
    
    public void RemoveModule(String moduleName) {
        helper.RemoveIndividual(OntologyKey.Class.MODULE, moduleName);
    }
    
    public void RemoveActor(String actorName) {
        helper.RemoveIndividual(OntologyKey.Class.ACTOR, actorName);
    }
    
    public void RemoveAction(String actionName) {
        helper.RemoveIndividual(OntologyKey.Class.ACTION, actionName);
    }

}
