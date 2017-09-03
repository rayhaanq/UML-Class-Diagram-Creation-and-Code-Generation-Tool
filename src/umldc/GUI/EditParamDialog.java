
package umldc.GUI;

import umldc.data.CMethod;
import umldc.data.Parameter;
import umldc.data.AllTypes;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import static javax.lang.model.SourceVersion.isName;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author rayha
 */
public class EditParamDialog extends AddParamDialog{
    
    private int selectedIndex;
    private CMethod meth;
    private CopyOnWriteArrayList<Parameter> params;
    private Parameter param;
    private Parameter editedParameter;
    
    /**
     *
     * @param parent
     * @param modal
     * @param meth
     * @param selectedIndex
     */
    public EditParamDialog(Frame parent, boolean modal, CMethod meth , int selectedIndex) {
        super(parent, modal, meth);
        this.meth = meth;
        this.params = this.meth.getParameters();
        this.param = params.get(selectedIndex);
        this.selectedIndex = selectedIndex;
        setInitValues();
       
       editedParameter = new Parameter();
       
        setTitle("Edit Parameter"); 
       
        
        
    }
    
    /**
     *
     * @param evt
     */
    @Override
    public void buttonSubmitActionPerformed(java.awt.event.ActionEvent evt) {                                             
        
        boolean isArray = getCbArray().isSelected();
        String paramName = "";
        String paramType = "";
        String comms =  generateComments();
        
        //check if fieldObName is same as any import
        if (isValidObName(getComboType(), getFieldObName()) && isName(getFieldVarName().getText())){
            AllTypes paramTypeSelected = (AllTypes)getComboType().getSelectedItem();
            
            paramName = getFieldVarName().getText();
            if (paramTypeSelected.equals(AllTypes.OBJECT)) {
                paramType = getFieldObName().getText().replaceAll(" ", "");

            } else {
                paramType = paramTypeSelected.getString();
            }

            
            editedParameter.setName(paramName);
            editedParameter.setType(paramType);
            editedParameter.setIsArray(isArray);
            editedParameter.setLibImports(getImportsArrayList());
            
            
            meth.editParam(editedParameter, selectedIndex);
            dispose();

        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid variable name or type",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
        
        
        
    }
    
    /**
     *
     * @param name name of attribute
     * @param varType type of attribute
     * @param isArray is it an array
     * @param imps the arraylist of imports
     * @param comms a string of comments
     */
    public void editAttribute(String name, String varType, boolean isArray, ArrayList<String> imps, String comms){
        
        String paramName = getFieldVarName().getText();
        boolean exists = false;
        
        
        for (int count = 0; count < params.size(); count++) {

            if (count == selectedIndex) {

                try {
                    count++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    //Do nothing
                }
            }
            
            try {
                if (params.get(count).getName().equals(paramName)) {
                    exists = true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                //Do nothing
            }


        }
        if (exists){
                JOptionPane.showMessageDialog(null,
                    "Parameter name already exists",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            param.setName(paramName);
            param.setType(varType);
            param.setIsArray(isArray);
            param.setLibImports(imps);
            params = meth.getParameters();
        }
    }
    
    /**
     * set the initial values of the parameter that should be edited
     */
    public void setInitValues() {
        getFieldVarName().setText(param.getName());
        getComboType().setSelectedItem(param.getType());
        
        if(param.getType().equals(AllTypes.OBJECT)){
            getFieldObName().setText(getFieldObName().getText());
        }
        
        getCbArray().setSelected(param.isArray());
        getTaImports().setText(param.importsToString());
    }
    
}
