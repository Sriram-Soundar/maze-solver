# Maze Solver

## Overview

This Java project provides implementations of two graph traversal algorithms, Depth First Search (DFS) and Breadth First Search (BFS), to solve a maze. The project includes classes for maze representation, graph traversal algorithms, and a main class to demonstrate the usage.

## Key Learning Points

- **Depth First Search (DFS):**
  - Implemented using a stack.
  - The `solveDFS` method in the `Algorithms` class provides a DFS solution to the maze.

- **Breadth First Search (BFS):**
  - Implemented using a queue.
  - The `solveBFS` method in the `Algorithms` class provides a BFS solution to the maze.

## Classes

### `Algorithms`

- Provides static methods `solveDFS` and `solveBFS` for maze solving.
- Uses the `getAllPossibleMoves` helper method to get possible moves in all four cardinal directions.

### `Maze`

- Represents the maze and includes methods for maze generation using a recursive DFS algorithm.
- Defines cardinal directions (`DIRECTION` enum) and a `Move` class for navigating the maze.

### `Move`

- Represents a move in the maze with row and column indices.
- Includes methods for testing equivalence and a `toString` method.

### `Node`

- Represents a node in the maze with binary variables indicating edges in north, east, south, and west directions.
- Includes a `STATUS` enum to represent the state of the node (UNVISITED, IN_PROGRESS, FINISHED).

## Usage

### DFS Algorithm

To use the DFS algorithm for solving a maze, call the static method `solveDFS` from the `Algorithms` class, passing in the maze to be solved.

```java
Maze solvedMazeDFS = Algorithms.solveDFS(new Maze(10));
System.out.println(solvedMazeDFS);
```

### BFS Algorithm

For the BFS algorithm, use the static method solveBFS in a similar manner.

```java
Copy code
Maze solvedMazeBFS = Algorithms.solveBFS(new Maze(10));
System.out.println(solvedMazeBFS);
```
