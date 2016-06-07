/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kongmy.srs.core;

/**
 *
 * @author Kong My
 */
public class Requirement {

    private String module;
    private String requirement;

    public Requirement(String module, String requirement) {
        this.module = (module != null && !module.isEmpty()) ? module : "Overall";
        this.requirement = requirement;
    }

    public String getModule() {
        return this.module;
    }

    public String getRequirement() {
        return this.requirement;
    }

    @Override
    public String toString() {
        return requirement;
    }

}
