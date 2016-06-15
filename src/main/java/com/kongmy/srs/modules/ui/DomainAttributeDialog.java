/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kongmy.srs.modules.ui;

import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import com.kongmy.srs.modules.OntologyModule;

/**
 *
 * @author Kong My
 */
public class DomainAttributeDialog extends javax.swing.JDialog {

    /**
     * Creates new form DomainAttributeDialog
     */
    public DomainAttributeDialog(java.awt.Frame parent, boolean modal, OntologyModule module) {
        super(parent, modal);
        this.module = module;
        this.domainModel = new DefaultComboBoxModel<>();

        List<String> allDomains = module.getAllDomains();
        allDomains.forEach((val) -> domainModel.addElement(val));
        initComponents();
        UpdateData();

        moduleCheckboxPanel.setPanelNames("Included Modules", "Excluded Modules");
        actionCheckboxPanel.setPanelNames("Included Actions", "Excluded Actions");
        actorCheckboxPanel.setPanelNames("Included Actors", "Excluded Actors");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblSelectedModule1 = new javax.swing.JLabel();
        cbxSelectedDomain = new javax.swing.JComboBox();
        tabbedPane = new javax.swing.JTabbedPane();
        panelModules = new javax.swing.JPanel();
        moduleCheckboxPanel = new com.kongmy.srs.ui.CheckboxDualPanel();
        panelActor = new javax.swing.JPanel();
        actorCheckboxPanel = new com.kongmy.srs.ui.CheckboxDualPanel();
        panelAction = new javax.swing.JPanel();
        actionCheckboxPanel = new com.kongmy.srs.ui.CheckboxDualPanel();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configure Domain Attributes");

        lblSelectedModule1.setText("Selected Domain:");

        cbxSelectedDomain.setModel(domainModel);
        cbxSelectedDomain.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSelectedDomainItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout panelModulesLayout = new javax.swing.GroupLayout(panelModules);
        panelModules.setLayout(panelModulesLayout);
        panelModulesLayout.setHorizontalGroup(
            panelModulesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelModulesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(moduleCheckboxPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelModulesLayout.setVerticalGroup(
            panelModulesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelModulesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(moduleCheckboxPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPane.addTab("Modules", panelModules);

        javax.swing.GroupLayout panelActorLayout = new javax.swing.GroupLayout(panelActor);
        panelActor.setLayout(panelActorLayout);
        panelActorLayout.setHorizontalGroup(
            panelActorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelActorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(actorCheckboxPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelActorLayout.setVerticalGroup(
            panelActorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelActorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(actorCheckboxPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPane.addTab("Actors", panelActor);

        javax.swing.GroupLayout panelActionLayout = new javax.swing.GroupLayout(panelAction);
        panelAction.setLayout(panelActionLayout);
        panelActionLayout.setHorizontalGroup(
            panelActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelActionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(actionCheckboxPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelActionLayout.setVerticalGroup(
            panelActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelActionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(actionCheckboxPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPane.addTab("Actions", panelAction);

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSelectedModule1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxSelectedDomain, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblSelectedModule1, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(cbxSelectedDomain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnSave))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxSelectedDomainItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSelectedDomainItemStateChanged
        UpdateData();
    }//GEN-LAST:event_cbxSelectedDomainItemStateChanged

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        module.Save();
        dispose();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        module.Load();
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.kongmy.srs.ui.CheckboxDualPanel actionCheckboxPanel;
    private com.kongmy.srs.ui.CheckboxDualPanel actorCheckboxPanel;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cbxSelectedDomain;
    private javax.swing.JLabel lblSelectedModule1;
    private com.kongmy.srs.ui.CheckboxDualPanel moduleCheckboxPanel;
    private javax.swing.JPanel panelAction;
    private javax.swing.JPanel panelActor;
    private javax.swing.JPanel panelModules;
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables
    private final OntologyModule module;
    private final DefaultComboBoxModel<String> domainModel;

    private void UpdateData() {
        String selectedDomain = cbxSelectedDomain.getSelectedItem().toString();

        List<String> includedModules = module.getModulesFrom(selectedDomain);
        List<String> excludedModules = module.getAllModules();
        excludedModules.removeAll(includedModules);

        ActionListener moduleActionListener = (e) -> {
            JCheckBox cbx = (JCheckBox) e.getSource();
            if (cbx.isSelected()) {
                module.AddModuleTo(selectedDomain, cbx.getText());
            } else {
                module.RemoveModuleFrom(selectedDomain, cbx.getText());
            }
            moduleCheckboxPanel.UpdatePanels();
        };
        moduleCheckboxPanel.PopulateData(includedModules, excludedModules, moduleActionListener, moduleActionListener);
        moduleCheckboxPanel.UpdatePanels();

        List<String> includedActors = module.getActorsFrom(selectedDomain);
        List<String> excludedActors = module.getAllActors();
        excludedActors.removeAll(includedActors);

        ActionListener actorActionListener = (e) -> {
            JCheckBox cbx = (JCheckBox) e.getSource();
            if (cbx.isSelected()) {
                module.AddActorTo(selectedDomain, cbx.getText());
            } else {
                module.RemoveActorFrom(selectedDomain, cbx.getText());
            }
            actorCheckboxPanel.UpdatePanels();
        };
        actorCheckboxPanel.PopulateData(includedActors, excludedActors, actorActionListener, actorActionListener);
        actorCheckboxPanel.UpdatePanels();

        List<String> includedActions = module.getActionsFrom(selectedDomain);
        List<String> excludedActions = module.getAllActions();
        excludedActions.removeAll(includedActions);

        ActionListener actionActionListener = (e) -> {
            JCheckBox cbx = (JCheckBox) e.getSource();
            if (cbx.isSelected()) {
                module.AddActionTo(selectedDomain, cbx.getText());
            } else {
                module.RemoveActionFrom(selectedDomain, cbx.getText());
            }
            actionCheckboxPanel.UpdatePanels();
        };
        actionCheckboxPanel.PopulateData(includedActions, excludedActions, actionActionListener, actionActionListener);
        actionCheckboxPanel.UpdatePanels();
    }

}
