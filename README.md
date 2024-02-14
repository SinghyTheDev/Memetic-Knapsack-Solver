# Memetic Knapsack Solver

## Problem Description
The Knapsack Problem is a combinatorial optimization problem in which you need to select a subset of items from a given list, each with a specific weight and profit, to maximize the total profit while staying within a specified weight (capacity) constraint.

The Memetic Algorithm is a population-based optimization/search AI algorithm which combines principles of genetic algorithms (GAs) with local search techniques to solve optimization problems.

## Algorithm Overview
The Memetic Algorithm implemented in this code involves the following components:

1. **Initialization:** Initial solutions are generated as either random solutions, or greedy heuristic solutions based on profit per unit weight.

2. **Main Loop:** The main loop iteratively refines solutions over a predefined number of generations. In each generation, the algorithm performs the following steps:

   - **Tournament Selection:** Selects two parent solutions based on their fitness (profit).
   - **Crossover:** Combines the bit representations of the selected parent solutions to create two offspring solutions.
   - **Random Mutation:** Introduces random mutations to the offspring solutions.
   - **Local Search (Davis-Bit Hill Climbing):** Applies local search to the offspring solutions to improve their quality.
   - **Strong Elitism Replacement:** Replaces the worst solutions in the population with the improved offspring solutions, maintaining a strong elitism.
   - **Objective Value Tracking:** The best and worst objective values in each generation are tracked for later analysis.

3. **Output:** At the end of each trial, the algorithm stores the results, including the best solution's bit representation, profit, and the best and worst objective values at each generation, in a text file.

## How to Run
To run the program, run main class in Main. Algorithm configurations are set in Configurations class. Test instances are in folder /testInstances. Trial output files are in folder /trialOutputs.