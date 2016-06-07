/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kongmy.ontology;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kong My
 */
public final class OntologyKey {

    public static final class Class {

        public static final String DOMAIN = "domain";
        public static final String MODULE = "module";
        public static final String ACTOR = "actor";
        public static final String ACTION = "action";
        public static final String METRIC = "metric";
        public static final String QUALITY_CHARACTERISTICS = "qualityCharacteristics";

        public static List<String> getReservedKeywords() {
            List<String> list = new ArrayList<>();
            list.add(DOMAIN);
            list.add(MODULE);
            list.add(ACTOR);
            list.add(ACTION);
            list.add(METRIC);
            list.add(QUALITY_CHARACTERISTICS);
            return list;
        }

    }

    public static final class ObjectProperty {

        public static final String HAS_ACTOR = "hasActor";
        public static final String HAS_MODULE = "hasModule";
        public static final String HAS_ACTION = "hasAction";
        public static final String HAS_METRIC = "hasMetric";

        public static List<String> getReservedKeywords() {
            List<String> list = new ArrayList<>();
            list.add(HAS_ACTOR);
            list.add(HAS_MODULE);
            list.add(HAS_ACTION);
            list.add(HAS_METRIC);
            return list;
        }

    }

    public static final class QualityCharacteristics {

        public static final String FUNCTIONAL_SUITABILITY = "functionalSuitability";
        public static final String PERFORMANCE_EFFICIENCY = "performanceEfficiency";
        public static final String COMPATIBILITY = "compatibility";
        public static final String USABILITY = "usability";
        public static final String RELIABILITY = "reliability";
        public static final String SECURITY = "security";
        public static final String MAINTAINABILITY = "maintainability";
        public static final String PORTABILITY = "portability";

        public static List<String> getReservedKeywords() {
            List<String> list = new ArrayList<>();
            list.add(FUNCTIONAL_SUITABILITY);
            list.add(PERFORMANCE_EFFICIENCY);
            list.add(COMPATIBILITY);
            list.add(USABILITY);
            list.add(RELIABILITY);
            list.add(SECURITY);
            list.add(MAINTAINABILITY);
            list.add(PORTABILITY);
            return list;
        }

    }

    public static List<String> getReservedKeywords() {
        List<String> list = new ArrayList<>();
        list.addAll(Class.getReservedKeywords());
        list.addAll(ObjectProperty.getReservedKeywords());
        list.addAll(QualityCharacteristics.getReservedKeywords());
        return list;
    }

}
