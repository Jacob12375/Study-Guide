package bio;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class BioEdit extends JFrame{
    public static FileReader readerQuestion, readerCorrectAnswer, readerWrongAnswer;
    public static BufferedReader buffQuestion, buffCorrectAnswer, buffWrongAnswer;
    public static FileWriter writerQuestion, writerCorrectAnswer, writerWrongAnswer;
    public static BufferedWriter buffwQuestion, buffwCorrectAnswer, buffwWrongAnswer;
    public static void setUp(){
        try{
            readerQuestion = new FileReader("questions.txt");
            readerCorrectAnswer = new FileReader("CorrectAnswers.txt");
            readerWrongAnswer = new FileReader("WrongAnswers.txt");
            buffQuestion = new BufferedReader(readerQuestion);
            buffCorrectAnswer = new BufferedReader(readerCorrectAnswer);
            buffWrongAnswer = new BufferedReader(readerWrongAnswer);
}catch(Exception fnfe){
    System.out.println("Error: " + fnfe.getMessage());
            }
    }
    public BioEdit(){
        super("Editor");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel pane = new JPanel();
        JScrollPane scrollPane = new JScrollPane();
        JTextArea questionArea = new JTextArea(30, 15);
        JTextArea correctArea = new JTextArea(30, 15);
        JTextArea wrongArea = new JTextArea(30, 15);
        String toSet = "";
        boolean done = true;
        for(int i = 0; i < 3; i++){
            toSet = "";
            done = true;
        while(done){
            try{
                    BufferedReader read = buffQuestion;
                    if(i == 0){
                        read = buffQuestion;
                    }else if(i == 1){
                        read = buffCorrectAnswer;
                    }else if(i == 2){
                        read = buffWrongAnswer;
                    }
            String add = read.readLine();
            if(add == null){
                done = false;
            }else{
                done = true;
            }
         //done = (add == null) ? false:true; 
         if(done == false)
             break;
            toSet += add + "\n";
            //System.out.println(add);
                }catch(IOException ioe){
                    System.out.println("Error " + ioe.getMessage());
                }
                }
        if(i == 0){
            questionArea.setText(toSet);
        }
        if(i == 1){
            correctArea.setText(toSet);
        }
        if(i == 2){
            wrongArea.setText(toSet);
        }
        }
        //questionArea.setText(toSet);
        JButton save = new JButton("Save Changes");
        save.addMouseListener(new MouseAdapter(){
            public void mouseReleased(MouseEvent mEvent){
                try{
            writerQuestion = new FileWriter("questions.txt");
            buffwQuestion = new BufferedWriter(writerQuestion);
                buffwQuestion.write(questionArea.getText());
                buffwQuestion.close();
                writerCorrectAnswer = new FileWriter("CorrectAnswers.txt");
                buffwCorrectAnswer = new BufferedWriter(writerCorrectAnswer);
                buffwCorrectAnswer.write(correctArea.getText());
                buffwCorrectAnswer.close();
                writerWrongAnswer = new FileWriter("WrongAnswers.txt");
                buffwWrongAnswer = new BufferedWriter(writerWrongAnswer);
                buffwWrongAnswer.write(wrongArea.getText());
                buffwWrongAnswer.close();
                System.out.println(questionArea.getText());
                JOptionPane.showMessageDialog(null, "Files Updated");
                }catch(IOException ioe){
                    //System.out.println(ioe.getMessage());
                }
            }
        });
        pane.add(save);
        pane.add(questionArea);
        pane.add(correctArea);
        pane.add(wrongArea);
        add(pane);
        setVisible(true);
        pack();
        }
    public static void main(String[] arguments){
        Bio.setLookAndFeel();
        setUp();
        new BioEdit();
    }
        }