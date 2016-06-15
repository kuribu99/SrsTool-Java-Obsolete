/*
 */
package com.kongmy.srs.modules;

import com.kongmy.core.Application;
import com.kongmy.core.DataContext;
import com.kongmy.core.HasMenu;
import com.kongmy.core.Module;
import com.kongmy.srs.SrsApplication;
import com.kongmy.srs.core.Requirement;
import com.kongmy.srs.core.RequirementModule;
import com.kongmy.srs.ui.MainFrame;
import com.kongmy.srs.ui.ModulePanel;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Kong My
 */
public class RefreshGeneratedRequirementModule extends Module implements HasMenu {

    @Override
    public Component getMenu(JFrame parent) {
        JMenuItem menuItem = new JMenuItem("Refresh Generated Requirements");
        menuItem.addActionListener((e) -> {
            DataContext dataContext = Application.getInstance().getDataContext();
            Map<String, List<Requirement>> requirementByModules = (Map<String, List<Requirement>>) dataContext.getData()
                    .getOrDefault(SrsApplication.DATA_GENERATED_REQUIREMENTS, new HashMap<>());

            Application.getInstance().getModules().values().stream()
                    .filter((module) -> module instanceof RequirementModule)
                    .map((module) -> (RequirementModule) module)
                    .forEach((module) -> {
                        module.getRequirements().forEach((requirement) -> {
                            if (!requirementByModules.containsKey(requirement.getModule())) {
                                requirementByModules.put(requirement.getModule(), new ArrayList<>());
                            }
                            requirementByModules.get(requirement.getModule()).add(requirement);
                        });
                    });

            dataContext.getData().put(SrsApplication.DATA_GENERATED_REQUIREMENTS, requirementByModules);
            dataContext.setSaved(false);
            ((MainFrame) parent).UpdateRequirements();

            JOptionPane.showMessageDialog(
                    parent,
                    "Requirements refreshed succesfully",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        return menuItem;
    }

}
