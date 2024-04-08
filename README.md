# Memetic Knapsack Solver

## Problem Description
The Knapsack Problem is a combinatorial optimization problem in which you need to select a combination of items from a list of items, each with a weight and profit, which maximize the total profit whilst staying within the weight limit.

The Memetic Algorithm is a population-based optimization AI algorithm which combines principles of genetic algorithms (GAs) with local search techniques to solve optimization problems.

## Algorithm Overview
The Memetic Algorithm implemented in this code involves the following:

1. **Initialization:** Initial solutions are generated as either random solutions or greedy heuristic solutions based on profit per unit weight.

2. **Main Loop:** The main loop iteratively optimizes solutions over a number of generations. In each generation, the algorithm performs the following steps:

   - **Tournament Selection:** Selects two parent solutions based on their fitness (profit).
   - **Crossover:** Combines the bit representations of the selected parent solutions to create two offspring solutions.
   - **Random Mutation:** Introduces random mutations to the offspring solutions.
   - **Local Search (Davis-Bit Hill Climbing):** Applies local search to the offspring solutions to improve their quality.
   - **Strong Elitism Replacement:** Replaces the worst solutions in the population with the improved offspring solutions to maintain the strongest genes.

3. **Output:** At the end of each trial the algorithm stores the results in a text file, including the best solutions bit representations, profits, and best and worst values at each generation.

## How to Run
To run the program, run main function in Main. Hyperparamaters are in Configurations class. Test instances are in the /testInstances folder. Trial output files are in the /trialOutputs folder.
