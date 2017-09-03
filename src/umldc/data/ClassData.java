
package umldc.data;

import java.util.ArrayList;

/**
 *
 * @author rayha
 */
public abstract class ClassData {
    
    private String name;
    private VisibilityType visibility;
    private String comments;
    private boolean isStatic;
    private String type;
    private ArrayList<String> libImports;
    
    //Variable Constructor

    /**
     *
     * @param nm name of ClassData
     * @param visType visibility type of ClassData
     * @param typ type of ClassData
     * @param isStatic is the ClassData Static
     * @param imports list of imports
     * @param comms comments 
     */
    public ClassData(String nm, VisibilityType visType, String typ, boolean isStatic,  ArrayList<String> imports, String comms){
        name = nm;
        visibility = visType;
        this.isStatic = isStatic;
        comments = comms;
        type = typ;
        libImports = imports;
    }
    
    //Parameter Constructor

    /**
     *
     * @param nm name of ClassData
     * @param typ type of ClassData
     */
    public ClassData(String nm, String typ){
        name = nm;
        type = typ;
    }
    
    /**
     *
     */
    public ClassData(){
        
    }

    /**
     *
     * @return string version of imports
     */
    public String importsToString(){
        String importsString = "";
        
        try {
            for (String imp : libImports) {
                importsString += imp + "\n";
            }
        } catch (NullPointerException e) {

        }
        return importsString;
    }
    
    /**
     *
     * @return name of ClassData
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return visibility type
     */
    public VisibilityType getVisibility() {
        return visibility;
    }

    /**
     *
     * @return comments
     */
    public String getComments() {
        return comments;
    }

    /**
     *
     * @return if it is static
     */
    public boolean isStatic() {
        return isStatic;
    }

    /**
     *
     * @return type of ClassData
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @return imports list
     */
    public ArrayList<String> getLibImports() {
            return libImports;
        }

    /**
     *
     * @param name name to be set
     */
    public void setName(String name) {
        this.name = name;
        System.out.println("Name Set");
    }

    /**
     *
     * @param visibility visibility type to be set
     */
    public void setVisibility(VisibilityType visibility) {
        this.visibility = visibility;
    }

    /**
     *
     * @param comments comments to be set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     *
     * @param isStatic is it static
     */
    public void setIsStatic(boolean isStatic) {
        this.isStatic = isStatic;
    }

    /**
     *
     * @param type type to be set
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     *
     * @param libImports list of imports to be set
     */
    public void setLibImports(ArrayList<String> libImports) {
        this.libImports = libImports;
    }
}
