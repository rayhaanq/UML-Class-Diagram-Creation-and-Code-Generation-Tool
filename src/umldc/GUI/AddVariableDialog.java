
package umldc.GUI;

import umldc.data.ClassObject;
import umldc.data.VisibilityType;
import umldc.data.AllTypes;
import umldc.data.Attribute;
import java.util.ArrayList;
import static javax.lang.model.SourceVersion.isName;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author rayha
 */
public class AddVariableDialog extends MyJDialog {
    
        // Created by NetBeans GUI Builder                    
    private javax.swing.JButton buttonSubmit;
    private javax.swing.JCheckBox cbArray;
    //private javax.swing.JCheckBox cbIgnoreImports;
    private javax.swing.JCheckBox cbRO;
    private javax.swing.JCheckBox cbStatic;
    private javax.swing.JComboBox<String> comboType;
    private javax.swing.JComboBox<String> comboVisibility;
    private javax.swing.JTextField fieldObName;
    private javax.swing.JTextField fieldVarName;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelVis;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel labelComments;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane sPComments;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextArea taComments;
    // End of variables declaration                   

    private ArrayList<String> imports = new ArrayList<String>();
    private String readOnly = "";
    private String staticVar = "";
    
    /**
     *
     * @param parent parent that ran this dialog
     * @param modal true or false if this dialog is modal
     * @param cOb the class object which should contain the attribute
     */
    public AddVariableDialog(java.awt.Frame parent, boolean modal, ClassObject cOb) {
        super(parent, modal, cOb);
        
    }
    
    
   // @Override

    /**
     *
     * @param evt
     */
    public void buttonSubmitActionPerformed(java.awt.event.ActionEvent evt) {                                             
        
        boolean isArray = getCbArray().isSelected();
        boolean isStatic = getCbStatic().isSelected();
        boolean isRO = getCbRO().isSelected();
        VisibilityType visType = (VisibilityType)getComboVisibility().getSelectedItem();
        String varName = "";
        String varType = "";
        String comms =  generateComments();
        
        //check if fieldObName is same as any import
        if (isValidObName(getComboType(), getFieldObName()) && isName(getFieldVarName().getText())){
            AllTypes varTypeSelected = (AllTypes)getComboType().getSelectedItem();
            varName = getFieldVarName().getText();
            if(varTypeSelected.equals(AllTypes.OBJECT)){
                varType = getFieldObName().getText().replaceAll(" ", "");
                
            } else {
                varType = varTypeSelected.getString();
            }
                
            
                //addClassDial.addAttribute(new Attribute(varName, visType, varType, isArray, isStatic,  isRO, imports, comms));
                Attribute att = new Attribute(varName, visType, varType, isArray, isStatic,  isRO, getImports(), comms);
                
                if (!classOb.attributeExists(att)){
                    classOb.addAttribute(new Attribute(varName, visType, varType, isArray, isStatic,  isRO, getImports(), comms));
                    dispose();
                    
                }
            
                
        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid variable name or type",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
        
        
    }
    
    /**
     *
     */
    public void initComponents() {
        labelName = new javax.swing.JLabel();
        labelVis = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelComments = new javax.swing.JLabel();
        fieldVarName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbRO = new javax.swing.JCheckBox();
        buttonSubmit = new javax.swing.JButton();
        sPComments = new javax.swing.JScrollPane();
        taComments = new javax.swing.JTextArea();
//        jTextField3 = new javax.swing.JTextField();
        cbStatic = new javax.swing.JCheckBox();
        cbArray = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        comboType = new javax.swing.JComboBox<>();
        fieldObName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        comboVisibility = new javax.swing.JComboBox<>();

        setTitle("Add Variable");

        labelName.setText("Variable Name: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(labelName, gridBagConstraints);

        labelVis.setText("Visibility: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(labelVis, gridBagConstraints);

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

        cbRO.setText("Read Only");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(cbRO, gridBagConstraints);

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

        cbStatic.setText("Static");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(cbStatic, gridBagConstraints);

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

        comboVisibility.setModel(new DefaultComboBoxModel(VisibilityType.values()));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(comboVisibility, gridBagConstraints);

        pack();
    }
    
    /**
     *
     * @param evt
     */
    protected void comboTypeActionPerformed(java.awt.event.ActionEvent evt) {

        AllTypes selectedType = (AllTypes) comboType.getSelectedItem();

        //If OBJECT is selected then enable the fieldObName field
        if (selectedType.equals(AllTypes.OBJECT)) {
            fieldObName.setEditable(true);
        } else {
            fieldObName.setEditable(false);
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
     * @return cbRO
     */
    public JCheckBox getCbRO() {
        return cbRO;
    }

    /**
     *
     * @return cbStatic
     */
    public JCheckBox getCbStatic() {
        return cbStatic;
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
     * @return comboVisibility
     */
    public JComboBox<String> getComboVisibility() {
        return comboVisibility;
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
     * @return imports
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
     * @return labelVis
     */
    public JLabel getLabelVis() {
        return labelVis;
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
     * @return generates a string of comments based on the users input of static, readonly and comments
     */
    @Override
    public String generateComments() {
        String roStatic = "";
        String comments = taComments.getText().trim();
        if (cbRO.isSelected() && (cbStatic.isSelected() || !comments.isEmpty())) {
            readOnly = "readOnly, ";
        } else if (cbRO.isSelected()) {
            readOnly = "readOnly";
        }

        if (cbStatic.isSelected() && !comments.isEmpty()) {
            staticVar = "static, ";
        } else if (cbStatic.isSelected()) {
            staticVar = "static";
        }

        roStatic = readOnly + staticVar + comments;

        return roStatic;
    }

}
