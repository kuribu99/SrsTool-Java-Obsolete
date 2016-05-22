/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kongmy.core;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import kongmy.srs.core.Requirement;

/**
 *
 * @author Owner
 */
public abstract class Module implements ActionListener {

    protected final String moduleName;
    protected final List<Requirement> generatedRequirements;
    
    public Module(String moduleName) {
        this.moduleName = moduleName;
        this.generatedRequirements = new ArrayList<>();
    }
    
    public List<Requirement> getRequirements() {
        this.UpdateGeneratedRequirements();
        return this.generatedRequirements;
    }
    
    protected abstract void UpdateGeneratedRequirements() ;
    
    public String getModuleName() {
        return this.moduleName;
    }
    
}
