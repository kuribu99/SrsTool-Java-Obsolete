/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kongmy.core;

import java.util.List;

/**
 *
 * @author Kong My
 */
public abstract class Module {

    public String getModuleName() {
        return this.getClass().getName();
    }

    public List<String> getDependencies() {
        return null;
    }

}
