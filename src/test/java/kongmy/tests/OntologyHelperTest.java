/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kongmy.tests;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import kongmy.ontology.OntologyHelper;
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
import org.semanticweb.owlapi.model.OWLOntologyManager;

/**
 *
 * @author Kong My
 */
public class OntologyHelperTest {

    private final static String FILE_NAME = "OwlHelperTestFile.owl";
    private final static String BASE_IRI = "http://www.semanticweb.org/kongmy/ontologies/2016/5/test";
    private static OntologyHelper helper;
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
        helper = new OntologyHelper(FILE_NAME);
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
        helper.Load();
        assertEquals(getIRIString("abc"), helper.getIRI("abc").toString());
    }

    @Test
    public void TestGetClassIndividuals() throws Exception {
        List<String> expectedResult = new ArrayList<>();
        String name;
        OWLClass cls;
        OWLIndividual ind;
        OWLClassAssertionAxiom assertion;

        for (int i = 0; i <= 5; i++) {
            name = "abc_" + i;
            expectedResult.add(name);
            cls = factory.getOWLClass(getIRI("testClass"));
            ind = factory.getOWLNamedIndividual(getIRI(name));
            assertion = factory.getOWLClassAssertionAxiom(cls, ind);
            manager.addAxiom(ontology, assertion);
        }
        manager.saveOntology(ontology, new FileOutputStream(FILE_NAME));
        helper.Load();

        List<String> actualResult = helper.getClassIndividuals("testClass");
        expectedResult.removeAll(actualResult);

        assertEquals(0, expectedResult.size());
    }

    @Test
    public void TestGetTargetIndividualsByObjectProperty() throws Exception {
        List<String> expectedResult = new ArrayList<>();
        String name;
        OWLObjectProperty property = factory.getOWLObjectProperty(getIRI("property"));
        OWLNamedIndividual source = factory.getOWLNamedIndividual(getIRI("source"));
        OWLNamedIndividual target;
        OWLObjectPropertyAssertionAxiom assertion;

        for (int i = 0; i <= 5; i++) {
            name = "abc_" + i;
            expectedResult.add(name);
            target = factory.getOWLNamedIndividual(getIRI(name));
            assertion = factory.getOWLObjectPropertyAssertionAxiom(property, source, target);
            manager.addAxiom(ontology, assertion);
        }

        manager.saveOntology(ontology, new FileOutputStream(FILE_NAME));
        helper.Load();

        List<String> actualResult = helper.getTargetIndividualsByObjectProperty("source", "property");
        expectedResult.removeAll(actualResult);

        assertEquals(0, expectedResult.size());
    }

}
