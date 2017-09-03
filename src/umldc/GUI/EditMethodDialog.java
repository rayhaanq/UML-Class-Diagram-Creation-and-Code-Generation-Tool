
package umldc.GUI;

import umldc.data.ClassObject;
import umldc.data.CMethod;
import umldc.data.Parameter;
import umldc.data.MethodMemento;
import umldc.data.VisibilityType;
import umldc.data.AllTypes;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import static javax.lang.model.SourceVersion.isName;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author rayha
 */
public class EditMethodDialog extends AddMethodDialog{
    
    private int selectedIndex;
    private CopyOnWriteArrayList<CMethod> methods = classOb.getMethods();
    private CMethod selectedMeth;
    private CMethod editedMethod;
    private ArrayList<Parameter> paramsToAdd;
    private MethodMemento _memento;
    
    /**
     *
     * @param parent parent that ran this dialog
     * @param modal true or false if this dialog is modal
     * @param cOb the class object which should contain the method
     * @param selectedIndex index of the selected method
     */
    public EditMethodDialog(Frame parent, boolean modal, ClassObject cOb, int selectedIndex) {
        super(parent, modal, cOb);
        
        editedMethod = new CMethod();
        paramsToAdd = new ArrayList<Parameter>();
        this.selectedIndex = selectedIndex;
        setTitle("Edit Method");
        this.selectedMeth= methods.get(selectedIndex);
        System.out.println(selectedMeth.hashCode());
        System.out.println(editedMethod.hashCode());
        System.out.println("Selected Method Params");
        System.out.println(selectedMeth.getParameters());
        
        
        saveClass(selectedMeth.saveStateToMemento());
        this.addWindowListener(new WindowAdapter() {
            
            public void windowClosed(WindowEvent e) {
                selectedMeth.removeObserver((EditMethodDialog)e.getWindow());
                System.out.println("Before "+selectedMeth.getState());
                selectedMeth.getStateFromMemento(_memento);
                System.out.println("After "+selectedMeth.getState());
                System.out.println("MementoState "+_memento.getState());
            }
        });
        
        
        this.editedMethod.registerObserver(this);
        setInitValues();
         System.out.println(selectedMeth);
        
    }
    
    /**
     *
     * @param evt
     */
    @Override
    public void buttonSubmitActionPerformed(java.awt.event.ActionEvent evt) {                                             
        
        VisibilityType visType = (VisibilityType)getComboVisibility().getSelectedItem();
        boolean isStatic = getCbStatic().isSelected();

        String methName = "";
        String methType = "";
        String comms =  generateComments();
        
        //check if fieldObName is same as any import
        if (isValidObName(getComboType(), getFieldObName()) && isName(getFieldMethName().getText())){
            AllTypes paramTypeSelected = (AllTypes)getComboType().getSelectedItem();
            
            methName = getFieldMethName().getText();
            if (paramTypeSelected.equals(AllTypes.OBJECT)) {
                methType = getFieldObName().getText().replaceAll(" ", "");

            } else {
                methType = paramTypeSelected.getString();
            }
            
            editedMethod.setName(methName);
            editedMethod.setVisibility(visType);
            editedMethod.setIsStatic(isStatic);
            editedMethod.setLibImports(getImportsArrayList());
            editedMethod.setComments(comms);
            editedMethod.setType(methType);

            //CMethod editedMethod = new CMethod(methName, visType, methType, isStatic, getImportsArrayList(), comms);
            //editMethod(methName, visType, methType, meth.getParameters(), isStatic , getImportsArrayList(), comms);
            classOb.editMethod(editedMethod, selectedIndex);
            
            System.out.println("Parameters after editing");
            System.out.println(selectedMeth.getParameters());
            
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
     * @param evt
     */
    @Override
    protected void buttonAddParamActionPerformed(ActionEvent evt) {
        MyJDialog addParamDialog = new AddParamDialog(null, true,  editedMethod);
        addParamDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        addParamDialog.setLocationRelativeTo(this);
        addParamDialog.pack();
        addParamDialog.setVisible(true);
        System.out.println("AddParamActionPerformed");
        System.out.println(selectedMeth.getParameters());
        System.out.println(editedMethod.getParameters());
    }
    
    /**
     *
     * @param evt
     */
    @Override
    protected void buttonEditParamActionPerformed(ActionEvent evt) {
      
        if ( !editedMethod.getParameters().isEmpty() && !paramListView.isSelectionEmpty()) {
            
            int selectedParamIndex = paramListView.getSelectedIndex();
            MyJDialog editParamDialog = new EditParamDialog(null, true, editedMethod, selectedParamIndex);
            editParamDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            editParamDialog.setLocationRelativeTo(this);
            editParamDialog.pack();
            editParamDialog.setVisible(true);
        }
    } 
    
    /**
     *
     * @param evt
     */
    @Override
    protected void buttonRemParamActionPerformed(ActionEvent evt) {
        editedMethod.removeParam(selectedIndex);
    }
     
    /**
     *
     */
    public void setInitValues() {
        //paramListModel = new DefaultListModel();
        getFieldMethName().setText(selectedMeth.getName());
        getComboType().setSelectedItem(selectedMeth.getType());
        
        if(selectedMeth.getType().equals(AllTypes.OBJECT)){
            getFieldObName().setText(getFieldObName().getText());
        }
        
        getComboVisibility().setSelectedItem(selectedMeth.getVisibility());
        getCbStatic().setSelected(selectedMeth.isStatic());
        getTaImports().setText(selectedMeth.importsToString());
        getTaComments().setText(selectedMeth.getComments());
        editedMethod.setParameters(selectedMeth.getParameters());
       
        pack();
    }
    
    /**
     *
     * @param meth the method that should be updated
     */
    @Override
    public void update(CMethod meth) {
        editedMethod = meth;
        
        paramListModel.clear();
        
        CopyOnWriteArrayList<Parameter> parameters = editedMethod.getParameters();

        if (!parameters.isEmpty()) {
            for (Parameter param : parameters) {
                paramListModel.addElement(param);
            }
        }

    }
    
    /**
     *
     * @param state save the current state of a method as a memento
     */
    public void saveClass(MethodMemento state){
      _memento = state;
   }

    /**
     *
     * @return get the state of a method from the memento
     */
    public MethodMemento retrieveClass(){
      return _memento;
   }

}
