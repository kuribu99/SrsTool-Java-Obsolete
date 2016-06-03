/*
 */
package kongmy.srs.modules.ui;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import kongmy.srs.modules.OntologyModule;

/**
 *
 * @author Kong My
 */
public class ModifyActionDialog extends javax.swing.JDialog {

    /**
     * Creates new form OntologyAttributesDialog
     */
    public ModifyActionDialog(java.awt.Frame parent, boolean modal, OntologyModule module) {
        super(parent, modal);
        this.module = module;
        this.listModel = new DefaultListModel<>();
        module.getAllActions().forEach((val) -> listModel.addElement(val));
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        list = new javax.swing.JList();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));

        list.setModel(listModel);
        list.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(list);

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancel))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        String oldAction = list.getSelectedValue().toString();
        Object newAction = null;

        while (true) {
            newAction = JOptionPane.showInputDialog(
                    rootPane,
                    "Please enter the name of action",
                    "Add Action",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    null,
                    oldAction);

            if (newAction == null) {
                return;
            } else if (newAction.toString().isEmpty()) {
                JOptionPane.showMessageDialog(
                        rootPane,
                        "Please enter a name",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else if (listModel.contains(newAction)) {
                JOptionPane.showMessageDialog(
                        rootPane,
                        "New action already exist",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else if (newAction.equals(oldAction)) {
                JOptionPane.showMessageDialog(
                        rootPane,
                        "Please enter new value for action",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                listModel.removeElement(oldAction);
                listModel.addElement(newAction.toString().replaceAll("[\\W+]", " ").trim());
                module.Rename(oldAction, newAction.toString());
                return;
            }
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        list.getSelectedValuesList()
                .forEach((val) -> {
                    module.RemoveAction(val.toString());
                    listModel.removeElement(val);
                });
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        module.Load();
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        module.Save();
        dispose();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        String newAction = null;

        while (true) {
            newAction = JOptionPane.showInputDialog(
                    rootPane,
                    "Please enter the name of action",
                    "Add Action",
                    JOptionPane.QUESTION_MESSAGE);

            if (newAction == null) {
                return;
            } else if (newAction.isEmpty()) {
                JOptionPane.showMessageDialog(
                        rootPane,
                        "Please enter a name",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else if (listModel.contains(newAction)) {
                JOptionPane.showMessageDialog(
                        rootPane,
                        "New action already exist",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                listModel.addElement(newAction.replaceAll("[\\W+]", " ").trim());
                module.AddNewAction(newAction);
                return;
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void listValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listValueChanged
        int selected = list.getSelectedValuesList().size();
        btnEdit.setEnabled(selected == 1);
        btnDelete.setEnabled(selected > 0);
    }//GEN-LAST:event_listValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList list;
    // End of variables declaration//GEN-END:variables
    private final OntologyModule module;
    private DefaultListModel<String> listModel;
}
