/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kongmy.srs.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import kongmy.srs.core.Requirement;
import kongmy.core.Application;
import kongmy.core.HasMenu;
import kongmy.srs.SrsApplication;
import kongmy.srs.core.RequirementModule;

/**
 *
 * @author Kong My
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        requirementByModules = new HashMap<>();
        initComponents();

        Application.getInstance().getModules().values().forEach((module) -> {
            if (module instanceof HasMenu) {
                menuModules.add(((HasMenu) module).getMenu(this));
            }
        });

        UpdateRequirements();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        actionPanel = new javax.swing.JPanel();
        lblSelectedDomainAndModule = new javax.swing.JLabel();
        tbxSelectedDomainAndModule = new javax.swing.JTextField();
        lblChange = new javax.swing.JLabel();
        btnAddFunctionalRequirement = new javax.swing.JButton();
        btnAddNonFunctionalRequirement = new javax.swing.JButton();
        moduleTabbedPane = new javax.swing.JTabbedPane();
        menuBar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuItemNewSRS = new javax.swing.JMenuItem();
        menuItemOpenSRS = new javax.swing.JMenuItem();
        separatorOpenClose = new javax.swing.JPopupMenu.Separator();
        menuItemSaveSRS = new javax.swing.JMenuItem();
        menuItemExportSRS = new javax.swing.JMenuItem();
        menuItemCloseSRS = new javax.swing.JMenuItem();
        separatorClose = new javax.swing.JPopupMenu.Separator();
        menuItemRestart = new javax.swing.JMenuItem();
        menuItemExit = new javax.swing.JMenuItem();
        menuOntology = new javax.swing.JMenu();
        menuItemLoadOntology = new javax.swing.JMenuItem();
        menuItemModifyDomain = new javax.swing.JMenuItem();
        menuItemModifyMetrics = new javax.swing.JMenuItem();
        menuBoilerplate = new javax.swing.JMenu();
        menuItemModifyBoilerplates = new javax.swing.JMenuItem();
        menuItemImportBoilerplates = new javax.swing.JMenuItem();
        menuItemExportBoilerplates = new javax.swing.JMenuItem();
        menuModules = new javax.swing.JMenu();
        menuSettings = new javax.swing.JMenu();
        menuItemWriteConfigurationFile = new javax.swing.JMenuItem();
        menuItemWriteDefaultConfigurationFile = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Software Requirement Specification Tool");

        actionPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblSelectedDomainAndModule.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSelectedDomainAndModule.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSelectedDomainAndModule.setText("Selected Domain and Module");

        tbxSelectedDomainAndModule.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbxSelectedDomainAndModule.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tbxSelectedDomainAndModule.setText("Subject Registration System/Login page");
        tbxSelectedDomainAndModule.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbxSelectedDomainAndModuleMouseClicked(evt);
            }
        });

        lblChange.setForeground(new java.awt.Color(0, 0, 255));
        lblChange.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblChange.setText("Click to change");
        lblChange.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblChangeMouseClicked(evt);
            }
        });

        btnAddFunctionalRequirement.setText("Add Functional Requirement");
        btnAddFunctionalRequirement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFunctionalRequirementActionPerformed(evt);
            }
        });

        btnAddNonFunctionalRequirement.setText("Add Non-Functional Requirement");
        btnAddNonFunctionalRequirement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNonFunctionalRequirementActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout actionPanelLayout = new javax.swing.GroupLayout(actionPanel);
        actionPanel.setLayout(actionPanelLayout);
        actionPanelLayout.setHorizontalGroup(
            actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tbxSelectedDomainAndModule)
                    .addGroup(actionPanelLayout.createSequentialGroup()
                        .addComponent(lblSelectedDomainAndModule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblChange, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddNonFunctionalRequirement, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddFunctionalRequirement, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        actionPanelLayout.setVerticalGroup(
            actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnAddFunctionalRequirement, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblSelectedDomainAndModule))
                    .addComponent(lblChange, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbxSelectedDomainAndModule, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddNonFunctionalRequirement))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        moduleTabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        moduleTabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        menuBar.setToolTipText("");
        menuBar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        menuFile.setText("File");

        menuItemNewSRS.setText("New SRS");
        menuFile.add(menuItemNewSRS);

        menuItemOpenSRS.setText("Open SRS");
        menuFile.add(menuItemOpenSRS);
        menuFile.add(separatorOpenClose);

        menuItemSaveSRS.setText("Save SRS");
        menuFile.add(menuItemSaveSRS);

        menuItemExportSRS.setText("Export SRS");
        menuFile.add(menuItemExportSRS);

        menuItemCloseSRS.setText("Close");
        menuFile.add(menuItemCloseSRS);
        menuFile.add(separatorClose);

        menuItemRestart.setText("Restart");
        menuItemRestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRestartActionPerformed(evt);
            }
        });
        menuFile.add(menuItemRestart);

        menuItemExit.setText("Exit");
        menuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemExitActionPerformed(evt);
            }
        });
        menuFile.add(menuItemExit);

        menuBar.add(menuFile);

        menuOntology.setText("Ontology");

        menuItemLoadOntology.setText("Load ontology file");
        menuOntology.add(menuItemLoadOntology);

        menuItemModifyDomain.setText("Modify domain");
        menuItemModifyDomain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemModifyDomainActionPerformed(evt);
            }
        });
        menuOntology.add(menuItemModifyDomain);

        menuItemModifyMetrics.setText("Modify quality metrics");
        menuItemModifyMetrics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemModifyMetricsActionPerformed(evt);
            }
        });
        menuOntology.add(menuItemModifyMetrics);

        menuBar.add(menuOntology);

        menuBoilerplate.setText("Boilerplate");

        menuItemModifyBoilerplates.setText("Modify boilerplate templates");
        menuBoilerplate.add(menuItemModifyBoilerplates);

        menuItemImportBoilerplates.setText("Import boilerplate templates");
        menuBoilerplate.add(menuItemImportBoilerplates);

        menuItemExportBoilerplates.setText("Export boilerplate templates");
        menuItemExportBoilerplates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemExportBoilerplatesActionPerformed(evt);
            }
        });
        menuBoilerplate.add(menuItemExportBoilerplates);

        menuBar.add(menuBoilerplate);

        menuModules.setText("Modules");
        menuBar.add(menuModules);

        menuSettings.setText("Settings");

        menuItemWriteConfigurationFile.setText("Write configuration file");
        menuItemWriteConfigurationFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemWriteConfigurationFileActionPerformed(evt);
            }
        });
        menuSettings.add(menuItemWriteConfigurationFile);

        menuItemWriteDefaultConfigurationFile.setText("Write default configuration file");
        menuItemWriteDefaultConfigurationFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemWriteDefaultConfigurationFileActionPerformed(evt);
            }
        });
        menuSettings.add(menuItemWriteDefaultConfigurationFile);

        menuBar.add(menuSettings);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(moduleTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
                    .addComponent(actionPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(actionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(moduleTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbxSelectedDomainAndModuleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbxSelectedDomainAndModuleMouseClicked
        SelectModuleDialog dlg = new SelectModuleDialog(this, true);
        dlg.setVisible(true);
    }//GEN-LAST:event_tbxSelectedDomainAndModuleMouseClicked

    private void btnAddFunctionalRequirementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFunctionalRequirementActionPerformed
        AddFunctionalRequirementDialog dlg = new AddFunctionalRequirementDialog(this, true);
        dlg.setVisible(true);
    }//GEN-LAST:event_btnAddFunctionalRequirementActionPerformed

    private void btnAddNonFunctionalRequirementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNonFunctionalRequirementActionPerformed
        AddNonFunctionalRequirementDialog dlg = new AddNonFunctionalRequirementDialog(this, true);
        dlg.setVisible(true);
    }//GEN-LAST:event_btnAddNonFunctionalRequirementActionPerformed

    private void menuItemExportBoilerplatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemExportBoilerplatesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuItemExportBoilerplatesActionPerformed

    private void menuItemModifyMetricsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemModifyMetricsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuItemModifyMetricsActionPerformed

    private void lblChangeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChangeMouseClicked
        SelectModuleDialog dlg = new SelectModuleDialog(this, true);
        dlg.setVisible(true);
    }//GEN-LAST:event_lblChangeMouseClicked

    private void menuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemExitActionPerformed
        dispose();
    }//GEN-LAST:event_menuItemExitActionPerformed

    private void menuItemModifyDomainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemModifyDomainActionPerformed
        ModifyDomainDialog dlg = new ModifyDomainDialog(this, true);
        dlg.setVisible(true);
    }//GEN-LAST:event_menuItemModifyDomainActionPerformed

    private void menuItemWriteConfigurationFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemWriteConfigurationFileActionPerformed
        try {
            Application.getInstance().getConfiguration().WriteConfigurationToFile();
            JOptionPane.showMessageDialog(this,
                    "Configuration file created successfully",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Error saving configuration",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_menuItemWriteConfigurationFileActionPerformed

    private void menuItemWriteDefaultConfigurationFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemWriteDefaultConfigurationFileActionPerformed
        try {
            Application.getInstance().getConfiguration().LoadDefaultConfiguration();
            Application.getInstance().getConfiguration().WriteConfigurationToFile();
            JOptionPane.showMessageDialog(this,
                    "Configuration file created successfully",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Error saving configuration",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_menuItemWriteDefaultConfigurationFileActionPerformed

    private void menuItemRestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRestartActionPerformed
        new Thread(() -> {
            SrsApplication.bootstrap().run();
        }).start();
        this.dispose();
    }//GEN-LAST:event_menuItemRestartActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionPanel;
    private javax.swing.JButton btnAddFunctionalRequirement;
    private javax.swing.JButton btnAddNonFunctionalRequirement;
    private javax.swing.JLabel lblChange;
    private javax.swing.JLabel lblSelectedDomainAndModule;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuBoilerplate;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenuItem menuItemCloseSRS;
    private javax.swing.JMenuItem menuItemExit;
    private javax.swing.JMenuItem menuItemExportBoilerplates;
    private javax.swing.JMenuItem menuItemExportSRS;
    private javax.swing.JMenuItem menuItemImportBoilerplates;
    private javax.swing.JMenuItem menuItemLoadOntology;
    private javax.swing.JMenuItem menuItemModifyBoilerplates;
    private javax.swing.JMenuItem menuItemModifyDomain;
    private javax.swing.JMenuItem menuItemModifyMetrics;
    private javax.swing.JMenuItem menuItemNewSRS;
    private javax.swing.JMenuItem menuItemOpenSRS;
    private javax.swing.JMenuItem menuItemRestart;
    private javax.swing.JMenuItem menuItemSaveSRS;
    private javax.swing.JMenuItem menuItemWriteConfigurationFile;
    private javax.swing.JMenuItem menuItemWriteDefaultConfigurationFile;
    private javax.swing.JMenu menuModules;
    private javax.swing.JMenu menuOntology;
    private javax.swing.JMenu menuSettings;
    private javax.swing.JTabbedPane moduleTabbedPane;
    private javax.swing.JPopupMenu.Separator separatorClose;
    private javax.swing.JPopupMenu.Separator separatorOpenClose;
    private javax.swing.JTextField tbxSelectedDomainAndModule;
    // End of variables declaration//GEN-END:variables
    private final Map<String, List<Requirement>> requirementByModules;

    private void AddModuleTab(ModulePanel panel) {
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);
        moduleTabbedPane.addTab(panel.getModuleName(), scrollPane);
    }

    private void UpdateRequirements() {
        requirementByModules.clear();
        moduleTabbedPane.removeAll();

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

        requirementByModules.entrySet().forEach((entry) -> {
            AddModuleTab(new ModulePanel(entry.getKey(), entry.getValue()));
        });
    }
}
