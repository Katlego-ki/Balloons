# Balloons
Guess the order of N colours.



## On GitHub
*The "Baloons.java" file is a "prototype" of the game and it is run using command promp while The Other files "Main.java" , "Back/FrontEnd.java" will require an IDE because of the libraries used (e.g swing).

## Running "Baloons.java"
  - input your guess of the colour order as a string where Y-yellow, R-red, G-green, and B-blue
    e.g   

    You have 3 attempts left!

    Your guess : BRGY       | if you think the order is Blue-Red-Green-Yellow.

    0 1 1 0                 | 0 means the corresponding colour you chose is wrong and 1 means it's correct

    You have 2 attempts left!  
.............

## Graphics and other UI additions :

1. Use fillOval() method for the baloons.

2. Gameplay:
    - after each guess, play distinct sounds (ting! for correct and gg! for wrong) with 1 second delay according
      to the order of the attempts.
    - OR after each guess, display black (0) and white (1) baloons for 1 second and clear the screen.

3. Easy (3 baloons), medium (4 baloons), hard (5 baloons).
