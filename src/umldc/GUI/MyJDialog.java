
package umldc.GUI;

import umldc.data.CMethod;
import umldc.data.ClassObject;
import umldc.data.AllTypes;
import java.awt.Frame;
import java.util.ArrayList;
import static javax.lang.model.SourceVersion.isName;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author rayha
 */
public abstract class MyJDialog extends JDialog{
    
    private boolean ignoreImports = false;
    private ArrayList<String> imports = new ArrayList<String>();
    private javax.swing.JTextArea taImports;
    private javax.swing.JLabel labelImports;
    private javax.swing.JScrollPane sPImports;
    private javax.swing.JCheckBox cbIgnoreImports;

    /**
     *
     */
    protected java.awt.GridBagConstraints gridBagConstraints;

    /**
     *
     */
    protected ClassObject classOb;

    /**
     *
     */
    protected CMethod meth;
    
    /**
     *
     * @param parent parent that ran this dialog
     * @param modal true or false if this dialog is modal
     * @param cOb the class object which is being added to or edited
     */
    public MyJDialog(Frame parent, boolean modal, ClassObject cOb) {
        super(parent, modal);
        this.classOb = cOb;
        setModal(true);
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());
        
        initComponents();
        initImport();
    }
    
    /**
     *
     * @param parent parent that ran this dialog
     * @param modal true or false if this dialog is modal
     */
    public MyJDialog(Frame parent, boolean modal) {
        super(parent, modal);
        setModal(true);
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());
        
        initComponents();
        initImport();
    }
    
    /**
     *
     * @param parent parent that ran this dialog
     * @param modal true or false if this dialog is modal
     * @param meth method to be added to or edited
     */
    public MyJDialog(Frame parent, boolean modal, CMethod meth) {
        super(parent, modal);
        this.meth = meth;
        setModal(true);
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());
        
        initComponents();
        initImport();
    }
    private void initImport(){

        sPImports = new javax.swing.JScrollPane();
        taImports = new javax.swing.JTextArea();
        labelImports = new javax.swing.JLabel();
        cbIgnoreImports = new javax.swing.JCheckBox();
        

        
        labelImports.setText("Imports: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        getContentPane().add(labelImports, gridBagConstraints);
        
        taImports.setColumns(20);
        taImports.setRows(5);
        taImports.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                taImportsFocusLost(evt);
            }
        });
        sPImports.setViewportView(taImports);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(sPImports, gridBagConstraints);
        
        cbIgnoreImports.setText("Ignore Imports");
        cbIgnoreImports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIgnoreImportsActionPerformed(evt);
            }

            
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        getContentPane().add(cbIgnoreImports, gridBagConstraints);
        
    }
    
    
    
    private void taImportsFocusLost(java.awt.event.FocusEvent evt) {
        imports.clear();
        imports = getImportsArrayList();
    }

    private void cbIgnoreImportsActionPerformed(java.awt.event.ActionEvent evt) {
        if (cbIgnoreImports.isSelected()) {
            ignoreImports = true;
        } else if (!cbIgnoreImports.isSelected()) {
            ignoreImports = false;
        }
    } 

    /**
     * how to initialize components
     */
    public abstract void initComponents();
    
    /**
     *
     * @return generate comments based on input
     */
    public abstract String generateComments();

    
    
    private String removeParams (String str){
        String removed = str.replaceAll("((\\s*)(<(.*)>))$", "");;
        return removed;
    }
  
    private boolean hasValidObParTypes(JTextField fieldObName) {
        boolean valid = false;
        String obNameNoWhitespace = fieldObName.getText().replaceAll(" ", "");
        
        
        if (obNameNoWhitespace.matches("(\\w+)(\\s*)((<((\\s*)(\\w+)(\\s*))(,(\\s*)(\\w+)(\\s*))*>)?)$")) {// old (\\w+)((<(.+)(,?(.+)>))?)$
            valid = true;
        }

        return valid;
    }
    
    //Checks if the inputted object name is a valid object

    /**
     * checks if the object name is valid or not based on a regular expression
     * @param comboType the JComboBox that contains the type of the attribute/method
     * @param fieldObName the Object name field
     * @return true of false based on whether or not the object name is correct
     */
    protected boolean isValidObName(JComboBox comboType, JTextField fieldObName){
        boolean valid = true;
        AllTypes selected = (AllTypes)comboType.getSelectedItem();
        //String removedParams = fieldObName.getText().replaceAll("(<(.*)>)$", "");
        String removedParams = removeParams(fieldObName.getText());
        
        //If the selected Item in the combolist is OBJECT, do the following checks
        if (selected.equals(AllTypes.OBJECT)) {
            
            
            
            if (isName(removeParams(fieldObName.getText())) && hasValidObParTypes(fieldObName)){ //  
                //If ignore imports is not checked
                
                if (ignoreImports) {

                    if (isName(removedParams)) {
                        valid = true;
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Please enter a valid object name",
                                "Invalid object name",
                                JOptionPane.WARNING_MESSAGE);
                        valid = false;
                    }

                } else {


                    //If the text area Imports is not empty
                    if(taImports.getText().trim().length() != 0){

                        //iterate through each item in the imports ArrayList
                        for (String className : imports) {
                            //Check if the Object name is a valid package name any of the imports
                            if (className.matches("((.*)(\\.))?" + fieldObName.getText())) {
                                valid = true;

                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Please check that you have an import for the object name or check the \"Ignore Imports\" check box",
                                        "Warning",
                                        JOptionPane.WARNING_MESSAGE);
                                valid = false;
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,
                            "Imports cannot be empty",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);
                        valid = false;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null,
                            "Invalid Parameter Types",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);
                valid = false;
            }
        }
        return valid;
    }
    
    /**
     *
     * @return ArraList of imports
     */
    protected ArrayList<String> getImportsArrayList(){
        imports.clear();
        ArrayList<String> impsArray = new ArrayList<String>();
        int start = 0;
        int end;
        
        String taImportsText = taImports.getText();
        
        for (int index = 0; index< taImportsText.length(); index++){
            char character = taImportsText.charAt(index);
            if (character == '\n' || taImportsText.length() - 1 == index){
                end = index+1;
                String sub = taImportsText.substring(start, end);
                if (isName(sub.trim())) {//MyUtils.isFullyQualifiedClassname(sub.trim())
                    imports.add(sub.trim());
                    
                }
                start = index;
            }
        }
        return impsArray;
    }

    /**
     *
     * @return taImports
     */
    public JTextArea getTaImports() {
        return taImports;
    }

    /**
     *
     * @param ignoreImports true if to set ignoreImports as true
     */
    public void setIgnoreImports(boolean ignoreImports) {
        this.ignoreImports = ignoreImports;
    }

   

    
    
    
    
}
