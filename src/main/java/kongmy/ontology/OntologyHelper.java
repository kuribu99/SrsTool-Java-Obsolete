/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kongmy.ontology;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Kong My
 */
public class OntologyHelper extends OWLHelper {

    private final String fileName;
    
    public OntologyHelper(String fileName) {
        super();
        this.fileName = fileName;
    }
    
    public boolean Load() {    
        try {
            super.Load(fileName);
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(OntologyHelper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public String getFileName() {
        return this.fileName;
    }
        
    public List<String> getTargetIndividualsByObjectProperty (String sourceName, String propertyName) {
        return getObjectPropertyTargets(sourceName, propertyName).stream()
                .map((ind) -> getString(ind.getIRI()))
                .collect(Collectors.toList());
    }
    
    public List<String> getClassIndividuals(String className) {
        return getIndividualsFromClass(className).stream()
                .map((ind) -> getString(ind.getIRI()))
                .collect(Collectors.toList());
    }
    
}
