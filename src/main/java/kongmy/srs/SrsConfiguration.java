/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kongmy.srs;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kongmy.core.Configuration;

/**
 *
 * @author Owner
 */
public class SrsConfiguration extends Configuration {
    
    public static Configuration ReadOrDefaultConfiguration() {
        Configuration configuration = new SrsConfiguration();
        
        try {
            configuration.ReadConfigurationFile();
        }
        catch (FileNotFoundException ex) {
            configuration.LoadDefaultConfiguration();
            Logger.getLogger(Configuration.class.getName()).log(Level.INFO, "Default configuration file loaded", ex);
        }
        return configuration;
    }

    private static String[] getDefaultModules() {
        return new String[] {
            "kongmy.srs.modules.DummyDataModule",
            "kongmy.srs.modules.OntologyModule",
            "kongmy.srs.modules.AccessControlModule"
        };
    }

    @Override
    public void LoadDefaultConfiguration() {
        settings.clear();
        settings.put(MODULES_CLASS_NAMES, String.join(",", getDefaultModules()));
    }
}
