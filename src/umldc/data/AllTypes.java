
package umldc.data;

/**
 *
 * @author rayha
 */
public enum AllTypes {

    /**
     * void type
     */
    VOID("void"),

    /**
     * int type
     */
    INT("int"),

    /**
     * String type
     */
    STRING("String"),

    /**
     * byte type
     */
    BYTE("byte"),

    /**
     * short type
     */
    SHORT("short"),

    /**
     * long type
     */
    LONG("long"),

    /**
     * float type
     */
    FLOAT("float"),

    /**
     *  double type
     */
    DOUBLE("double"),

    /**
     * boolean type
     */
    BOOLEAN("boolean"),

    /**
     *char type
     */
    CHAR("char"),

    /**
     * object type
     */
    OBJECT("object");
    
    private String str;
    
    AllTypes(String nm){
        str = nm;
    }

    //Return string version of enum

    /**
     *
     * @return string version of enum
     */
    public String getString() {
        return str;
    }
    
    /**
     *
     * @return all enum values except void (for attributes)
     */
    public static AllTypes[] valuesNoVoid(){
        int index = 0;
        AllTypes types[] = new AllTypes[10]; 
        for (AllTypes type: AllTypes.values()){
            if(type!=AllTypes.VOID){
                types[index] = type;
                index ++;
            } 
        }
        return types;
    }
}
