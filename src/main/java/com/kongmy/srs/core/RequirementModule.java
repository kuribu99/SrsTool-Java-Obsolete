/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kongmy.srs.core;

import java.util.ArrayList;
import java.util.List;
import com.kongmy.core.Module;

/**
 *
 * @author Kong My
 */
public abstract class RequirementModule extends Module {

    protected final List<Requirement> generatedRequirements;

    public RequirementModule() {
        super();
        this.generatedRequirements = new ArrayList<>();
    }

    public List<Requirement> getRequirements() {
        this.UpdateGeneratedRequirements();
        return this.generatedRequirements;
    }

    protected abstract void UpdateGeneratedRequirements();

}
