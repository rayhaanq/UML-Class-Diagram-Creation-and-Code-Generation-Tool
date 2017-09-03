
package umldc.data;

/**
 *
 * @author rayha
 */
public enum VisibilityType {
    
    /**
     * public visibility
     */
    PUBLIC("public", "+"),

    /**
     * protected visibility
     */
    PROTECTED("protected", "#"),

    /**
     * package visibility
     */
    PACKAGE("package", "~"),

    /**
     * private visibility
     */
    PRIVATE("private", "-");
    
    private String value;
    private String valShort;
    
    VisibilityType(String val, String shrt){
        value = val;
        valShort = shrt;
    }

    @Override
    public String toString() {
        return value; //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return short version of the visibility type e.g. +, -, #, ~
     */
    public String getValShort() {
        return valShort;
    }
    
}
