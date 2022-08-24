import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;

public class BackEnd extends JPanel implements ActionListener {

    static final int HEIGHT = 800;
    static final int WIDTH = 800;
    static int balloonPosX = 100;
    static int balloonPosY = 300;

    final  Timer timer = new Timer(0,this);
    public static Random randVariable = new Random();

    boolean gameOver = false;
    boolean validInput = true;
    String colourCode = ""; //To complete constructor initial arguments.

    static List<String> colourCodes = new ArrayList<>();
    static List<String> newState = new ArrayList<>();
    static List<String> baseCodes = new ArrayList<>();

    static int attemptsLeft = 3;

    public BackEnd() {

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT)); //SIZE OF PANEL OR "GAME SCREEN"
        this.setBackground(Color.DARK_GRAY);
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if (!gameOver) {
                    timer.start();
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_Y:
                            colourCode = "Yellow";
                            validInput = true;
                            break;
                        case KeyEvent.VK_R:
                            colourCode = "Red";
                            validInput = true;
                            break;
                        case KeyEvent.VK_G:
                            colourCode = "Green";
                            validInput = true;
                            break;
                        case KeyEvent.VK_B:
                            colourCode = "Blue";
                            validInput = true;
                            break;
                        default:
                            validInput = false;
                            break;
                    }
                }
            }

        });
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        //Paint light grey circles that shows balloon positions.
        for(int i = 1; i <= 4; i++){
            g.setColor(Color.LIGHT_GRAY);
            g.fillOval(150*i,balloonPosY,100,100);
        }

        if(!gameOver && (colourCodes.size() > 0)){

            //Paint previously selected balloons.
            for (int i = 1; i < balloonPosX; i++){
                switch (colourCodes.get(i-1)) {
                    case "Blue":
                        g.setColor(Color.BLUE);
                        g.fillOval(150*i,balloonPosY,100,100);
                        break;
                    case "Red":
                        g.setColor(Color.RED);
                        g.fillOval(150*i,balloonPosY,100,100);
                        break;
                    case "Green":
                        g.setColor(Color.GREEN);
                        g.fillOval(150*i,balloonPosY,100,100);
                        break;
                    case "Yellow":
                        g.setColor(Color.YELLOW);
                        g.fillOval(150*i,balloonPosY,100,100);
                        break;
                    default:
                        break;
                }

            }

            //paint the current balloon.
            switch (colourCode) {
                case "Blue":
                    g.setColor(Color.BLUE);
                    g.fillOval(150*balloonPosX,balloonPosY,100,100);

                    break;
                case "Red":
                    g.setColor(Color.RED);
                    g.fillOval(150*balloonPosX,balloonPosY,100,100);
                    break;
                case "Green":
                    g.setColor(Color.GREEN);
                    g.fillOval(150*balloonPosX,balloonPosY,100,100);
                    break;
                case "Yellow":
                    g.setColor(Color.YELLOW);
                    g.fillOval(150*balloonPosX,balloonPosY,100,100);
                    break;
                default:

                    break;
            }

            if(balloonPosX == 4){

                if((attemptsLeft > 0) || (colourCodes.equals(newState))){

                    if(colourCodes.equals(newState)){
                        String youWon = "You Won!";

                        g.setColor(Color.GREEN);
                        g.setFont(new Font("Arial", Font.BOLD, 50));
                        g.drawString(youWon, (WIDTH - getFontMetrics(g.getFont()).stringWidth(youWon)) / 2, HEIGHT / 3);


                    } else {

                        //for() loop for drawing black and white balloons to show which colours were guessed correctly.
                        for(int i = 0; i < colourCodes.size(); i++){
                            if(colourCodes.get(i).equals(newState.get(i))){
                                g.setColor(Color.WHITE);
                                g.fillOval(150*(i+1),balloonPosY,20,20);
                            } else{
                                g.setColor(Color.BLACK);
                                g.fillOval(150*(i+1),balloonPosY,20,20);
                            }
                        }

                        String userNotice = String.format("Attempts left : %d", attemptsLeft);
                        String pressKey = "(press 'B' to begin your next attempt)";

                        g.setColor(Color.BLACK);
                        g.setFont(new Font("TimesRoman", Font.BOLD, 50));
                        g.drawString(userNotice, (WIDTH - getFontMetrics(g.getFont()).stringWidth(userNotice)) / 2, 100 + HEIGHT / 2);
                        g.setFont(new Font("TimesRoman", Font.ITALIC, 25));
                        g.drawString(pressKey, (WIDTH - getFontMetrics(g.getFont()).stringWidth(pressKey)) / 2, 150 + HEIGHT / 2);
                    }

                } else{
                    gameOver = true;
                    String youLost = "GAME OVER!";
                    
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("TimesRoman",Font.BOLD,50));
                    g.drawString(youLost,(WIDTH - getFontMetrics(g.getFont()).stringWidth(youLost))/2, 100 + HEIGHT/2 );

                    //for() loop for drawing black and white balloons to show which colours were guessed correctly.
                    for(int i = 0; i < colourCodes.size(); i++){
                        if(colourCodes.get(i).equals(newState.get(i))){
                            g.setColor(Color.WHITE);
                            g.fillOval(150*(i+1),balloonPosY,20,20);
                        } else{
                            g.setColor(Color.BLACK);
                            g.fillOval(150*(i+1),balloonPosY,20,20);
                        }
                    }
                }

                colourCodes.clear();
            }
        }
    }

    
    public static int[] randomIndices(){

        int [] indices = new int[4];
        int i;

        for(int k = 0; k < indices.length; k++){

            indices[k] = randVariable.nextInt(2);
            i = 0;

            while(i < indices.length){
                if(indices[k] == indices[i] && k > i){
                    if(indices[k] + 1 < indices.length){
                        indices[k] += 1 ;
                    }
                    else{
                        indices[k] = indices.length - indices[k] - 1;
                    }
                    i = 0;
                }
                else{
                    i++;
                }
            }
        }
        return indices;
    }

    public static void generateBalloons(int[] indices){

        for (int i = 0; i < baseCodes.size() - 1; i++){
            newState.add(baseCodes.get(indices[i]));
        }
    }

    // newGame not used for now!
    public void newGame(){
        baseCodes.add("Blue");
        baseCodes.add("Red");
        baseCodes.add("Green");
        baseCodes.add("Yellow");
        baseCodes.add("Orange");

        newState.clear();
        generateBalloons(randomIndices());
    }

    //Specifies the position of the current balloon to be painted.
    public void balloonAt(){
        balloonPosX = colourCodes.size();
        if(attemptsLeft == 3){
            baseCodes.clear();
            newGame();
        }

        if(balloonPosX == 4){
            repaint();
            //colourCodes.clear(); //Try again after failed attempt.
            attemptsLeft--;

        }
    }

    @Override
    public void actionPerformed(ActionEvent e){ //Equivalent to main() in C++ i.e "entry point"

        if((balloonPosX == 4) && (colourCode == "Blue")){
            repaint();
            validInput = false;
            balloonPosX = 0;
        }
        if(!gameOver && validInput){

            colourCodes.add(colourCode);
            balloonAt();
            repaint();  //calling paintComponent()!
            timer.stop();
        }
    }
}