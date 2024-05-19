# Memetic Knapsack Solver

## Problem Description
The Knapsack Problem is a combinatorial optimization problem in which you select a combination of items from a list of items, each with a profit and weight attached, and try to maximize the total profit possible whilst staying under the weight limit of your backpack.

Evolutionary algorithms are a field of AI inspired by the evolutionary process of life. Genetic algorithms are a branch of evolutionary algorithms which specifically mimic the principle of genetics and survival of the fittest.

The Memetic Algorithm is a population-based evolutionary optimization algorithm which combines principles of genetic algorithms (GAs) with local search techniques and is used to solve optimization problems such as the Knapsack Problem.

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
To run the program, run main function in Main. Hyperparamaters are in Configurations class. Test instances are in /testInstances folder. Trial output files are in /trialOutputs folder.
