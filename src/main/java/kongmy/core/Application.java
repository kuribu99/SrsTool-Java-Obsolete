/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kongmy.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Owner
 */
public abstract class Application implements Runnable {
    
    protected static Application instance;
    protected final Configuration configuration;
    protected final Map<String, Module> modules;
    protected final List<String> modulesNotFound;
    
    public Application(Configuration configuration) {
        this.configuration = configuration;
        this.modules = new HashMap<>();
        this.modulesNotFound = new ArrayList<>();
    }
    
    public static Application getInstance() {
        return instance;
    }
    
    public Module getModule(String moduleName) {
        return this.modules.get(moduleName);
    }
    
    public Map<String, Module> getModules() {
        return this.modules;
    }
    
    public List<String> getModulesNotFound() {
        return this.modulesNotFound;
    }
    
    public Configuration getConfiguration() {
        return this.configuration;
    }
    
    public void LoadModules() {
        this.modules.clear();
        this.modulesNotFound.clear();
        String[] moduleClassNames = this.configuration.settings.get(Configuration.MODULES_CLASS_NAMES).split("[, ]+");
        
        for(String moduleClassName: moduleClassNames) {
            try {
                Module module = (Module) Class.forName(moduleClassName).newInstance();
                this.modules.put(module.getModuleName(), module);
                
            } catch (ClassNotFoundException|InstantiationException|IllegalAccessException ex) {
                modulesNotFound.add(moduleClassName);
            }
        }
    }
}
