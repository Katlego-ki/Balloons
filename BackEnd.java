import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class BackEnd extends JPanel implements ActionListener {

    static final int HEIGHT = 800;
    static final int WIDTH = 800;
    static final int BALLOON_DIAMETER = 50; //OR change it to diameter!
    static int balloonPos;
    static final int BOARD_SIZE = 256;
    final  Timer timer = new Timer(0,this);

    boolean gameOver = false;
    boolean validInput = true;
    String colourCode = null;

    //Map<String, String > services = new HashMap<String, String>();
    static List<String> colourCodes = new ArrayList<String>();

    public BackEnd() {

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT)); //SIZE OF PANEL OR "GAME SCREEN"
        this.setBackground(Color.DARK_GRAY);
        this.setFocusable(true); //
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

        //Paint light grey cirlces that shows balloon positions.
        for(int i = 1; i <= 4; i++){
            g.setColor(Color.LIGHT_GRAY);
            g.fillOval(150*i,300,100,100);
        }

        //Paint previously selected balloons.
        for (int i = 1; i < balloonPos; i++){
            switch (colourCodes.get(i-1)) {
                case "Blue":
                    g.setColor(Color.BLUE);
                    g.fillOval(150*i,300,100,100);
                    break;
                case "Red":
                    g.setColor(Color.RED);
                    g.fillOval(150*i,300,100,100);
                    break;
                case "Green":
                    g.setColor(Color.GREEN);
                    g.fillOval(150*i,300,100,100);
                    break;
                case "Yellow":
                    g.setColor(Color.YELLOW);
                    g.fillOval(150*i,300,100,100);
                    break;
                default:
                    break;
            }

            if(balloonPos == 3){
                
            }
        }

        //paint the current balloon.
        switch (colourCode) {
            case "Blue":
                g.setColor(Color.BLUE);
                g.fillOval(150*balloonPos,300,100,100);

                break;
            case "Red":
                g.setColor(Color.RED);
                g.fillOval(150*balloonPos,300,100,100);
                break;
            case "Green":
                g.setColor(Color.GREEN);
                g.fillOval(150*balloonPos,300,100,100);
                break;
            case "Yellow":
                g.setColor(Color.YELLOW);
                g.fillOval(150*balloonPos,300,100,100);
                break;
            default:

                break;
            
        }
    }

    //Specifies the position of the current balloon to be painted.
    public void balloonAt(){
        balloonPos = colourCodes.size();
        if(balloonPos == 4){
            gameOver = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){ //Equivalent to main() in C++ i.e "entry point"
        if(!gameOver && validInput){

            colourCodes.add(colourCode);
            balloonAt();
            repaint();  //calling paintComponent()!
            timer.stop();
        }
            
    }
}
