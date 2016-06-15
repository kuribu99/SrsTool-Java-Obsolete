/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kongmy.srs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.kongmy.core.Configuration;
import com.kongmy.srs.modules.AccessControlModule;
import com.kongmy.srs.modules.ActionResourceConstraintModule;
import com.kongmy.srs.modules.DomainAttributeModule;
import com.kongmy.srs.modules.OntologyModule;
import com.kongmy.srs.modules.RefreshGeneratedRequirementModule;

/**
 *
 * @author Kong My
 */
public class SrsConfiguration extends Configuration {

    public static Configuration ReadOrDefaultConfiguration() {
        Configuration configuration = new SrsConfiguration();

        try {
            configuration.ReadConfigurationFile();
        } catch (FileNotFoundException ex) {
            try {
                configuration.LoadDefaultConfiguration();
                configuration.WriteConfigurationToFile();
            } catch (IOException ex1) {
                Logger.getLogger(SrsConfiguration.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return configuration;
    }

    private static String[] getDefaultModules() {
        return new String[]{
            //DummyDataModule.class.getName(),
            OntologyModule.class.getName(),
            AccessControlModule.class.getName(),
            DomainAttributeModule.class.getName(),
            RefreshGeneratedRequirementModule.class.getName(),
            ActionResourceConstraintModule.class.getName()
        };
    }

    @Override
    public void LoadDefaultConfiguration() {
        settings.clear();
        settings.put(CONFIGURATION_MODULES, String.join(",", getDefaultModules()));
    }
}
