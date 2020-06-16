# 8-State-Puzzle
Search algorithm to solve an 8-state puzzle.

The puzzle begins with scrambled tiles, and the goal is to move the tiles (or equivalently the blank “tile”) so that the blank tile in the upper left corner and the numbers are in order as shown.

  
Solve the puzzle from its current state using A-star search using heuristic equal to “h1” or “h2”. Briefly, h1 is the number of misplaced tiles; h2 is the sum of the distances of the tiles from their goal positions. When the goal is found, the code should print the number of tile moves needed to obtain the solution followed by the solution as a sequence of moves (up, down, left, or right) from the starting state to the goal state.



# Required Methods:
setState(state)
printState()
move(direction)
randomizeState()
solve_A_star(heuristics)
maxNodes(n)

# NOTE: 
This is was written without reference to A* search in 2019 and does not follow its format. Self interpreted with a similiar decision idea but not optimized. In April of 2020 I built an A* search path-finding website that does conform to optimized A* Search concepts. 

Here is the Link: https://ai-path-visualizer.web.app
