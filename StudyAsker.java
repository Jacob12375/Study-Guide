package StudyGuide;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class StudyAsker extends JFrame{
    public static ArrayList cAnswer = new ArrayList();
    public static ArrayList wAnswer = new ArrayList();
    public static ArrayList question = new ArrayList();
    public static JButton[] buttons = new JButton[4];
    public static boolean spot1, spot2, spot3, spot4 = false;
    public StudyAsker(){
        super("Question generator");
        StudyGuideMain.setLookAndFeel();
        question.clear();
        cAnswer.clear();
        wAnswer.clear();
        setUp();
        spot1 = false;
        spot2 = false;
        spot3 = false;
        spot4 = false;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        JPanel pane = new JPanel();
        pane.setLayout(new GridLayout(10, 1));
        
        int toFetch = (int)Math.floor(Math.random() * (question.size()));
        JLabel questionLabel = new JLabel(question.get(toFetch) + "");
        int count = 0;
        int random = (int)Math.floor(Math.random() * 3);
        //buttons[random].setText(cAnswer.get(toFetch).toString());
        //setUsed(random);
        boolean continueLoop = true;
        boolean correctShown = false;
        for(int i = 0; i < 4; i++){
            buttons[i] = new JButton("");
        }
        while(continueLoop){
            int toTry = (int)Math.floor(Math.random() * 4);
            switch(toTry){
                case 0:
                    if(spot1 == false){
                        if(correctShown){
                            buttons[0].setText(wAnswer.get((toFetch * 3) + count).toString());
                            count++;
                        }else {
                            buttons[0].setText(cAnswer.get(toFetch)+ "");
                        correctShown = true;
                    }
                    }
                    spot1 = true;
                    break;
                case 1:
                          if(spot2 == false){
                        if(correctShown){
                            buttons[1].setText(wAnswer.get((toFetch * 3) + count).toString());
                            count++;
                        }else{ 
                        buttons[1].setText(cAnswer.get(toFetch) + "");
                        correctShown = true;
                        }
                    }
                          spot2 = true;
                    break;
                case 2:
                             if(spot3 == false){
                        if(correctShown){
                            buttons[2].setText(wAnswer.get((toFetch * 3) + count).toString());
                            count++;
                        }else{ 
                            buttons[2].setText(cAnswer.get(toFetch) + "");
                        }
                        correctShown = true;
                    }
                             spot3 = true;
                    break;
                case 3:
                             if(spot4 == false){
                        if(correctShown){
                            buttons[3].setText(wAnswer.get((toFetch * 3) + count).toString());
                            count++;
                        }else{
                            buttons[3].setText(cAnswer.get(toFetch) + "");
                        }
                        correctShown = true;
                    }
                             spot4 = true;
                    break;
                       }
            //System.out.println(toTry + "");
            if(spot1 == true & spot2 == true & spot3 == true & spot4 == true){
                continueLoop = false;
            }
        }
        MouseAdapter mouseAdapt = new MouseAdapter(){
            public void mouseReleased(MouseEvent me){
                JButton button = (JButton)me.getSource();
                if(button.getText().equals(cAnswer.get(toFetch))){
                    JOptionPane.showMessageDialog(null, "Correct!");
                }else{
                    JOptionPane.showMessageDialog(null, "Incorrect, the correct answer was " + cAnswer.get(toFetch));
                    
                }
            }
        };
        pane.add(questionLabel);
        for(int j = 0; j < 4; j++){
            buttons[j].addMouseListener(mouseAdapt);
            pane.add(buttons[j]);
        }
        add(pane);
        setVisible(true);
    }
    public static void setLookAndFeel(){
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(Exception exc){
            
        }
    }
    public static void setUp(){
    try{
    // Set up the input streams
    FileReader fileQuestion = new FileReader("questions.txt");
    FileReader fileCAnswer = new FileReader("CorrectAnswers.txt");
    FileReader fileWAnswer = new FileReader("WrongAnswers.txt");
    BufferedReader questionReader = new BufferedReader(fileQuestion);
    BufferedReader CAnswerReader = new BufferedReader(fileCAnswer);
    BufferedReader WAnswerReader = new BufferedReader(fileWAnswer);
    boolean continueLoop = true;
    while(continueLoop){
        String fromFile = "";
        try{
            fromFile = questionReader.readLine();
            if(fromFile != null){
                question.add(fromFile);
            }else{
                continueLoop = false;
            }
        }catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }
    continueLoop = true;
    while(continueLoop){
        String fromFile = "";
        try{
            fromFile = CAnswerReader.readLine();
            if(fromFile != null){
                cAnswer.add(fromFile);
            }else{
                continueLoop = false;
            }
        }catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }
        continueLoop = true;
        while(continueLoop){
            String fromFile = "";
            String toAdd = "";
            try{
                fromFile = WAnswerReader.readLine();
                if(fromFile != null){
                    for(int i = 0; i < fromFile.length(); i++){
                        if(fromFile.charAt(i) != '-'){
                            toAdd += fromFile.charAt(i);
                        }else{
                            wAnswer.add(toAdd);
                            toAdd = "";
                        }
                    }wAnswer.add(toAdd);
                }else{
                    continueLoop = false;
                }
            }catch(IOException ioe){
                System.out.println(ioe.getMessage());
            }
        }
    }catch(IOException ioe){
        System.out.println(ioe.getMessage());
    }
    }
    public static void main(String[] arguments){
        new StudyAsker();
    }
 }
