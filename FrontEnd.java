import javax.swing.*;

public class FrontEnd extends JFrame {

   //Equivalent to Class "Game" in th
   public FrontEnd(){
       this.add(new BackEnd());
       this.setTitle("Snake Game");
       this.pack();
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setResizable(false);
       this.setVisible(true);
       this.setLocationRelativeTo(null);
   }
}
