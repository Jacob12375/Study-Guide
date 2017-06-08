package bio;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class BioMain extends JFrame{
    public static Boolean mainMade = false;
    private void setLookAndFeel(){
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(Exception exc){
            System.out.println(exc.getMessage());
        }
    }
    public static String s;
    public static void startAsker(){
        new Bioo();
    }
    public static String destination;
    BioMain(){
        super("Choose an action");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(100, 100);
        setLayout(new FlowLayout());
        
        JPanel pane = new JPanel();
        JButton toMain = new JButton("Go to question asker");
        toMain.setBackground(Color.ORANGE);
        toMain.setOpaque(true);
        toMain.setBorderPainted(false);
        JButton toAdder = new JButton("Go to question adder");
        toAdder.setBackground(Color.RED);
        toAdder.setOpaque(true);
        toAdder.setBorderPainted(false);
        JButton toEditor = new JButton("Go to question editor");
        toEditor.setBackground(Color.GREEN);
        toEditor.setOpaque(true);
        toEditor.setBorderPainted(false);
        pane.setBackground(Color.BLACK);
        MouseAdapter mouseAdapt = new MouseAdapter(){
            public void mouseReleased(MouseEvent mEvent){
            if(mEvent.getSource() == toMain){
                Bioo.setUp();
                new Bioo();
            }else if(mEvent.getSource() == toAdder){
                new BioWriteEdit();
            }else{
                BioEdit.setUp();
                new BioEdit();
            }
            }
        };
       toMain.addMouseListener(mouseAdapt);
       toAdder.addMouseListener(mouseAdapt);
       toEditor.addMouseListener(mouseAdapt);
       
       pane.add(toMain);
       pane.add(toAdder);
       pane.add(toEditor);
       add(pane);
       setVisible(true);
       pack();
    }
    
    public static void main(String[] arguments){
        new BioMain();
    }
}