/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kongmy.ontology;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.semanticweb.owlapi.model.OWLLogicalAxiom;
import org.semanticweb.owlapi.model.OWLObjectProperty;

/**
 *
 * @author Kong My
 */
public class OntologyHelper {

    private final String fileName;
    private final OWLHelper helper;
    
    public OntologyHelper(String fileName) {
        this.fileName = fileName;
        this.helper = new OWLHelper();
    }
    
    public boolean Load() {    
        try {
            helper.Load(fileName);
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
        
    public List<String> getInstancesByObjectProperty (String sourceName, String propertyName) {
        return helper.getObjectPropertyTargets(sourceName, propertyName).stream()
                .map((ind) -> helper.getString(ind.getIRI()))
                .collect(Collectors.toList());
    }
    
    public List<String> getClassInstances(String className) {
        return helper.getIndividualsFromClass(className).stream()
                .map((ind) -> helper.getString(ind.getIRI()))
                .collect(Collectors.toList());
    }
    
}
