/*
 */
package com.kongmy.srs.modules.ui;

import com.kongmy.srs.modules.ActionResourceConstraintModule.ActionResourceConstraintData;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Kong My
 */
public class ResourceConstraintPanel extends javax.swing.JPanel {

    public interface ResourceConstraintListener {

        public void onCheckBoxStatedChanged(boolean isChecked, String resourceName, String value);

        public void onTextBoxDocumentChanged(String resourceName, String oldValue, String newValue);
        
    }

    /**
     * Creates new form ResourceContraintPanel
     */
    public ResourceConstraintPanel(String resourceName, String constraintValue) {
        this.resourceName = resourceName;
        this.metricValue = constraintValue;

        initComponents();
        tbxValue.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                Update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                Update();
            }

            private void Update() {
                String newValue = tbxValue.getText();
                if (listener != null) {
                    listener.onTextBoxDocumentChanged(resourceName, metricValue, newValue);
                }
                metricValue = newValue;
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbxResource = new javax.swing.JCheckBox();
        tbxValue = new javax.swing.JTextField();

        cbxResource.setText(resourceName);
        cbxResource.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cbxResourceStateChanged(evt);
            }
        });

        tbxValue.setText(metricValue);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(cbxResource, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbxValue, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cbxResource, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(tbxValue, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbxResourceStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cbxResourceStateChanged
        if (listener != null) {
            listener.onCheckBoxStatedChanged(cbxResource.isSelected(), resourceName, metricValue);
        }
    }//GEN-LAST:event_cbxResourceStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbxResource;
    private javax.swing.JTextField tbxValue;
    // End of variables declaration//GEN-END:variables
    private final String resourceName;
    private String metricValue;
    private ResourceConstraintListener listener;

    public void setResourceConstraintListener(ResourceConstraintListener listener) {
        this.listener = listener;
    }

    public void UpdateData(ActionResourceConstraintData data) {
        cbxResource.setSelected(data.isChecked());
        tbxValue.setText(data.getValue());
    }

}
