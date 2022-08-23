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
    static final int BALLOON_DIAMETER = 50; //OR change it to diameter!
    static int balloonPosX = 100;
    static int balloonPosY = 200;
    static final int BOARD_SIZE = 256;
    final Font font = new Font("TimesRoman",Font.BOLD,50);

    final  Timer timer = new Timer(300,this);
    public static Random randVariable = new Random();

    boolean gameOver = false;
    boolean validInput = true;
    String colourCode = ""; //To complete constructor initial arguments.

    static List<String> colourCodes = new ArrayList<>();
    static List<String> newState = new ArrayList<>();
    static List<String> baseCodes = new ArrayList<>();

    static int attemptsLeft = 2;

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

        if(!gameOver && (colourCodes.size() > 0)){
            //Paint light grey circles that shows balloon positions.
            for(int i = 1; i <= 4; i++){
                g.setColor(Color.LIGHT_GRAY);
                g.fillOval(150*i,balloonPosY,100,100);
            }

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

                balloonPosY += 100;
                g.setColor(Color.BLACK);
                g.setFont(font);

                if(attemptsLeft > 0){
                    String userNotice = String.format("You have %d attempts left", attemptsLeft);
                    g.drawString(userNotice,(WIDTH - getFontMetrics(g.getFont()).stringWidth(userNotice))/2, 100 + HEIGHT/2 );
                    
                } else{
                    gameOver = true;
                    String userNotice1 = "GAME OVER!";
                    g.drawString(userNotice1,(WIDTH - getFontMetrics(g.getFont()).stringWidth(userNotice1))/2, 100 + HEIGHT/2 );
                }
                
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

    public static List<String> generateBalloons(int[] indices){

        for (int i = 0; i < newState.size(); i++){
            newState.add(baseCodes.get(indices[i])); //= baseSequence[indices[i]];
        }

        return newState;
    }

    // newGame not used for now!
    public void newGame(){
        baseCodes.add("Blue");
        baseCodes.add("Red");
        baseCodes.add("Green");
        baseCodes.add("Yellow");
        baseCodes.add("Orange");

        attemptsLeft = 2; //Will be changed to "newState.size() - 1"
        newState = generateBalloons(randomIndices());
        gameOver = false;

    }

    //Specifies the position of the current balloon to be painted.
    public void balloonAt(){
        balloonPosX = colourCodes.size();
        if(balloonPosX == 5){
            repaint();
            colourCodes.clear(); //Try again after failed attempt.
            attemptsLeft--;

        }
    }

    @Override
    public void actionPerformed(ActionEvent e){ //Equivalent to main() in C++ i.e "entry point"

        // if(attemptsLeft == 0){
            
        //     newGame();
        // }

        if(!gameOver && validInput){

            colourCodes.add(colourCode);
            balloonAt();
            repaint();  //calling paintComponent()!
            timer.stop();
        }
    }
}
