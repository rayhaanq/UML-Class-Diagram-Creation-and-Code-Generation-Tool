package umldc.data;


/**
 *
 * @author rayha
 */
public interface IClassSubject {
    
    /**
     *
     * @param o observer to register
     */
    public void registerObserver(IClassObserver o);

    /**
     *
     * @param o observer to remove
     */
    public void removeObserver(IClassObserver o);

    /**
     * notify all observers of any changes made to class object
     */
    public void notifyObservers();
    
}
