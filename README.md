# 8-State-Puzzle
A star search algorithm to solve an 8-state puzzle.

The puzzle begins with scrambled tiles, and the goal is to move the tiles (or equivalently the blank “tile”) so that the blank tile in the upper left corner and the numbers are in order as shown.

  
Solve the puzzle from its current state using A-star search using heuristic equal to “h1” or “h2”. Briefly, h1 is the number of misplaced tiles; h2 is the sum of the distances of the tiles from their goal positions.  You are free to try other heuristics, but be sure that they are admissible and describe them in your writeup.  When the goal is found, the code should print the number of tile moves needed to obtain the solution followed by the solution as a sequences of moves (up, down, left, or right) from the starting state to the goal state.
