import java.awt.Color;

import javax.swing.*;

public class FrontEnd extends JFrame {

   //Equivalent to Class "Game" in th
   public FrontEnd(){
    this.setBackground(Color.DARK_GRAY);
       this.add(new BackEnd());
       this.setTitle("Balloons Game");
       this.pack();
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setResizable(false);
       this.setVisible(true);
       this.setLocationRelativeTo(null);
   }
}
