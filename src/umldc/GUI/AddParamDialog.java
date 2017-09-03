
package umldc.GUI;

import umldc.data.CMethod;
import umldc.data.Parameter;
import umldc.data.AllTypes;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import static javax.lang.model.SourceVersion.isName;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author rayha
 */
public class AddParamDialog extends MyJDialog{
    
            // Created by NetBeans GUI Builder                    
    private javax.swing.JButton buttonSubmit;
    private javax.swing.JCheckBox cbArray;
    private javax.swing.JComboBox<String> comboType;
    private javax.swing.JTextField fieldObName;
    private javax.swing.JTextField fieldVarName;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel labelComments;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane sPComments;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextArea taComments;
    // End of variables declaration             

    private CMethod methh;
    private ArrayList<String> imports = new ArrayList<String>();
    private Parameter param;
    
    /**
     *
     * @param parent parent that ran this dialog
     * @param modal true or false if this dialog is modal
     * @param meth the method which should contain the parameter
     */
    public AddParamDialog(Frame parent, boolean modal, CMethod meth ) {
        super(parent, modal);
        
        this.methh = meth;
        System.out.println(meth.hashCode());
        this.setTitle("Add Parameter");
        
        
        
        
        labelName.setText("Parameter Name: ");
    }
    
    /**
     *
     */
    @Override
        public void initComponents() {

        labelName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelComments = new javax.swing.JLabel();
        fieldVarName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        buttonSubmit = new javax.swing.JButton();
        sPComments = new javax.swing.JScrollPane();
        taComments = new javax.swing.JTextArea();
        cbArray = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        comboType = new javax.swing.JComboBox<>();
        fieldObName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setTitle("Add Variable");

        labelName.setText("Variable Name: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(labelName, gridBagConstraints);


        jLabel3.setText("Object Name: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(jLabel3, gridBagConstraints);

        labelComments.setText("Additional Comments: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        getContentPane().add(labelComments, gridBagConstraints);

        fieldVarName.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(fieldVarName, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(jLabel6, gridBagConstraints);


        buttonSubmit.setText("Submit");
        buttonSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSubmitActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(buttonSubmit, gridBagConstraints);

        taComments.setColumns(20);
        taComments.setRows(5);
        sPComments.setViewportView(taComments);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(sPComments, gridBagConstraints);


        cbArray.setText("Array");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(cbArray, gridBagConstraints);

        jLabel4.setText("Type: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(jLabel4, gridBagConstraints);

        comboType.setModel(new DefaultComboBoxModel(AllTypes.valuesNoVoid()));
        comboType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTypeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(comboType, gridBagConstraints);

        this.fieldObName.setEditable(false);
        fieldObName.setToolTipText("Object name");
        fieldObName.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        getContentPane().add(fieldObName, gridBagConstraints);

        pack();
    }
        
        private void comboTypeActionPerformed(java.awt.event.ActionEvent evt) {

        AllTypes selectedType = (AllTypes) comboType.getSelectedItem();

        if (selectedType.equals(AllTypes.OBJECT)) {
            fieldObName.setEditable(true);
        } else {
            fieldObName.setEditable(false);
        }
    }

    //@Override

    /**
     *
     * @param evt
     */
    public void buttonSubmitActionPerformed(ActionEvent evt) {
        
        boolean isArray = getCbArray().isSelected();
        String paramName = "";
        String paramType = "";
        
        //check if fieldObName is same as any import
        if (isValidObName(getComboType(), getFieldObName()) && isName(getFieldVarName().getText())){
            AllTypes paramTypeSelected = (AllTypes)getComboType().getSelectedItem();
            paramName = getFieldVarName().getText();
            if(paramTypeSelected.equals(AllTypes.OBJECT)){
                paramType = getFieldObName().getText().replaceAll(" ", "");
                
            } else {
                paramType = paramTypeSelected.getString();
            }
                
            
                param = new Parameter(paramName, paramType, isArray, getImports());
                if (this.methh.isValidParam(param)){
                   param.setName(paramName);
                   param.setType(paramType);
                   param.setIsArray(isArray);
                   param.setLibImports(getImportsArrayList());
                   this.methh.addParam(param);
                   System.out.println("AddParameterDialog method parameters");
                   System.out.println(methh.getParameters());
                    dispose();
                    
                }
            
                
        } else {
            JOptionPane.showMessageDialog(null,
                    "Invalid parameter name or type",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
        
        
    }
    
    /**
     *
     * @return cbArray
     */
    public JCheckBox getCbArray() {
        return cbArray;
    }

    /**
     *
     * @return comboType
     */
    public JComboBox<String> getComboType() {
        return comboType;
    }

    /**
     *
     * @return fieldObName
     */
    public JTextField getFieldObName() {
        return fieldObName;
    }

    /**
     *
     * @return fieldVarName
     */
    public JTextField getFieldVarName() {
        return fieldVarName;
    }

    /**
     *
     * @return taComments
     */
    public JTextArea getTaComments() {
        return taComments;
    }

    /**
     *
     * @return ArrayList of imports entered
     */
    public ArrayList<String> getImports() {
        return imports;
    }

    /**
     *
     * @return labelName
     */
    public JLabel getLabelName() {
        return labelName;
    }

    /**
     *
     * @return labelComments
     */
    public JLabel getLabelComments() {
        return labelComments;
    }

    /**
     *
     * @return currently not implemented
     */
    @Override
    public String generateComments() {
        return "";
    }
    
    
    
}
