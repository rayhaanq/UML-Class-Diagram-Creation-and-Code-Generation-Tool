
package umldc.data;

import umldc.data.Attribute;
import java.awt.Window;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author rayha
 */
public class ClassObject implements IClassSubject{

    private String className;
    private CopyOnWriteArrayList<Attribute> attributes = new CopyOnWriteArrayList<Attribute>();
    private CopyOnWriteArrayList<CMethod> methods = new CopyOnWriteArrayList<CMethod>();
    private ArrayList<IClassObserver> observers;
    
    /**
     *
     */
    public ClassObject(){
        observers = new ArrayList<IClassObserver>();
    }
    
    /**
     *
     * @param cName class name
     * @param attList attribute list
     * @param methList method list
     * @param relationMap class relationships (not yet implemented)
     */
    public ClassObject(String cName, CopyOnWriteArrayList<Attribute> attList, CopyOnWriteArrayList<CMethod> methList, HashMap<ClassObject, RelationType> relationMap){
        
        className = cName;
        attributes = attList;
        methods = methList;
       
    }
    
    /**
     *
     * @param o observer to be registered
     */
    @Override
    public void registerObserver(IClassObserver o) {
        observers.add(o);
    }

    /**
     *
     * @param o observer to be removed
     */
    @Override
    public void removeObserver(IClassObserver o) {
        int index = observers.indexOf(o);
        if(index >= 0){
            observers.remove(index);
        }
    }

    /**
     * notify all observers of any changes
     */
    @Override
    public void notifyObservers() {
        for (IClassObserver observer : observers){
            observer.update(this);
        }
    }

    /**
     *
     * @param varsListView jlist to remove attribute from
     */
    public void removeAttribute(JList varsListView) {
        try {
            int index = varsListView.getSelectedIndex();
            attributes.remove(index);
            notifyObservers();
        } catch (Exception e) {
            //Ignore
        }
    }

    /**
     *
     * @param methsListView jlist to remove method from
     */
    public void removeMethod(JList methsListView) {
        try {
            int index = methsListView.getSelectedIndex();
            methods.remove(index);
            notifyObservers();
        } catch (Exception e) {
            //Ignore
        }
    }
    
    /**
     *
     * @param var attribute to add
     */
    public void addAttribute(Attribute var) {

        attributes.add(var);
        notifyObservers();

    }
    
    /**
     *
     * @param var attribute to check if it exists
     * @return
     */
    public boolean attributeExists(Attribute var) {
        boolean exists = false;

        if (!attributes.isEmpty()) {
            for (Attribute item : attributes) {
                if (item.getName().equals(var.getName())) {
                    exists = true;
                }
            }
        }

        if (exists) {
            JOptionPane.showMessageDialog(null,
                    "Variable name already exists",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
        return exists;
    }
    
    /**
     *
     * @param meth method to add
     */
    public void addMethod(CMethod meth) {
       
            methods.add(meth);
            System.out.println(methods);
            notifyObservers();
            System.out.println("MY METHODS");
            
        
    }
    
    /**
     *
     * @param meth method to check if it exists
     * @return
     */
    public boolean methodExists(CMethod meth) {
        boolean exists = false;

        if (!methods.isEmpty()) {
            for (CMethod item : methods) {
                if (item.getName().equals(meth.getName())) {
                    exists = true;
                }
            }
        }

        if (exists) {
            JOptionPane.showMessageDialog(null,
                    "Method name already exists",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);

        }
        return exists;
    }
    
    /**
     *
     * @param meth method to edit
     * @param selectedIndex selected methods index in jlist
     */
    public void editMethod(CMethod meth, int selectedIndex){
        
        String methName = meth.getName();
        
        boolean exists = false;
        
        for (int count = 0; count < methods.size(); count++) {

            if (count == selectedIndex) {

                try {
                    count++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    //Do nothing
                }
            }
            
            try {
                if (methods.get(count).getName().equals(methName)) {
                    exists = true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                //Do nothing
            }


        }
        if (exists){
                JOptionPane.showMessageDialog(null,
                    "Variable name already exists",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            
            CMethod edit = methods.get(selectedIndex);
            edit.setName(meth.getName());
            edit.setVisibility(meth.getVisibility());
            edit.setType(meth.getType());
            edit.setIsStatic(meth.isStatic());
            edit.setLibImports(meth.getLibImports());
            edit.setComments(meth.getComments());
            
            notifyObservers();
            
        }
    }
    //

    /**
     *
     * @param att attribute to be edited
     * @param selectedIndex selected attributes index in jlist
     */
    public void editAttribute(Attribute att, int selectedIndex){
        
        String varName = att.getName();
        boolean exists = false;
        
        for (int count = 0; count < attributes.size(); count++) {

            if (count == selectedIndex) {

                try {
                    count++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    //Do nothing
                }
            }
            
            try {
                if (attributes.get(count).getName().equals(varName)) {
                    exists = true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                //Do nothing
            }


        }
        if (exists){
                JOptionPane.showMessageDialog(null,
                    "Variable name already exists",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            
            Attribute edit = attributes.get(selectedIndex);
            edit.setName(varName);
            edit.setVisibility(att.getVisibility());
            edit.setType(att.getType());
            edit.setIsArray(att.isArray());
            edit.setIsStatic(att.isStatic());
            edit.setIsReadOnly(att.isReadOnly());
            edit.setLibImports(att.getLibImports());
            edit.setComments(att.getComments());
            notifyObservers();
        }
    
    }
    
    
    
     @Override
    public String toString() {
        return "ClassObject{" + "className=" + className + ", attributes=" + attributes + ", methods=" + methods + '}';
    }
    
    /**
     *
     * @return method list
     */
    public CopyOnWriteArrayList<CMethod> getMethods() {
        return methods;
    }
    
    /**
     *
     * @return class name
     */
    public String getClassName() {
        return className;
    }

    /**
     *
     * @param className clas name to set
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     *
     * @return list of attributes
     */
    public CopyOnWriteArrayList<Attribute> getAttributes() {
        return attributes;
    }

    /**
     *
     * @param attributes set class attributes to this
     */
    public void setAttributes(CopyOnWriteArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }

}
