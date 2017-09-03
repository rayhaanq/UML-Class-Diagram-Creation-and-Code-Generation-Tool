
package umldc.data;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author rayha
 */
public class CMethod extends ClassData implements IMethodSubject {

    private CopyOnWriteArrayList<Parameter> parameters = new CopyOnWriteArrayList<Parameter>();
    private JList<String> paramListView;
    private DefaultListModel paramListModel;
    private ArrayList<IMethodObserver> observers;
    private CMethod state;

    /**
     * used to create an empty method object
     */
    public CMethod() {
        observers = new ArrayList<IMethodObserver>();
    }
    
    /**
     *
     * @param state set the state of the method
     */
    public void setState(CMethod state) {
        this.state = state;
    }

    /**
     *
     * @return get state of method
     */
    public CMethod getState() {
        return state;
    }

    /**
     *
     * @return save the state as a new memento
     */
    public MethodMemento saveStateToMemento() {
        return new MethodMemento(state);
    }

    /**
     *
     * @param memento the memento's state to get
     */
    public void getStateFromMemento(MethodMemento memento) {
        state = memento.getState();
    }
    
    /**
     *
     * @param o observer to register
     */
    @Override
    public void registerObserver(IMethodObserver o) {
        observers.add(o);
    }

    /**
     *
     * @param o observer to remove
     */
    @Override
    public void removeObserver(IMethodObserver o) {
        int index = observers.indexOf(o);
        if (index >= 0) {
            observers.remove(index);
        }
    }

    /**
     * notify an observer if a change is made
     */
    @Override
    public void notifyObservers() {

        for (IMethodObserver observer : observers) {
            observer.update(this);
        }
    }

    /**
     *
     * @return get list of observers
     */
    public ArrayList<IMethodObserver> getObservers() {
        return observers;
    }

    /**
     *
     * @param param the parameter to edit
     * @param selectedIndex the selected parameters index
     */
    public void editParam(Parameter param, int selectedIndex) {

        boolean exists = false;

        for (int count = 0; count < parameters.size(); count++) {

            if (count == selectedIndex) {

                try {
                    count++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    //Do nothing
                }
            }

            try {
                if (parameters.get(count).getName().equals(param.getName())) {
                    exists = true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                //Do nothing
            }

        }
        if (exists) {
            JOptionPane.showMessageDialog(null,
                    "Parameter name already exists",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            Parameter edit = parameters.get(selectedIndex);
            edit.setName(param.getName());
            edit.setType(param.getType());
            edit.setIsArray(param.isArray());
            edit.setLibImports(param.getLibImports());
            notifyObservers();
        }
        System.out.println("Parameters");
        System.out.println(parameters);
    }

    @Override
    public String toString() {

        String methString = "";

        String vis = getVisibility().getValShort();
        String comments = getComments();

        if (!comments.trim().isEmpty()) {
            comments = "{ " + comments + " }";
        } else {
            comments = "";
        }

        methString = vis + getName() + paramsToString() + " : " + getType() + " " + comments;

        return methString;//methString;
    }

    /**
     *
     * @return a temporary string (not used in implementation)
     */
    public String toStringTemp() {
        return "CMethod{Name: " + getName() + "Visibility: " + getVisibility() + "Parameters: " + paramsToString() + "Comments: " + getComments() + '}';
    }

    /**
     *
     * @return string version of a parameter, used to show in the class diagram
     */
    public String paramsToString() {
        String params = "";

        for (Parameter parameter : parameters) {
            if (parameter.equals(parameters.get(parameters.size() - 1))) {
                params += parameter.toString();
            } else {
                params += parameter.toString() + ", ";
            }

        }

        params = "(" + params + ")";

        return params;
    }

    /**
     *
     * @param param parameter to add to method
     */
    public void addParam(Parameter param) {

        parameters.add(param);
        notifyObservers();


    }
    
    /**
     *
     * @param param parameter to check if its valid
     * @return returns true if the parameter is valid
     */
    public boolean isValidParam(Parameter param) {
        boolean added = true;
      
        if (!parameters.isEmpty()) {
            for (Parameter item : parameters) {
                if (item.getName().equals(param.getName())) {
                    added = false;
                    //exists = true;
                }
            }
        }

        if (!added) {
            JOptionPane.showMessageDialog(null,
                    "Parameter name already exists",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);

        }
        return added;
    }

    /**
     *
     * @param index index of parameter to be removed
     */
    public void removeParam(int index) {

        try {
            //int index = paramListView.getSelectedIndex();
            //paramListModel.remove(index);
            parameters.remove(index);
            notifyObservers();
        } catch (Exception e) {
            //Ignore
        }
    }

    /**
     *
     * @param parameters set method parameters to these parameters
     */
    public void setParameters(CopyOnWriteArrayList<Parameter> parameters) {
        this.parameters = parameters;
        notifyObservers();
    }

    /**
     *
     * @return returns parameters
     */
    public CopyOnWriteArrayList<Parameter> getParameters() {
        return parameters;
    }

    /**
     *
     * @return parameterListView
     */
    public JList<String> getParamListView() {
        return paramListView;
    }

    /**
     *
     * @return DefaultListModel paramListModel
     */
    public DefaultListModel getParamListModel() {
        return paramListModel;
    }

}
