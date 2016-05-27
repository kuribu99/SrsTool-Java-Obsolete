/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kongmy.ontology;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;

/**
 *
 * @author Kong My
 */
public class OWLHelper {
    
    protected final OWLOntologyManager manager;
    protected final OWLDataFactory factory;
    protected OWLReasoner reasoner;
    protected String baseIRI;
    protected OWLOntology ontology;
        
    public OWLHelper() {        
        manager =  OWLManager.createOWLOntologyManager();
        factory = manager.getOWLDataFactory();
    }
    
    public void Load(String fileName) throws OWLOntologyCreationException {
        ontology = manager.loadOntologyFromOntologyDocument(new File(fileName));        
        baseIRI = ontology.getOntologyID().getOntologyIRI().toString();
        reasoner = new StructuralReasonerFactory().createReasoner(ontology);
    }
    
    public static IRI getIRI(String baseIRI, String name) {
        return IRI.create(baseIRI + "#" + name);
    }
    
    public IRI getIRI(String name) {
        return getIRI(baseIRI, name);
    }
    
    public static String getString(IRI iri) {
        return iri.getRemainder().get();
    }
    
    public OWLClass getClass(String className) {
        return factory.getOWLClass(getIRI(className));
    }
    
    public OWLIndividual getIndividual(String name) {
        return name == null || name.isEmpty()? 
                factory.getOWLAnonymousIndividual():
                factory.getOWLNamedIndividual(getIRI(name));
    }
    
    public OWLIndividual getNamedIndividual(String name) {
        return factory.getOWLNamedIndividual(getIRI(name));
    }
    
    
    public OWLObjectProperty getObjectProperty(String name) {
        return factory.getOWLObjectProperty(getIRI(name));
    }
    
    public Set<OWLNamedIndividual> getIndividualsFromClass(String className) {
        OWLClass cls = getClass(className);
        return reasoner.getInstances(cls, true).getFlattened();
    }
    
    public Set<OWLNamedIndividual> getObjectPropertyTargets(String sourceName, String propertyName) {
        OWLNamedIndividual source = getIndividual(sourceName).asOWLNamedIndividual();
        OWLObjectProperty property = getObjectProperty(propertyName);
        return reasoner.getObjectPropertyValues(source, property).getFlattened();
    }
    
    public OWLObjectPropertyAssertionAxiom getObjectPropertyAxiom(String sourceName, String propertyName, String targetName) {
        OWLIndividual source = getIndividual(sourceName);
        OWLIndividual target = getIndividual(targetName);
        OWLObjectProperty property = getObjectProperty(propertyName);
        
        return factory.getOWLObjectPropertyAssertionAxiom(property, source, target);
    }
    
    public OWLClassAssertionAxiom getClassAxiom(String className, String individualName) {
        OWLClass cls = getClass(className);
        OWLIndividual ind = getIndividual(individualName);
        return factory.getOWLClassAssertionAxiom(cls, ind);
    }
    
    public boolean Save() {
        try {
            manager.saveOntology(ontology);
            return true;
        } catch (OWLOntologyStorageException ex) {
            Logger.getLogger(OWLHelper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean SaveAs(String fileName) throws FileNotFoundException {
        try {
            manager.saveOntology(ontology, new FileOutputStream(fileName));
            return true;
        } catch (OWLOntologyStorageException ex) {
            Logger.getLogger(OWLHelper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void AddIndividual(String className, String individualName) {
        OWLClassAssertionAxiom axiom = getClassAxiom(className, individualName);
        manager.addAxiom(ontology, axiom);
    }
    
    public void RemoveIndividual(String className, String individualName) {
        OWLClassAssertionAxiom axiom = getClassAxiom(className, individualName);
        manager.removeAxiom(ontology, axiom);
    }
    
    public void AddObjectProperty(String sourceName, String propertyName, String targetName) {
        OWLObjectPropertyAssertionAxiom axiom = getObjectPropertyAxiom(sourceName, propertyName, targetName);
        manager.addAxiom(ontology, axiom);
    }
    
    public void AddObjectPropertyAssertion(String sourceName, String propertyName, String targetName) {
        OWLObjectPropertyAssertionAxiom axiom = getObjectPropertyAxiom(sourceName, propertyName, targetName);
        manager.addAxiom(ontology, axiom);
    }
    
    public void RemoveObjectPropertyAssertion(String sourceName, String propertyName, String targetName) {
        OWLObjectPropertyAssertionAxiom axiom = getObjectPropertyAxiom(sourceName, propertyName, targetName);
        manager.removeAxiom(ontology, axiom);
    }
    
}
