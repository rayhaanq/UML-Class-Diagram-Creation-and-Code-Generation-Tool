
package umldc.GUI;

import umldc.data.CMethod;
import umldc.data.ClassObject;
import umldc.data.Attribute;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javafx.scene.shape.Line;
import javax.swing.JPanel;

/**
 *
 * @author rayha
 */
public class DrawingPanel extends JPanel{

    private ArrayList<ClassObject> classes;
    Point2D.Double point;
    ArrayList<Rectangle2D.Double> classBoxes = new ArrayList<Rectangle2D.Double>();

    /**
     *
     * @param classes the ArrayList of classes that have been added by the user
     */
    public DrawingPanel(ArrayList<ClassObject> classes) {
        this.classes = classes;
       
        setBackground(Color.WHITE);
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        
        point = new Point2D.Double(10, 10);
        
        for (ClassObject clazz : classes){
            
            
            drawClass(g2, point, clazz);
            
        }
        
        this.setPreferredSize(this.getArea());
        this.revalidate();
    }
    
    /**
     * Draws a single class object as a diagram object
     * @param g2 graphics 2d object
     * @param point the point that should act as a pen when drawing the diagram
     * @param c a class object
     */
    private void drawClass(Graphics2D g2, Point2D.Double point, ClassObject c){
        
        String className = c.getClassName();
        FontMetrics metrics = g2.getFontMetrics();
        int areaX = 0;
        int areaY = 0;
        

        int longestString = getLongestStringWidth(g2, c);

        Rectangle2D.Double classBox = new Rectangle2D.Double(point.x, point.y, longestString + 20, getBoxHeight(c));
        classBoxes.add(classBox);
        g2.draw(classBox);

        int stringX = (int)((classBox.width - metrics.stringWidth(className)) / 2) +10;
        
        point.setLocation(point.x, point.y + 20);
        g2.drawString(className, (int) stringX, (int) point.y);

        if (!c.getAttributes().isEmpty() || !c.getMethods().isEmpty()) {

            point.setLocation(point.x, point.y + 10);

            Line2D.Double lineSeperator = new Line2D.Double(point.x, point.y, classBox.width + point.x, point.y);
            g2.draw(lineSeperator);

            //ArrayList<String> variables = new ArrayList<String>();
            point.setLocation(point.x, point.y + 20);

            for (Attribute var : c.getAttributes()) {
                String variable = var.toString();
                g2.drawString(variable, (int) point.x + 10, (int) point.y);

                point.setLocation(point.x, point.y + 20);
            }
            
            if (!c.getAttributes().isEmpty() && !c.getMethods().isEmpty()){
                point.setLocation(point.x, point.y - 10);
                lineSeperator = new Line2D.Double(point.x, point.y, classBox.width + point.x, point.y);
                g2.draw(lineSeperator);
                point.setLocation(point.x, point.y + 20);
            }
            
            for (CMethod meth : c.getMethods()) {
                String method = meth.toString();
                g2.drawString(method, (int) point.x + 10, (int) point.y);

                point.setLocation(point.x, point.y + 20);
            }

        }
        
        this.point.setLocation(classBox.x, classBox.height + classBox.y + 20);
    }
    
    /**
     * Gets the longest string inside a classObject
     * @param g2 a Graphics2D object
     * @param c a class object
     * 
     * 
     */
    private int getLongestStringWidth(Graphics2D g2, ClassObject c){
        
        //List of all strings of a class
        ArrayList<String> strings = getAllStrings(c);
        FontMetrics metrics = g2.getFontMetrics();
        
        int longestStringSize = 0;
        
        //set longestStringSize to the size of the longest string
        for (String str : strings){
            if (metrics.stringWidth(str)> longestStringSize){
                longestStringSize = metrics.stringWidth(str);
            }
        }
        
        return longestStringSize;
    }
    
    //Get a list of all displayable strings of a ClassObject (class name,
    //methods and varaiables)
    private ArrayList<String> getAllStrings(ClassObject c){
        ArrayList<String> strings = new ArrayList<String>();
        
        strings.add(c.getClassName());
        
        for (Attribute var : c.getAttributes()) {
            strings.add(var.toString());
        }
        for (CMethod meth : c.getMethods()) {
            strings.add(meth.toString());
        }
        
        return strings;
    }

    //get the height of a class diagram box
    private int getBoxHeight(ClassObject c){
        
        ArrayList<String> strings = getAllStrings(c);
        
        int height = 20;
        
        if (!c.getAttributes().isEmpty() && !c.getMethods().isEmpty()){
            height +=  10;
        }
        
        for (String str : strings){
            height +=  20;
        }
        
        return height;
    }
    
    /**
     *
     * @return the area that should be drawn on based on the size of each class diagram object
     */
    public Dimension getArea(){
        
        int furthestX = 0;
        int furthestY = 0;
        
        for (Rectangle2D.Double rect : classBoxes){
            if (rect.x + rect.width > furthestX){
                furthestX = (int)(rect.x + rect.width);
            }
            if (rect.y + rect.height > furthestY){
                furthestY = (int)(rect.y + rect.height);
            }
        }
        
        Dimension area = new Dimension(furthestX + 20, furthestY + 20);
        
        return area;
    }

}
