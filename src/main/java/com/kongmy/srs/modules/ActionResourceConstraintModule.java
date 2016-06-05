/*
 */
package com.kongmy.srs.modules;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import com.kongmy.core.HasMenu;
import com.kongmy.srs.core.RequirementModule;

/**
 *
 * @author Kong My
 */
public class ActionResourceConstraintModule extends RequirementModule implements HasMenu {

    @Override
    public List<String> getDependencies() {        
        List<String> dependencies = new ArrayList<>();
        dependencies.add(OntologyModule.class.getName());
        return dependencies;
    }

    @Override
    public Component getMenu(JFrame parent) {
        JMenuItem menuItem = new JMenuItem("Configure Action Resource Constraints");
        menuItem.addActionListener((e) -> {
            
        });
        return menuItem;
    }

    @Override
    protected void UpdateGeneratedRequirements() {
    }
    
}
