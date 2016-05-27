/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kongmy.srs.modules;

import java.util.List;
import kongmy.srs.core.Requirement;
import kongmy.srs.core.RequirementModule;

/**
 *
 * @author Kong My
 */
public class DummyDataModule extends RequirementModule {
    
    public DummyDataModule() {
        super();
    }

    @Override
    protected void UpdateGeneratedRequirements() {
        this.generatedRequirements.clear();
        
        String[][] samples = new String[][] {
            {"Login page", "If the student ID and password is correct, the student shall be able to login to the system"},
            {"Login page", "The load time of login page shall less than 3 seconds"},
            {"Login page", "The student shall be able to login if they already met their academic advisor"},
            {"Login page", "If the user did not make request for more than 5 minutes, the system shall time out the user"},
            {"Login page", "The student shall be able to search subject with unit code or unit name"},
            {"Register Subject page", "The student should be able to register subjects"}
        };
        for(String[] arr: samples)
            this.generatedRequirements.add(new Requirement(arr[0], arr[1]));
    }    

    @Override
    public List<String> getDependencies() {
        return null;
    }
    
}
