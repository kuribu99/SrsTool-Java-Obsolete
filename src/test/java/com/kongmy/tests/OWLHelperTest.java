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
import java.io.FileNotFoundException;
import java.util.Optional;
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
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;

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
    public void TestSave() throws Exception {
        helper.Load(FILE_NAME);
        String actual = helper.getIRI("abc").toString();
        helper.Save();

        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(new File(FILE_NAME));
        String expected = manager.getOWLDataFactory().getOWLClass(getIRI("abc")).getIRI().toString();

        assertEquals(expected, actual);
    }

    @Test
    public void TestSaveAs() throws Exception {
        helper.Load(FILE_NAME);
        String fileName = "abc.owl";

        String actual = helper.getIRI("abc").toString();
        helper.SaveAs(fileName);

        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(new File(fileName));
        String expected = manager.getOWLDataFactory().getOWLClass(getIRI("abc")).getIRI().toString();
        new File(fileName).delete();

        assertEquals(expected, actual);
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
    public void TestGetObjectPropertyAxiom() throws OWLOntologyCreationException, FileNotFoundException, OWLOntologyStorageException, OWLOntologyStorageException {
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

    @Test
    public void TestGetObjectPropertyTargets() throws OWLOntologyCreationException, OWLOntologyStorageException, FileNotFoundException {
        OWLObjectProperty property = factory.getOWLObjectProperty(getIRI("property"));
        OWLNamedIndividual source = factory.getOWLNamedIndividual(getIRI("source"));
        OWLNamedIndividual target;
        String targetName;
        OWLObjectPropertyAssertionAxiom assertion;
        List<String> expectedTargets = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            targetName = "target" + i;
            target = factory.getOWLNamedIndividual(getIRI(targetName));
            assertion = factory.getOWLObjectPropertyAssertionAxiom(property, source, target);
            manager.addAxiom(ontology, assertion);
            expectedTargets.add(getIRIString(targetName));
        }
        manager.saveOntology(ontology, new FileOutputStream(FILE_NAME));

        helper.Load(FILE_NAME);
        List<String> actualTargets = helper.getObjectPropertyTargets("source", "property").stream()
                .map((ind) -> ind.getIRI().toString())
                .collect(Collectors.toList());

        expectedTargets.removeAll(actualTargets);
        assertEquals(0, expectedTargets.size());
    }

    @Test
    public void TestRename() throws OWLOntologyCreationException, FileNotFoundException, OWLOntologyStorageException {
        helper.Load(FILE_NAME);

        OWLClass cls = factory.getOWLClass(getIRI("testClass"));
        OWLNamedIndividual ind = factory.getOWLNamedIndividual(getIRI("namedIndividual"));
        OWLClassAssertionAxiom assertion = factory.getOWLClassAssertionAxiom(cls, ind);
        manager.addAxiom(ontology, assertion);

        manager.saveOntology(ontology, new FileOutputStream(FILE_NAME));
        helper.Load(FILE_NAME);

        helper.Rename("testClass", "renamedTestClass");
        helper.Save();

        manager = OWLManager.createOWLOntologyManager();
        ontology = manager.loadOntologyFromOntologyDocument(new File(FILE_NAME));
        factory = manager.getOWLDataFactory();
        OWLReasoner reasoner = new StructuralReasonerFactory().createReasoner(ontology);

        OWLClass newCls = factory.getOWLClass(getIRI("renamedTestClass"));
        Optional<OWLNamedIndividual> namedInd = reasoner.getInstances(newCls, true).getFlattened().stream().findFirst();
        assertTrue(namedInd.isPresent());
        assertEquals(ind.getIRI().toString(), namedInd.get().getIRI().toString());
    }

    @Test
    public void TestAddIndividual() throws OWLOntologyCreationException {
        helper.Load(FILE_NAME);
        helper.AddIndividual("testClass", "testIndividual");
        helper.Save();

        manager = OWLManager.createOWLOntologyManager();
        ontology = manager.loadOntologyFromOntologyDocument(new File(FILE_NAME));
        factory = manager.getOWLDataFactory();
        OWLReasoner reasoner = new StructuralReasonerFactory().createReasoner(ontology);

        OWLClass cls = factory.getOWLClass(getIRI("testClass"));
        Optional<OWLNamedIndividual> ind = reasoner.getInstances(cls, true).getFlattened().stream().findFirst();
        assertTrue(ind.isPresent());
        assertEquals(getIRIString("testIndividual"), ind.get().getIRI().toString());
    }

    @Test
    public void TestAddObjectProperty() throws OWLOntologyCreationException {
        helper.Load(FILE_NAME);
        helper.AddObjectPropertyAssertion("testClass", "hasIndividual", "testIndividual");
        helper.Save();

        manager = OWLManager.createOWLOntologyManager();
        ontology = manager.loadOntologyFromOntologyDocument(new File(FILE_NAME));
        factory = manager.getOWLDataFactory();
        OWLReasoner reasoner = new StructuralReasonerFactory().createReasoner(ontology);

        OWLNamedIndividual source = factory.getOWLNamedIndividual(getIRI("testClass"));
        OWLObjectProperty property = factory.getOWLObjectProperty(getIRI("hasIndividual"));
        Optional<OWLNamedIndividual> target = reasoner.getObjectPropertyValues(source, property)
                .getFlattened().stream().findFirst();

        assertTrue(target.isPresent());
        assertEquals(getIRIString("testIndividual"), target.get().getIRI().toString());
    }

    @Test
    public void TestRemoveObjectPropertyAssertion() throws OWLOntologyCreationException, OWLOntologyStorageException, FileNotFoundException {
        OWLNamedIndividual source = factory.getOWLNamedIndividual(getIRI("testClass"));
        OWLObjectProperty property = factory.getOWLObjectProperty(getIRI("hasIndividual"));
        OWLNamedIndividual target = factory.getOWLNamedIndividual(getIRI("testIndividual"));
        OWLObjectPropertyAssertionAxiom assertion = factory.getOWLObjectPropertyAssertionAxiom(property, source, target);

        manager.addAxiom(ontology, assertion);
        manager.saveOntology(ontology, new FileOutputStream(FILE_NAME));

        helper.Load(FILE_NAME);
        helper.RemoveObjectPropertyAssertion("testClass", "hasIndividual", "testIndividual");
        helper.Save();

        manager = OWLManager.createOWLOntologyManager();
        ontology = manager.loadOntologyFromOntologyDocument(new File(FILE_NAME));
        factory = manager.getOWLDataFactory();
        OWLReasoner reasoner = new StructuralReasonerFactory().createReasoner(ontology);

        source = factory.getOWLNamedIndividual(getIRI("testClass"));
        property = factory.getOWLObjectProperty(getIRI("hasIndividual"));
        int actual = reasoner.getObjectPropertyValues(source, property).getFlattened().size();

        assertEquals(0, actual);
    }

    @Test
    public void TestRemoveIndividual() throws OWLOntologyCreationException, OWLOntologyStorageException, FileNotFoundException {
        OWLClass cls = factory.getOWLClass(getIRI("testClass"));
        OWLNamedIndividual target = factory.getOWLNamedIndividual(getIRI("testIndividual"));
        OWLClassAssertionAxiom assertion = factory.getOWLClassAssertionAxiom(cls, target);

        manager.addAxiom(ontology, assertion);
        manager.saveOntology(ontology, new FileOutputStream(FILE_NAME));

        helper.Load(FILE_NAME);
        helper.RemoveIndividual("testClass", "testIndividual");
        helper.Save();

        manager = OWLManager.createOWLOntologyManager();
        ontology = manager.loadOntologyFromOntologyDocument(new File(FILE_NAME));
        factory = manager.getOWLDataFactory();
        cls = factory.getOWLClass(getIRI("testClass"));
        OWLReasoner reasoner = new StructuralReasonerFactory().createReasoner(ontology);

        int actual = reasoner.getInstances(cls, true).getFlattened().size();

        assertEquals(0, actual);
    }

    @Test
    public void TestGetNamedIndividual() throws OWLOntologyCreationException, OWLOntologyStorageException, FileNotFoundException {
        OWLClass cls = factory.getOWLClass(getIRI("testClass"));
        OWLNamedIndividual target = factory.getOWLNamedIndividual(getIRI("testIndividual"));
        OWLClassAssertionAxiom assertion = factory.getOWLClassAssertionAxiom(cls, target);

        manager.addAxiom(ontology, assertion);
        manager.saveOntology(ontology, new FileOutputStream(FILE_NAME));

        helper.Load(FILE_NAME);
        String actual = helper.getNamedIndividual("testIndividual").getIRI().toString();

        assertEquals(getIRIString("testIndividual"), actual);
    }

}
