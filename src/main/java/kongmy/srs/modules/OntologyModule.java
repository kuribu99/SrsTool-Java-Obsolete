/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kongmy.srs.modules;

import java.util.ArrayList;
import java.util.List;
import kongmy.core.Module;
import kongmy.ontology.OntologyHelper;
import kongmy.ontology.OntologyKey;

/**
 *
 * @author Kong My
 */
public class OntologyModule extends Module {
    
    public OntologyModule() {
        super();
        allowed.add("student");
        disallowed.add("officer");
        disallowed.add("lecturer");        
    }
    
    public List<String> getAllActors() {
        return this.getActorsFromDomain(null);
    }
    
    public List<String> getActorsFromDomain(String domainName) {
        return this.getActorsFromModule(domainName, null);
    }
    
    public List<String> getActorsFromModule(String domainName, String moduleName) {
        if(moduleName == null) {
            List<String> all = new ArrayList<>(allowed);
            all.addAll(disallowed);
            return all;
        }
        if(true)
            return moduleName.equals("allowed")? allowed: disallowed;
        
        OntologyHelper helper = new OntologyHelper("ontology.owl");
        
        if(domainName == null)
            return helper.getClassInstances(OntologyKey.Class.ACTOR);
        
        if(moduleName == null)
            return helper.getClassInstances(OntologyKey.Class.ACTOR);
        else 
            return helper.getInstancesByObjectProperty(
                    moduleName,
                    OntologyKey.ObjectProperty.HAS_ACTOR);        
    }
    
    
    private static List<String> allowed = new ArrayList<>();
    private static List<String> disallowed = new ArrayList<>();
    
}
