/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kongmy.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Kong My
 */
public abstract class Application implements Runnable {

    protected static Application instance;
    protected final Configuration configuration;
    protected final Map<String, Module> modules;
    protected final Set<String> modulesNotFound;

    public Application(Configuration configuration) {
        this.configuration = configuration;
        this.modules = new HashMap<>();
        this.modulesNotFound = new HashSet<>();
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

    public Set<String> getModulesNotFound() {
        return this.modulesNotFound;
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }

    public void LoadModules() {
        this.modules.clear();
        this.modulesNotFound.clear();
        String[] moduleClassNames = this.configuration.settings.get(Configuration.MODULES_CLASS_NAMES).split("[, ]+");

        for (String moduleClassName : moduleClassNames) {
            if (!LoadModule(moduleClassName)) {
                modulesNotFound.add(moduleClassName);
            }
        }
        this.modules.values().stream()
                .filter((module) -> module instanceof Configurable)
                .map((module) -> (Configurable) module)
                .forEach((conf) -> {
                    instance.getConfiguration().AddConfiguration(conf);
                });
        this.modules.values().stream()
                .filter((module) -> module instanceof Loadable)
                .map((module) -> (Loadable) module)
                .forEach((module) -> module.Load());

    }

    protected boolean LoadModule(String className) {
        if (className == null || className.isEmpty()) {
            return true;
        }
        try {
            Module module = (Module) Class.forName(className).newInstance();
            List<String> dependencies = module.getDependencies();
            if (dependencies != null && dependencies.size() > 0) {
                List<String> required = dependencies.stream()
                        .filter((dep) -> !modules.containsKey(dep))
                        .collect(Collectors.toList());
                for (String dep : required) {
                    if (!LoadModule(dep)) {
                        modulesNotFound.add(dep);
                        return false;
                    }
                }
            }
            this.modules.put(module.getModuleName(), module);
            return true;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            modulesNotFound.add(className);
            return false;
        }
    }
}
