/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kongmy.srs.modules;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import kongmy.core.HasMenuItem;
import kongmy.srs.core.RequirementModule;
import kongmy.srs.modules.ui.AccessControlDialog;

/**
 *
 * @author Kong My
 */
public class AccessControlModule extends RequirementModule implements HasMenuItem {
    
    public AccessControlModule() {
        super();
    }
    
    @Override
    public void onMenuItemClicked(JFrame parent) {
        AccessControlDialog dlg = new AccessControlDialog(parent, true, this);
        dlg.setVisible(true);
    }

    @Override
    public String getMenuItemName() {
        return "Configure Access Control";
    }

    @Override
    protected void UpdateGeneratedRequirements() {
    }

    @Override
    public List<String> getDependencies() {
        List<String> dependencies = new ArrayList<String>();
        dependencies.add(OntologyModule.class.getName());
        return dependencies;
    }
    
}
