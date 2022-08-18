import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BackEnd extends JPanel implements ActionListener {

    static final int HEIGHT = 800;
    static final int WIDTH = 800;
    static final int BALLOON_DIAMETER = 50; //OR change it to diameter!
    static final int BOARD_SIZE = 256;
    final  Timer timer = new Timer(0,this);

    boolean gameOver = false;
    String colourCode = null;
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
                            break;
                        case KeyEvent.VK_R:
                            colourCode = "Red";
                            break;
                        case KeyEvent.VK_G:
                            colourCode = "Green";
                            break;
                        case KeyEvent.VK_B:
                            colourCode = "Blue";
                            break;
                    }
                }
            }

        });
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        if (!gameOver) {
            switch (colourCode) {
                case "Blue":
                    g.setColor(Color.BLUE);
                    g.fillOval(0,300,100,100);
                    break;
                case "Red":
                    g.setColor(Color.RED);
                    g.fillOval(200,300,100,100);
                    break;
                case "Green":
                    g.setColor(Color.GREEN);
                    g.fillOval(400,300,100,100);
                    break;
                case "Yellow":
                    g.setColor(Color.YELLOW);
                    g.fillOval(600,300,100,100);
                    //colourCode = "Blue";  //something strange happens!
                    break;
                default:
                    break;
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e){ //Equivalent to main() in C/C++ i.e "engine-code"
        if(!gameOver){
            repaint();  //calling paintComponent()!
            timer.stop();
        }
    }
}
