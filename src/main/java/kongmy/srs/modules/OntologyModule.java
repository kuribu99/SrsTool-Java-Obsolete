/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kongmy.srs.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import kongmy.core.Application;
import kongmy.core.Configurable;
import kongmy.core.Loadable;
import kongmy.core.Module;
import kongmy.ontology.OntologyHelper;
import kongmy.ontology.OntologyKey;

/**
 *
 * @author Kong My
 */
public class OntologyModule extends Module implements Configurable, Loadable {

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

    public List<String> getAllModules() {
        if(true) {
            List<String> modules = new ArrayList<>();
            modules.add("subject registration");
            modules.add("login");
            modules.add("class modification");
            return modules;
        }
        return helper.getClassIndividuals(OntologyKey.Class.MODULE);
    }

    public List<String> getAllActors() {
        if(true) {
            List<String> actors = new ArrayList<>();
            actors.add("student");
            actors.add("officer");
            actors.add("lecturer");
            return actors;
        }
        return helper.getClassIndividuals(OntologyKey.Class.ACTOR);
    }

    public List<String> getAllActions() {
        if(true) {
            List<String> actions = new ArrayList<>();
            actions.add("register subject");
            actions.add("login");
            actions.add("add class");
            return actions;
        }
        return helper.getClassIndividuals(OntologyKey.Class.ACTION);
    }

    public List<String> getModulesFromDomain(String domainName) {
        if(true) {
            List<String> modules = new ArrayList<>();
            modules.add("login");
            modules.add("class modification");
            return modules;
        }
        return helper.getTargetIndividualsByObjectProperty(domainName, OntologyKey.ObjectProperty.HAS_MODULE);
    }

    public List<String> getActorsFromDomain(String domainName) {
        if(true) {
            List<String> actors = new ArrayList<>();
            actors.add("student");
            actors.add("officer");
            actors.add("lecturer");
            return actors;
        }
        return helper.getTargetIndividualsByObjectProperty(domainName, OntologyKey.ObjectProperty.HAS_ACTOR);
    }

    public List<String> getActionsFromDomain(String domainName) {
        if(true) {
            List<String> actions = new ArrayList<>();
            actions.add("register subject");
            actions.add("login");
            return actions;
        }
        return helper.getTargetIndividualsByObjectProperty(domainName, OntologyKey.ObjectProperty.HAS_ACTION);
    }

    public List<String> getActorsFromModule(String moduleName) {
        List<String> allowed = new ArrayList<>();
        List<String> disallowed = new ArrayList<>();

        allowed.add("student");
        disallowed.add("officer");
        disallowed.add("lecturer");
        allowed.add("student");
        disallowed.add("officer");
        disallowed.add("lecturer");
        allowed.add("student");
        disallowed.add("officer");
        disallowed.add("lecturer");
        allowed.add("student");
        disallowed.add("officer");
        disallowed.add("lecturer");
        allowed.add("student");
        disallowed.add("officer");
        disallowed.add("lecturer");
        allowed.add("student");
        disallowed.add("officer");
        disallowed.add("lecturer");

        if(moduleName == null) {
            List<String> all = new ArrayList<>(allowed);
            all.addAll(disallowed);
            return all;
        }
        if(true)
            return moduleName.equals("allowed")? allowed: disallowed;

        // Aboves are dummy data
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
}
