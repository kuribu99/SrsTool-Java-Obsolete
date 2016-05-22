/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kongmy.srs.modules;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import kongmy.core.Module;
import kongmy.srs.core.Requirement;

/**
 *
 * @author Owner
 */
public class DummyDataModule extends Module {

    public static final String MODULE_NAME = "Dummy Data";
    
    public DummyDataModule() {
        super(MODULE_NAME);
    }

    @Override
    protected void UpdateGeneratedRequirements() {
        String[][] samples = new String[][] {
            {"Login page", "If the student ID and password is correct, the student shall be able to login to the system"},
            {"Login page", "The load time of login page shall less than 3 seconds"},
            {"Login page", "The student shall be able to login if they already met their academic advisor"},
            {"Login page", "If the user did not make request for more than 5 minutes, the system shall time out the user"},
            {"Login page", "The student shall be able to search subject with unit code or unit name"},
            {"Register Subject page", "The student should be able to register subjects"}
        };
        for (String[] arr: samples)
            this.generatedRequirements.add(new Requirement(arr[0], arr[1]));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Hello world", "Dialog", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    
}
