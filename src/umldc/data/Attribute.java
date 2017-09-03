
package umldc.data;

import java.util.ArrayList;

/**
 *
 * @author rayha
 */
public class Attribute extends ClassData{
    
    private boolean readOnly;
    private ArrayList<String> libImports;
    private boolean isArray;
    //is Array or Collection
    
    //Variable Constructor

    /**
     *
     * @param nm name of attribute
     * @param visType visibility type
     * @param typ attribute type
     * @param array is it an array
     * @param isStatic is it static
     * @param rO is it read only (final)
     * @param imports array list of imports
     * @param comms string of comments
     */
    public Attribute(String nm, VisibilityType visType, String typ, boolean array, boolean isStatic,  boolean rO, ArrayList<String> imports, String comms) {
        super(nm, visType, typ, isStatic, imports, comms);
        readOnly = rO;
        libImports = imports;
        isArray = array;
        
    }
    
    @Override
    public String toString(){
        
        String vis = super.getVisibility().getValShort();
        String name = super.getName();
        String type = super.getType();
        String array = "";
        String comments = super.getComments();
        
        if (!comments.trim().isEmpty()){
            comments = "{ " + comments + " }";
        } else {
            comments = "";
        }
        
        if(isArray){ array = "[]";}  
        
        String attributeString = vis + name + " : " + type + array + " " +
                                   comments;
        
        
        return attributeString;
    }
    
    /**
     *
     * @return is attribute readOnly
     */
    public boolean isReadOnly() {
        return readOnly;
    }

    /**
     *
     * @return list of imports
     */
    public ArrayList<String> getLibImports() {
        return libImports;
    }

    /**
     *
     * @return is attribute an array
     */
    public boolean isArray() {
        return isArray;
    }

    /**
     *
     * @param readOnly set to true of false if it is read only
     */
    public void setIsReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    /**
     *
     * @param libImports list of imports
     */
    public void setLibImports(ArrayList<String> libImports) {
        this.libImports = libImports;
    }

    /**
     *
     * @param isArray is it an array
     */
    public void setIsArray(boolean isArray) {
        this.isArray = isArray;
    }
    
}
