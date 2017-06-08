package bio;
import java.io.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class BioWriteEdit extends JFrame{
    public static FileWriter fileQuestionOut, fileCorrectOut, fileWrongOut;
    public static BufferedWriter questionOut, correctOut, wrongOut;
    private static void setUpStreams(){
        try{
            fileQuestionOut = new FileWriter("questions.txt", true);
            fileCorrectOut = new FileWriter("CorrectAnswers.txt", true);
            fileWrongOut = new FileWriter("WrongAnswers.txt", true);
            questionOut = new BufferedWriter(fileQuestionOut);
            correctOut = new BufferedWriter(fileCorrectOut);
            wrongOut = new BufferedWriter(fileWrongOut);
        }catch(IOException ioe){
            System.out.println("Error: " + ioe.getMessage());
        }
    }
    private void setLookAndFeel(){
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(Exception exc){
            System.out.println("Error: " + exc.getMessage());
        }
    }
    public static JLabel questionLabel, correctLabel, wrongLabel;
    public static JTextField questionField, correctField, wrongField1, wrongField2, wrongField3;
    public static JButton updateFiles;
    public static JPanel pane;
    public BioWriteEdit(){
        super("Add A Question");
        pane = new JPanel();
        setSize(500, 300);
        pane.setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLookAndFeel();
        
        questionLabel = new JLabel("Enter the quesion to add");
        questionLabel.setLocation(10, 10);
        questionLabel.setSize(160, 60);
        // distance of 150 pixels
        questionField = new JTextField();
        questionField.setLocation(160, 28);
        questionField.setSize(300, 27);
        
        correctLabel = new JLabel("Enter the correct answer");
        correctLabel.setLocation(10, 60);
        correctLabel.setSize(200, 60);
        
        correctField = new JTextField();
        correctField.setLocation(160, 78);
        correctField.setSize(300, 27);
        
        wrongLabel = new JLabel("Enter three wrong answers");
        wrongLabel.setLocation(10, 120);
        wrongLabel.setSize(200, 60);
        
        wrongField1 = new JTextField();
        wrongField1.setLocation(160, 138);
        wrongField1.setSize(100, 27);
        
        wrongField2 = new JTextField();
        wrongField2.setLocation(260, 138);
        wrongField2.setSize(100, 27);
        
        wrongField3 = new JTextField();
        wrongField3.setLocation(360, 138);
        wrongField3.setSize(100, 27);
        
        updateFiles = new JButton("Add to files");
        updateFiles.setLocation(175, 200);
        updateFiles.setSize(150, 30);
        updateFiles.addMouseListener(new MouseAdapter(){
            public void mouseReleased(MouseEvent me){
                setUpStreams();
                try{
                    questionOut.write(questionField.getText() + "\n");
                    correctOut.write(correctField.getText() + "\n");
                    wrongOut.write(wrongField1.getText() + "-" + wrongField2.getText() + "-" + wrongField3.getText() + "\n");
                    questionOut.close();
                    correctOut.close();
                    wrongOut.close();
                    JOptionPane.showMessageDialog(null, "Files updated");
                    questionField.setText("");
                    correctField.setText("");
                    wrongField1.setText("");
                    wrongField2.setText("");
                    wrongField3.setText("");
                }catch(IOException ioe){
                    System.out.println(ioe.getMessage());
                }
            }
        });
        
        pane.add(questionLabel);
        pane.add(questionField);
        pane.add(correctLabel);
        pane.add(correctField);
        pane.add(wrongLabel);
        pane.add(wrongField1);
        pane.add(wrongField2);
        pane.add(wrongField3);
        pane.add(updateFiles);
        add(pane);
        setVisible(true);
    }
    private static void resetLocation(double x, double y, double width, double height, Component comp){
        double relativeX = (x / 484);
        double  relativeY = (y / 261);
        comp.setLocation((int) (relativeX * (double)pane.getWidth()), (int) (relativeY * (double)pane.getHeight()));
        double relativeWidth = (width / 484);
        double relativeHeight = (height / 261);
        comp.setSize((int)(relativeWidth * (double)pane.getWidth()), (int)(relativeHeight * (double)pane.getHeight()));
    }
    //private static void resetSize(int width, int height, Component comp){
      //  comp.setSize((1000 / width) * (pane.getWidth() / 100));
    //}
    public static void main(String[] arguments){
        new BioWriteEdit();
        System.out.println(pane.getHeight());
        while(true){
            resetLocation(10, 10, 160, 60, questionLabel);  
            resetLocation(160, 28, 300, 27, questionField);
            resetLocation(10, 60, 200, 60, correctLabel);
            resetLocation(160, 78, 300, 27, correctField);
            resetLocation(10, 120, 200, 60, wrongLabel);
            resetLocation(160, 138, 100, 27, wrongField1);
            resetLocation(260, 138, 100, 27, wrongField2);
            resetLocation(360, 138, 100, 27, wrongField3);
            resetLocation(175, 200, 150, 30, updateFiles);
        }
    }
}