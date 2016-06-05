/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kongmy.tests;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.kongmy.ontology.OWLHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
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

/**
 *
 * @author Kong My
 */
public class OWLHelperTest {

    private final static String FILE_NAME = "OwlHelperTestFile.owl";
    private final static String BASE_IRI = "http://www.semanticweb.org/kongmy/ontologies/2016/5/test";
    private static OWLHelper helper;
    private static OWLOntologyManager manager;
    private static OWLDataFactory factory;
    ;
    private static OWLOntology ontology;

    public static String getIRIString(String suffix) {
        return BASE_IRI + "#" + suffix;
    }

    public static IRI getIRI(String suffix) {
        return IRI.create(getIRIString(suffix));
    }

    public static IRI getBaseIRI() {
        return IRI.create(BASE_IRI);
    }

    @Before
    public void setUp() throws Exception {
        helper = new OWLHelper();
        manager = OWLManager.createOWLOntologyManager();
        factory = manager.getOWLDataFactory();
        ontology = manager.createOntology(getBaseIRI());
        manager.saveOntology(ontology, new FileOutputStream(FILE_NAME));
    }

    @After
    public void tearDown() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void TestLoad() throws Exception {
        helper.Load(FILE_NAME);
        assertEquals(getIRIString("abc"), helper.getIRI("abc").toString());
    }

    @Test
    public void TestGetIRI() throws OWLOntologyCreationException {
        helper.Load(FILE_NAME);
        assertEquals(getIRI("abc"), helper.getIRI("abc"));
    }

    @Test
    public void TestGetClass() throws OWLOntologyCreationException {
        helper.Load(FILE_NAME);

        OWLClass cls = factory.getOWLClass(getIRI("testClass"));
        manager.addAxiom(ontology, factory.getOWLClassAssertionAxiom(cls, factory.getOWLAnonymousIndividual()));

        OWLClass actualCls = helper.getClass("testClass");
        assertEquals(getIRIString("testClass"), actualCls.getIRI().toString());
    }

    @Test
    public void TestGetIndividual() throws OWLOntologyCreationException {
        helper.Load(FILE_NAME);

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(getIRI("testIndividual"));
        OWLClass cls = factory.getOWLClass(getIRI("testClass"));
        manager.addAxiom(ontology, factory.getOWLClassAssertionAxiom(cls, ind));

        OWLNamedIndividual actualInd = helper.getIndividual("testIndividual").asOWLNamedIndividual();
        assertEquals(getIRIString("testIndividual"), actualInd.getIRI().toString());
    }

    @Test
    public void TestGetIndividualsFromClass() throws Exception {
        List<String> expectedResult = new ArrayList<>();
        IRI iri;
        OWLClass cls;
        OWLIndividual ind;
        OWLClassAssertionAxiom assertion;

        for (int i = 0; i <= 5; i++) {
            iri = getIRI("abc_" + i);
            expectedResult.add(iri.toString());
            cls = factory.getOWLClass(getIRI("testClass"));
            ind = factory.getOWLNamedIndividual(iri);
            assertion = factory.getOWLClassAssertionAxiom(cls, ind);
            manager.addAxiom(ontology, assertion);
        }
        manager.saveOntology(ontology, new FileOutputStream(FILE_NAME));
        helper.Load(FILE_NAME);

        List<String> actualResult = helper.getIndividualsFromClass("testClass").stream()
                .map((item) -> item.getIRI().toString())
                .collect(Collectors.toList());
        expectedResult.removeAll(actualResult);
        assertEquals(0, expectedResult.size());
    }

    @Test
    public void TestGetObjectProperty() throws OWLOntologyCreationException {
        helper.Load(FILE_NAME);

        OWLObjectProperty property = factory.getOWLObjectProperty(getIRI("testObjectProperty"));
        manager.addAxiom(ontology, factory.getOWLObjectPropertyAssertionAxiom(property,
                factory.getOWLAnonymousIndividual(),
                factory.getOWLAnonymousIndividual()));

        OWLObjectProperty actualProperty = helper.getObjectProperty("testObjectProperty");
        assertEquals(getIRIString("testObjectProperty"), actualProperty.getIRI().toString());
    }

    @Test
    public void TestGetClassAxiom() throws OWLOntologyCreationException {
        helper.Load(FILE_NAME);

        OWLClass cls = factory.getOWLClass(getIRI("testClass"));
        OWLNamedIndividual ind = factory.getOWLNamedIndividual(getIRI("namedIndividual"));
        OWLClassAssertionAxiom assertionExpected = factory.getOWLClassAssertionAxiom(cls, ind);

        OWLClassAssertionAxiom assertionActual = helper.getClassAxiom("testClass", "namedIndividual");

        assertEquals(assertionExpected.getClassExpression().asOWLClass().getIRI().toString(),
                assertionActual.getClassExpression().asOWLClass().getIRI().toString());
        assertEquals(assertionExpected.getIndividual().asOWLNamedIndividual().getIRI().toString(),
                assertionActual.getIndividual().asOWLNamedIndividual().getIRI().toString());
    }

    @Test
    public void TestGetObjectPropertyAxiom() throws OWLOntologyCreationException {
        helper.Load(FILE_NAME);

        OWLObjectProperty property = factory.getOWLObjectProperty(getIRI("property"));
        OWLNamedIndividual source = factory.getOWLNamedIndividual(getIRI("source"));
        OWLNamedIndividual target = factory.getOWLNamedIndividual(getIRI("target"));
        OWLObjectPropertyAssertionAxiom assertionExpected = factory.getOWLObjectPropertyAssertionAxiom(
                property, source, target);

        OWLObjectPropertyAssertionAxiom assertionActual = helper.getObjectPropertyAxiom("source", "property", "target");

        assertEquals(assertionExpected.getProperty().asOWLObjectProperty().getIRI().toString(),
                assertionActual.getProperty().asOWLObjectProperty().getIRI().toString());

        List<String> expectedIndividuals = assertionExpected.getIndividualsInSignature().stream()
                .map((ind) -> ind.getIRI().toString())
                .collect(Collectors.toList());
        List<String> actualIndividuals = assertionActual.getIndividualsInSignature().stream()
                .map((ind) -> ind.getIRI().toString())
                .collect(Collectors.toList());
        expectedIndividuals.removeAll(actualIndividuals);
        assertEquals(0, expectedIndividuals.size());
    }

}
