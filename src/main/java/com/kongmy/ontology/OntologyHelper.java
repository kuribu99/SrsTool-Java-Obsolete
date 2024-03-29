/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kongmy.ontology;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

/**
 *
 * @author Kong My
 */
public class OntologyHelper extends OWLHelper {

    protected static final String DEFAULT_ONTOLOGY_IRI = "http://www.semanticweb.org/kongmy/ontologies/2016/5/srs";
    private final String fileName;

    public OntologyHelper(String fileName) {
        super();
        this.fileName = fileName;
    }

    public boolean Load() {
        try {
            super.Load(fileName);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public String getFileName() {
        return this.fileName;
    }

    public List<String> getTargetIndividualsByObjectProperty(String sourceName, String propertyName) {
        return getObjectPropertyTargets(sourceName, propertyName).stream()
                .map((ind) -> getString(ind.getIRI()))
                .collect(Collectors.toList());
    }

    public List<String> getClassIndividuals(String className) {
        return getIndividualsFromClass(className).stream()
                .map((ind) -> getString(ind.getIRI()))
                .collect(Collectors.toList());
    }

    public void CreateDefaultOntologyFile() {
        try {
            manager.getOntologies().stream().forEach((ontology) -> manager.removeOntology(ontology));
            ontology = manager.createOntology(IRI.create(DEFAULT_ONTOLOGY_IRI));
            manager.saveOntology(ontology, new FileOutputStream(fileName));
            Load();

            OntologyKey.Class.getReservedKeywords().stream()
                    .forEach((val) -> AddIndividual(val, null));

            OntologyKey.ObjectProperty.getReservedKeywords().stream()
                    .forEach((val) -> AddObjectPropertyAssertion(null, val, null));

            OntologyKey.QualityCharacteristics.getReservedKeywords().stream()
                    .forEach((val) -> AddIndividual(OntologyKey.Class.QUALITY_CHARACTERISTICS, val));

            Save();
        } catch (OWLOntologyStorageException | OWLOntologyCreationException | IOException ex) {
            Logger.getLogger(OntologyHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
