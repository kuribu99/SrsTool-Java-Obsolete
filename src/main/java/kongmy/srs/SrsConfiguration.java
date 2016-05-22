/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kongmy.srs;

import kongmy.core.Configuration;

/**
 *
 * @author Owner
 */
public class SrsConfiguration extends Configuration {
    // Constants
    protected static final String SRS_DEFAULT_MODULES = "kongmy.srs.modules.DummyDataModule";
    
    public static Configuration getDefaultConfiguration() {
        Configuration configuration = Configuration.getDefaultConfiguration();
        configuration.getSettings().put(MODULES_CLASS_NAMES, SRS_DEFAULT_MODULES);
        return configuration;
    }
}
