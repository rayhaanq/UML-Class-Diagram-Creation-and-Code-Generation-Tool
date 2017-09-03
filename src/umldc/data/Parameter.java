
package umldc.data;

import java.util.ArrayList;

/**
 *
 * @author rayha
 */
public class Parameter extends ClassData{
    
    private boolean isArray;
    private ArrayList<String> libImports;
    
    /**
     * used to create empty parameter object
     */
    public Parameter(){
        
    }
    
    /**
     *
     * @param nm name of parameter
     * @param typ type of parameter
     * @param array is parameter an array
     * @param imports list of imports of parameter
     */
    public Parameter(String nm, String typ, boolean array, ArrayList<String> imports){
        super(nm, typ);
        
        isArray = array;
        libImports = imports;
    }
    
    @Override
    public String toString(){
        String name = super.getName();
        String type = super.getType();
        String array = "";
        
        if(isArray){ array = "[]";}  
        
        String attributeString = name + " : " + type + array;
        
        
        return attributeString;
    }

    /**
     *
     * @return if the parameter is an array
     */
    public boolean isArray() {
        return isArray;
    }
    
    /**
     *
     * @return imports of parameter
     */
    public ArrayList<String> getLibImports() {
        return libImports;
    }
    
    /**
     *
     * @param isArray true if parameter is an array
     */
    public void setIsArray(boolean isArray) {
        this.isArray = isArray;
    }

    /**
     *
     * @param libImports list of imports to be set
     */
    public void setLibImports(ArrayList<String> libImports) {
        this.libImports = libImports;
    }
    
    
    
}
