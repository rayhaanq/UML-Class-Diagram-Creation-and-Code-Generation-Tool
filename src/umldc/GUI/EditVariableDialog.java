
package umldc.GUI;

import umldc.data.ClassObject;
import umldc.data.VisibilityType;
import umldc.data.AllTypes;
import umldc.data.Attribute;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import static javax.lang.model.SourceVersion.isName;
import javax.swing.JOptionPane;

/**
 *
 * @author rayha
 */
public class EditVariableDialog extends AddVariableDialog{
    
    private int selectedIndex;
    private CopyOnWriteArrayList<Attribute> atts = classOb.getAttributes();
    private Attribute att;// = atts.get(selectedIndex);
    
    /**
     *
     * @param parent parent that ran this dialog
     * @param modal true or false if this dialog is modal
     * @param cOb the class object which should contain the attribute
     * @param selectedIndex index of the selected attribute
     */
    public EditVariableDialog(java.awt.Frame parent, boolean modal, ClassObject cOb, int selectedIndex) {
        super(parent, modal, cOb);
        this.selectedIndex = selectedIndex;
        att = atts.get(selectedIndex);
        setInitValues();
        
    }
    
    /**
     *
     * @param evt
     */
    @Override
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
                
                Attribute editedAtt = new Attribute(varName, visType, varType, isArray, isStatic,  isRO, getImportsArrayList(), comms);
                classOb.editAttribute(editedAtt, selectedIndex);
                dispose();
                
        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid variable name or type",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
        
        
    }
 
    /**
     * sets the initial values of each field based on the selected attribute
     */
    public void setInitValues() {
        getFieldVarName().setText(att.getName());
        getComboType().setSelectedItem(att.getType());
        
        if(att.getType().equals(AllTypes.OBJECT)){
            getFieldObName().setText(getFieldObName().getText());
        }
        
        getCbArray().setSelected(att.isArray());
        getComboVisibility().setSelectedItem(att.getVisibility());
        getCbRO().setSelected(att.isReadOnly());
        getCbStatic().setSelected(att.isStatic());
        getTaImports().setText(att.importsToString());
        getTaComments().setText(att.getComments());
    }
    
}
