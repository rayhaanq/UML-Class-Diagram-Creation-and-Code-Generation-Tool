
package umldc.data;

/**
 *
 * @author rayha
 */
public class MethodMemento {
    
    private CMethod state;
    
    /**
     *
     * @param state current state of method
     */
    public MethodMemento(CMethod state){
        this.state = state;
    }
    
    /**
     *
     * @return state of method
     */
    public CMethod getState(){
        return state;
    }
    
}
