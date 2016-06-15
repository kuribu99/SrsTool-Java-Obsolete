/*
 */
package com.kongmy.srs.modules;

import com.kongmy.core.Application;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import com.kongmy.core.HasMenu;
import com.kongmy.srs.core.Requirement;
import com.kongmy.srs.core.RequirementModule;
import com.kongmy.srs.modules.ui.ActionResourceConstraintDialog;
import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author Kong My
 */
public class ActionResourceConstraintModule extends RequirementModule implements HasMenu {

    public static class ActionResourceConstraintData implements Serializable {

        private final String metric;
        private String value;
        private boolean checked;

        public ActionResourceConstraintData(String metric, String value) {
            this.metric = metric;
            this.value = value;
            this.checked = false;
        }

        public String getMetric() {
            return metric;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        @Override
        public ActionResourceConstraintData clone() {
            ActionResourceConstraintData clone = new ActionResourceConstraintData(this.metric, this.value);
            clone.checked = this.checked;
            return clone;
        }

        @Override
        public String toString() {
            return String.format("ActionResourceConstraintData = { metric = %s, value = %s, checked = %s",
                    metric, value, String.valueOf(checked));
        }
    }

    private static final String MODULE_NAME = "Action Resource Constraint";
    public static String DATA_ACTION_RESOURCE_CONSTRAINT_MAP = "resourceMap";
    private static final String REQUIREMENT_BOILERPLATE = "The <metric> of <action> action must be <value>";

    @Override
    public List<String> getDependencies() {
        List<String> dependencies = new ArrayList<>();
        dependencies.add(OntologyModule.class.getName());
        return dependencies;
    }

    @Override
    public Component getMenu(JFrame parent) {
        JMenuItem menuItem = new JMenuItem("Configure Action Resource Constraints");
        menuItem.addActionListener((e) -> {
            OntologyModule module = (OntologyModule) Application.getInstance().getModule(OntologyModule.class.getName());
            ActionResourceConstraintDialog dlg = new ActionResourceConstraintDialog(parent, true, module);
            dlg.setVisible(true);
        });
        return menuItem;
    }

    @Override
    protected void UpdateGeneratedRequirements() {
        this.generatedRequirements.clear();

        Map<String, Map<String, ActionResourceConstraintData>> dataMap
                = (Map<String, Map<String, ActionResourceConstraintData>>) Application.getInstance()
                .getDataContext().getData().get(DATA_ACTION_RESOURCE_CONSTRAINT_MAP);

        if (dataMap != null) {
            dataMap.forEach((action, map) -> {
                String boilerplate = REQUIREMENT_BOILERPLATE.replace("<action>", action);
                map.forEach((metric, data) -> {
                    if (data.isChecked()) {
                        this.generatedRequirements.add(new Requirement(
                                MODULE_NAME,
                                boilerplate.replace("<metric>", metric).replace("<value>", data.getValue())));
                    }
                });
            });
        }
    }

}
