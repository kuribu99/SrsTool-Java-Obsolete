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
import kongmy.core.Application;
import kongmy.core.Configurable;
import kongmy.core.Module;
import kongmy.ontology.OntologyHelper;
import kongmy.ontology.OntologyKey;

/**
 *
 * @author Kong My
 */
public class OntologyModule extends Module implements Configurable {
    
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
    
    public boolean LoadOntology() {
        helper = new OntologyHelper(Application.getInstance().getConfiguration().getSetting(ONTOLOGY_FILE_PATH));
        return helper.Load();
    }
    
    public List<String> getActorsFromModule(String domainName, String moduleName) {
        List<String> allowed = new ArrayList<>();
        List<String> disallowed = new ArrayList<>();
        
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
        
        
        if(domainName == null || domainName.isEmpty())
            return helper.getClassIndividuals(OntologyKey.Class.ACTOR);
        
        if(moduleName == null || moduleName.isEmpty())
            return helper.getTargetIndividualsByObjectProperty(domainName, OntologyKey.ObjectProperty.HAS_ACTION);
        else 
            return helper.getTargetIndividualsByObjectProperty(moduleName, OntologyKey.ObjectProperty.HAS_ACTOR);        
    }
    
    public void AddActorToModule(String actorName, String moduleName) {
        helper.AddObjectProperty(moduleName, OntologyKey.ObjectProperty.HAS_ACTION, actorName);
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
    
}
