
package umldc.GUI;

import umldc.data.CMethod;
import umldc.data.ClassObject;
import umldc.data.Parameter;
import umldc.data.Attribute;
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author rayha
 */
public class MyGUI extends JFrame {
    
    DrawingPanel panelCenter;
     JScrollPane scrollPane;
    private ArrayList<ClassObject> classes = new ArrayList<ClassObject>();
    
    /**
     *
     */
    public MyGUI(){
        
        super("UML Diagram Creation and Conversion Tool");
        
        createView();
    }
    
    //Initialize UI Components

    /**
     * add components the the GUI
     */
    public void createView(){
        
        //Create Main content pane with Border Layout
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        //
        
        //Create Panel for West with GridBagLayout
        JPanel panelWest = new JPanel();
        panelWest.setLayout(new GridBagLayout());
        panelWest.setBackground(new Color(200, 200, 200));
        contentPane.add(panelWest, BorderLayout.WEST);
       
        //Set Layout?
        //scrollableDesktop.setBackground(new Color(255,255,255));
        panelCenter = new DrawingPanel(classes);
       // contentPane.add(panelCenter, BorderLayout.CENTER);
        scrollPane = new JScrollPane(panelCenter);
        
        scrollPane.setPreferredSize(new Dimension(200,200));
        add(scrollPane, BorderLayout.CENTER);
       
        //JMenuBar
        createMenu();
        //
        
        //Create Buttons on West Panel
        createToolButtons(panelWest);
        
       
        //setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(300, 200));
        setVisible(true);
    }
    
    private void createMenu(){
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        
        JMenu fileMenu = new JMenu("File");
        menubar.add(fileMenu);
        
        JMenuItem exportItem = new JMenuItem("Create Code...");
        exportItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               
                
                if (!classes.isEmpty()) {
                    JFileChooser folderChooser = new JFileChooser();

                    folderChooser.setAcceptAllFileFilterUsed(false);
                    folderChooser.setCurrentDirectory(new java.io.File("\\"));
                    folderChooser.setDialogTitle("Choose Folder to save files");
                    folderChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

                    int selected = folderChooser.showDialog(null, "OK");

                    File saveDir= null;

                    if (selected == JFileChooser.APPROVE_OPTION){
                        saveDir = folderChooser.getSelectedFile();
                        createClassFiles(saveDir);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                    "There are no classes",
                    "Attention",
                    JOptionPane.WARNING_MESSAGE);
                }
                
            }
        });
        fileMenu.add(exportItem);
    }
    
    
    //genertae the class file, takes a directory for output as an argument
    private void createClassFiles(File dir) {
        
        if (!classes.isEmpty()){
            
            for (ClassObject c : classes) {

                File classFile = new File(dir.getPath()+ "\\" + c.getClassName() + ".java");
                
                try {
                    classFile.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(MyGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(classFile));
                    writer.write("public class " + c.getClassName() + " {\n\n\t");
                    generateVarCode(writer, c);
                    generateMethCode(writer, c);
                    writer.write("\n}");
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(MyGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    //generate code for a variable
    private void generateVarCode(Writer writer, ClassObject c) throws IOException {
        for (Attribute att : c.getAttributes()) {

            String array = "";
            if (att.isArray()) {
                array = "[]";
            }

            String stat = "";
            if (att.isStatic()) {
                stat = "static ";
            }
            String rO = "";
            if (att.isReadOnly()) {
                rO = "final ";
            }

            writer.write(att.getVisibility().toString() + " " + stat + rO + att.getType() + array + " " + att.getName() + ";\n\t");
        }
        
        writer.write("\n");
    }

    //generate code for a method
    private void generateMethCode(Writer writer, ClassObject c) throws IOException{
        for (CMethod meth : c.getMethods()){
            
            writer.write("\t");
            
            String stat = "";
            if (meth.isStatic()){
                stat = "static ";
            }
            
            String parameters = "(";
            for (Parameter param : meth.getParameters()){
                String array = "";
                if (param.isArray()){
                    array = "[]";
                }
                String paramString = param.getType() + array + " " + param.getName() + ", ";
                
                
                
                
                parameters += paramString;
                
                
                
                
            }
            
            if (parameters.endsWith(", ")){
                    int index = parameters.lastIndexOf(", ");
                    
                    if (index >= 0){
                        parameters = parameters.substring(0, index);
                    }
                }
            
            parameters += ")";
            
            writer.write(meth.getVisibility().toString() + " " + stat + meth.getType() + " " + meth.getName() + parameters + "{\n\n\t");
            
                if (!meth.getType().equals("void")){
                    //writer.write(meth.getType() + "tempVar;\n\n");
                    createTempVar(writer, meth);
                    writer.write("\t\treturn tempVar;");
                }
            
            writer.write("\n\t}\n\n");
        }
    }
    
    private void createTempVar(Writer writer, CMethod meth) throws IOException{
        if (meth.getType().equals("int") || meth.getType().equals("byte") || meth.getType().equals("short") || meth.getType().equals("double") || meth.getType().equals("long") || meth.getType().equals("float") || meth.getType().equals("char")){
            writer.write("\t" + meth.getType() + " tempVar = 0;\n\n");
        } else if (meth.getType().equals("String")){
            writer.write("\t" + meth.getType() + " tempVar = \"\";\n\n");
        } else if (meth.getType().equals("boolean")){
            writer.write("\t" + meth.getType() + " tempVar = true;\n\n");
        } else {
            writer.write("\t" + meth.getType() + " tempVar = null;\n\n");
        }
    }
    
    //Create the tool buttons
    private void createToolButtons(JPanel panel){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel toolLabel = new JLabel("Tools");
        toolLabel.setOpaque(true);
        toolLabel.setBackground(new Color(150, 150, 150));
        toolLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(toolLabel, constraints);
        constraints.gridy ++;
        
        //Add Class Button
        JButton addClassButton = new JButton(new ImageIcon(getClass().getResource("resources/addClass.png")));
        addClassButton.setToolTipText("Add Class");
        addClassButton.addActionListener(new ActionListener(){
            //Action to open class dialog
            @Override
            public void actionPerformed(ActionEvent e) {
                //Component component = (Component) e.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(addClassButton);
                AddClassDialog ACDialog = new AddClassDialog(frame, true);
                ACDialog.setLocationRelativeTo(frame);
                ACDialog.setVisible(true);

            }
        });
        //
        
        
        
        //Add Relation Button
        JButton addRelButton = new JButton(new ImageIcon(getClass().getResource("resources/addRel.png")));
        addRelButton.setToolTipText("Add Relation");
        //
        
        
        //For Testing
        addRelButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(classes);
                
            }
            
        });
        
        panel.add(addClassButton, constraints);
        constraints.gridy ++;
        constraints.weighty = 1;
        panel.add(addRelButton, constraints);
    }
    
    private MyGUI getMyGUI(){
            return this;
        }
    
    /**
     *
     * @param cOb classObject that should be checked if it exists
     * @return true if the class already exists
     */
    public boolean classExists(ClassObject cOb){
         boolean exists = false;
        for (ClassObject clazz : classes){
            if (clazz.getClassName().equals(cOb.getClassName())){
                exists = true;
            }
        }
        return exists;
    }
    
    /**
     *
     * @param cOb classObject to be added to the ArrayList of classObjects
     */
    public void addClass(ClassObject cOb){
        
       classes.add(cOb);
        
    }
    
    
    
}