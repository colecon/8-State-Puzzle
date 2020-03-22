//Cole Constantino Project1 
//EECS 391
import java.util.*;

//The class AstarPuzzle with global variables
public class AstarPuzzle{
  public static String goalState = "b12345678";
  public static char[] goalArrayState = {'b','1','2','3','4','5','6','7','8'};
  public static String currentState;
  public static char[] arrayState = {'a','a','a','a','a','a','a','a','a'};
  public static ArrayList<String> path = new ArrayList<String>();
  public static int maxNodes = 1000;
  public static int moves = 0;
  public static boolean realMove = false;
  
  //empty constructor
  public AstarPuzzle(){
  }
  
  //Method to set the current state to whatever is inputed in the parameter
  public void setState(String state){
    currentState = state;
    for(int i = 0; i < 9; i++)
      arrayState[i] = currentState.charAt(i);
  }
  
  //Method to print the current state
  public void printState(){
    for(int i = 0; i<9; i++){
      System.out.print(currentState.charAt(i) + "  ");
      if(i == 2 || i == 5 || i == 8){
        System.out.println();
      }
    }
  }
  
  
  //Method to move the blank space
  public void move(String direction){
    //char[] arrayState = {};
    int blankIndex = 0;
    int tempIndex = 0;
    char tempChar = 'x';
    
    
    //Fill character array from current state
    for(int i = 0; i<goalState.length(); i++){
      arrayState[i] = currentState.charAt(i);
      //if the character is a blank space, then save the index
      if(arrayState[i] == 'b')
        blankIndex = i;
    }
    
    //Switch statement see which direction is chosen 
    switch (direction) {
      
      //Switch the characters of the box the blank space chose to move to
      case "up":
        tempIndex = blankIndex - 3;
        tempChar = arrayState[blankIndex - 3];
        arrayState[blankIndex] = tempChar;
        arrayState[tempIndex] = 'b';
        break;
        
      case "down":
        tempIndex = blankIndex + 3;
        tempChar = arrayState[blankIndex + 3];
        arrayState[blankIndex] = tempChar; 
        arrayState[tempIndex] = 'b'; 
        break;
        
      case "left":
        tempIndex = blankIndex-1;
        tempChar = arrayState[blankIndex - 1];
        arrayState[blankIndex] = tempChar;
        arrayState[tempIndex] = 'b';
        break;
        
      case "right":
        tempIndex = blankIndex + 1;
        tempChar = arrayState[blankIndex + 1];
        arrayState[blankIndex] = tempChar;
        arrayState[tempIndex] = 'b';
        break;
    }
    if(realMove == true) path.add(direction);
    
    //convert array of characters back to String
    currentState = new String(arrayState);
  }
  
  //randomize state n times is used to ensure an actual path to the goal state
  public void randomizeState(int n){
    Random random = new Random();
    //set current state to goal state and use the move method 'n' amount of times
    currentState = goalState;
    int blank = 0;
    
    //random number to determine what move the blank space should take away from the goal state
    for(int j = 0; j < n; j++){
      
      if(blank == 0){
        int number = random.nextInt(2);
        //determine move
        if(number == 0)
          move("down");
        else
          move("right");
      }
      
      else if(blank == 1){
        int number = random.nextInt(3);
        //determine move
        if(number == 0)
          move("down");
        else if(number == 1)
          move("right");
        else
          move("left");
      }
      
      
      else if(blank == 2){
        int number = random.nextInt(2);
        //determine move
        if(number == 0)
          move("down");
        else
          move("left");
      }
      
      
      else if(blank == 3){
        int number = random.nextInt(3);
        
        if(number == 0)
          move("up");
        else if(number == 1)
          move("down");
        else
          move("right");
      }
      
      
      else if(blank == 4){
        int number = random.nextInt(4);
        //determine move
        switch (number){
          case 0:
            move("up");
            break;
            
          case 1:
            move("down");
            break;
            
          case 2:
            move("left");
            break;
            
          case 3:
            move("right");
            break;
        }
      }
      
      
      else if(blank == 5){
        int number = random.nextInt(3);
        //determine move
        if(number == 0)
          move("up");
        else if(number == 1)
          move("down");
        else
          move("left");
      }
      
      
      else if(blank == 6){
        int number = random.nextInt(2);
        if(number == 0)
          move("up");
        else
          move("right");
      }
      
      
      else if(blank == 7){
        int number = random.nextInt(3);
        //determine move
        if(number == 0)
          move("up");
        else if(number == 1)
          move("right");
        else
          move("left");
      }
      
      
      else if(blank == 8){
        int number = random.nextInt(2);
        if(number == 0)
          move("up");
        else
          move("left");
      }
      
      for(int i = 0; i<goalState.length(); i++){
        //if the character is a blank space, then save the index
        if(currentState.charAt(i) == 'b')
          blank = i;
      }
    }//end loop
  }
  
  //implementation of A* search algorithm on 
  public int getHeuristic(){
    
    //heuristic 1 - how many misplaced tiles?
    int incorrectTiles = 0;
    for(int i = 0; i<9; i++){
      if(goalArrayState[i] != arrayState[i]){
        incorrectTiles++; //h1 value @ end of looop
      }
    }
    
    int heuristic1 = incorrectTiles;
    int heuristic2 = 0;
    
    //Heuristic 2 - added block distance of each tile from goal?
    for(int j = 0; j<9; j++){
      for(int k = 0; k<9; k++){
        if(arrayState[j] == goalArrayState[k]){
          heuristic2 =  heuristic2 + getManhattanDistance(j,k);
          break;
        }
      }
    }
    
    //System.out.println("Heuristic1: " + heuristic1);
    //System.out.println("Heuristic2: " + heuristic2);
    int totalHeuristic = heuristic1 + heuristic2;
    System.out.println("Total Heuristic: " + totalHeuristic);
    return totalHeuristic;
  }
  
  //Distance without going diaganol for A* search heuristic 2
  public int getManhattanDistance(int a, int b){
    
    //Syntax Key (Index, "XcoordYcoord")
    Hashtable<Integer, String> hash = new Hashtable<Integer, String>();
    //coordinates
    hash.put(0,"02");
    hash.put(1,"12");
    hash.put(2,"22");
    hash.put(3,"01");
    hash.put(4,"11");
    hash.put(5,"21");
    hash.put(6,"00");
    hash.put(7,"10");
    hash.put(8,"20");
    
    int x1 = (int)hash.get(a).charAt(0) - 48;
    int y1 = (int)hash.get(a).charAt(1) - 48;
    int x2 = (int)hash.get(b).charAt(0) - 48; 
    int y2 = (int)hash.get(b).charAt(1) - 48;
    
    int distance = Math.abs((x1-x2)+(y1-y2));  
    return distance;
  }
  
  public void aStarSearch(){
    int blank = 0;
    //Fill character array from current state
    for(int i = 0; i<goalState.length(); i++){
      arrayState[i] = currentState.charAt(i);
      //if the character is a blank space, then save the index
      if(arrayState[i] == 'b')
        blank = i;
    }
    
    int choice1;
    int choice2;
    int choice3;
    int choice4;
    
    
    
    while(!currentState.equals(goalState) && moves < maxNodes){
      realMove = false;
      
      if(blank == 0){
        move("right");
        choice1 = getHeuristic();
        move("left");
        
        move("down");
        choice2 = getHeuristic();
        move("up");
        
        realMove = true;
        
        if(choice1 < choice2)
          move("right"); 
        else 
          move("down");
      }
      
      else if(blank == 1){
        move("left");
        choice1 = getHeuristic();
        move("right");
        
        move("right");
        choice2 = getHeuristic();
        move("left");
        
        move("down");
        choice3 = getHeuristic();
        move("up");
        
        realMove = true;
        
        if(choice1 < choice2 && choice1 < choice3)
          move("left");
        else if(choice2 < choice1 && choice2 < choice3)
          move("right");
        else
          move("down");
      }
      
      else if(blank == 2){
        move("left");
        choice1 = getHeuristic();
        move("right");
        
        move("down");
        choice2 = getHeuristic();
        move("up");
        
        realMove = true;
        
        if(choice1 < choice2)
          move("left");
        else
          move("down");
      }
      
      else if(blank == 3){
        move("up");
        choice1 = getHeuristic();
        move("down");
        
        move("right");
        choice2 = getHeuristic();
        move("left");
        
        move("down");
        choice3 = getHeuristic();
        move("up");
        
        realMove = true;
        
        if(choice1 < choice2 && choice1 < choice3)
          move("up");
        else if (choice2 < choice1 && choice2 < choice3)
          move("right");
        else 
          move("down");
      }
      
      else if(blank == 4){
        move("left");
        choice1 = getHeuristic();
        move("right");
        
        move("right");
        choice2 = getHeuristic();
        move("left");
        
        move("down");
        choice3 = getHeuristic();
        move("up");
        
        move("up");
        choice4 = getHeuristic();
        move("down");
        
        realMove = true;
        
        if(choice1 < choice2 && choice1 < choice3 && choice1 < choice4)
          move("left");
        else if (choice2 < choice1 && choice2 < choice3 && choice2 < choice4)
          move("right");
        else if (choice3 < choice1 && choice3 < choice2 && choice3 < choice4)
          move("down");
        else 
          move("up");
      }
      
      else if(blank == 5){
        move("up");
        choice1 = getHeuristic();
        move("down");
        
        move("left");
        choice2 = getHeuristic();
        move("right");
        
        move("down");
        choice3 = getHeuristic();
        move("up");
        
        realMove = true;
        
        if(choice1 < choice2 && choice1 < choice3)
          move("up");
        else if (choice2 < choice1 && choice2 < choice3)
          move("left");
        else 
          move("down");
      }
      
      else if(blank == 6){
        move("up");
        choice1 = getHeuristic();
        move("down");
        
        move("right");
        choice2 = getHeuristic();
        move("left");
        
        realMove = true;
        
        if(choice1 < choice2)
          move("up");
        else
          move("right");
      }
      
      else if(blank == 7){
        move("left");
        choice1 = getHeuristic();
        move("right");
        
        move("right");
        choice2 = getHeuristic();
        move("left");
        
        move("up");
        choice3 = getHeuristic();
        move("down");
        
        realMove = true;
        
        if(choice1 < choice2 && choice1 < choice3)
          move("left");
        else if (choice2 < choice1 && choice2 < choice3)
          move("right");
        else 
          move("up");
      }
      
      else if(blank == 8){
        move("up");
        choice1 = getHeuristic();
        move("down");
        
        move("left");
        choice2 = getHeuristic();
        move("right");
        
        realMove = true;
        
        if(choice1 < choice2)
          move("up");
        else 
          move("left");    
      }
      
      moves++;
      System.out.println("end group");
      
      for(int i = 0; i<goalState.length(); i++){
        //if the character is a blank space, then save the index
        if(currentState.charAt(i) == 'b')
          blank = i;
      }
      
    }
    if(currentState.equals(goalState)){
      System.out.println("Moves to completion: " + moves);
      System.out.println("Path: " + path);
    }
    else{
      System.out.println("The puzzle was not able to be solved. \nThe algorithm either reached the max possible nodes\n or is an unsolvable case");
    }
  }
  
  
  
  //method to set max nodes
  public void maxNodes(int inputMaxNodes){
    maxNodes = inputMaxNodes;
  }
  
  public static void main(String args[]){
    //Main Method code
    AstarPuzzle puzzle = new AstarPuzzle();
    //puzzle.setState("312b45678");
    puzzle.randomizeState(7);
    puzzle.maxNodes(100);
    puzzle.printState();
    puzzle.aStarSearch();
  } 
}