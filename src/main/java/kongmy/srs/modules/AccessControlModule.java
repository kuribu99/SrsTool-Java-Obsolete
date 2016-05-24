/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kongmy.srs.modules;

import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
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
    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItem = (JMenuItem) e.getSource();
        
        Container container = menuItem.getParent();
        while(!(container instanceof JFrame))
            container = container.getParent();
        
        AccessControlDialog dlg = new AccessControlDialog((JFrame) container, true, this);
        dlg.setVisible(true);
    }

    @Override
    public String getMenuItemName() {
        return "Configure Access Control";
    }

    @Override
    protected void UpdateGeneratedRequirements() {
    }
    
}
