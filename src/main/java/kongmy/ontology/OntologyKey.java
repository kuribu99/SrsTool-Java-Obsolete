/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kongmy.ontology;

/**
 *
 * @author Kong My
 */
public final class OntologyKey {
    
    public static final class Class {
        public static final String ACTOR = "actor";
        public static final String DOMAIN = "domain";
        public static final String MODULE = "module";
        public static final String ACTION = "action";
    }
    
    public static final class ObjectProperty {
        public static final String HAS_ACTOR = "hasActor";
        public static final String HAS_MODULE = "hasModule";
        public static final String HAS_ACTION = "hasAction";
    }
    
}
