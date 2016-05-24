/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kongmy.core;

import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author Kong My
 */
public interface HasMenuItem {
    
    public String getMenuItemName();
    public void onMenuItemClicked(JFrame parent);
    
}
