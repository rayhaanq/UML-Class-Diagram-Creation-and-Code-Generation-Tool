
package umldc.data;

/**
 *
 * @author rayha
 */
public interface IMethodSubject {

    /**
     *
     * @param o observer to register
     */
    public void registerObserver(IMethodObserver o);

    /**
     *
     * @param o observer to remove
     */
    public void removeObserver(IMethodObserver o);

    /**
     * notify all observers of any changes made to method
     */
    public void notifyObservers();
}
