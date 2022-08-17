package Algorithms.Games;

import java.util.Random;
import java.util.Scanner;

public class Baloons {

    public static Random randVariable = new Random();
    public static char baseSequence [] = {'B','G','R','Y'}; 
    public static char newBaloons [] = new char[4];
    public static Scanner getInput = new Scanner(System.in);
    public static String baseSequence1 = "abcrfg";


    public static int[] randomIndices(){

        int [] indices = new int[4];
        int i = 0;

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

   //charAt was just used to check dynamic binding.
   public char charAt(){

        int i = randVariable.nextInt(4) ;
        System.out.println(baseSequence1.charAt(i));


    return newBaloons[i];
   } 

   public static char[] generateBaloons(int[] indices){ 

        for (int i = 0; i < newBaloons.length; i++){
            newBaloons[i] = baseSequence[indices[i]];
        }

        return newBaloons;
   }

    public static void main(String[] args) {

        newBaloons = generateBaloons(randomIndices()); 
        int attemptsLeft = 3;
        
        while(attemptsLeft > 0){

            int matchCount = 0;
            System.out.print("\n\nYou have " + attemptsLeft + " attempts left!\n\n" + "Your guess : ");
            String attemptSequence = getInput.next();
            System.out.print("\n\n");
            //Comparing attempt with correct order.

            for(int i = 0; i < newBaloons.length; i++){
                if(attemptSequence.charAt(i) == newBaloons[i]){
                    System.out.print("1  ");
                    matchCount++;
                }
                else{
                    System.out.print("0  ");
                }
            }

            if(matchCount == newBaloons.length){
                System.out.print("\n\nGood Job!\n\n1. Start New Game\n2. Quit\n\n choose option: ");

                if(getInput.nextInt()== 1){

                    newBaloons = generateBaloons(randomIndices());
                    attemptsLeft = 3;
                }
                else{
                    break;
                }
            }
            else{
                attemptsLeft--;
                if(attemptsLeft == 0){
                    System.out.println("Game Over!\n\n1. Start New Game\n2. Quit\n\n choose option: ");

                    if(getInput.nextInt()== 1){

                        newBaloons = generateBaloons(randomIndices());
                        attemptsLeft = 3;
                    }
                    else{
                        break;
                    }
                }
            }   
        }
    }
}

/*
On GitHub
*This is a "backend prototype" of the game and I'm still working on the complete game with UI design and other minor functionalities/additionals.

What about:

1. getinput.close() to avoid "resource leak"?
2. ......
3. Using intellij
    - after each guess, play distinct sounds (ting! for correct and gg! for wrong) with 1 second delay according
      to the order of the attempts.
    - OR after each guess, display black (0) and white (1) baloons for 1 second and clear the screen.
4. Easy (3 baloons), medium (4 baloons), hard (5 baloons)
*/