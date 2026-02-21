package allocationProblem;

import java.util.Arrays;

/**
 * Main, executive class for the Traveling Salesman Problem.
 * 
 * We don't have a real list of cities, so we randomly generate a number of them
 * on a 100x100 map.
 * 
 * The TSP requires that each city is visited once and only once, so we have to
 * be careful when initializing a random Individual and also when applying
 * crossover and mutation. Check out the GeneticAlgorithm class for
 * implementations of crossover and mutation for this problem.
 * 
 * @author bkanber
 *
 */
public class AP {
	public static int maxGenerations = 10000;
	public static void main(String[] args) {
		System.out.println("Mohamad Obaied");
		//System.out.println(Allocation.findOptimalCost());
		//System.out.println(Arrays.toString(Allocation.findOptimalAllocation() ));
		//System.out.println("valid?: " + new Allocation(Allocation.findOptimalAllocation()).isValid());
		//System.out.println(Allocation.getNumberOfAllocations());
		//System.out.println(Allocation.getNumberOfFeasibleAllocations());
		//System.exit(0);
		
		//System.out.println("Done");
		//int a[] = {0,4,6,6,0,5,3,7,1,5,1};
		//System.out.println(new Allocation(a).isValid());
		//System.out.println(new Allocation(a).cost());
		//System.out.println(Arrays.toString(Allocation.findOptimalAllocation()) );
		//System.out.println("valid?: " + new Allocation(Allocation.findOptimalAllocation()).isValid());
		//System.exit(0);
		
		int m = Allocation.m;
		int n = Allocation.n;
		
		System.out.println("Start");
		
		// Initial GA
		GeneticAlgorithm ga = new GeneticAlgorithm(80, 0.05, 0.95, 2, 5);

		// Initialize population
		Population population = ga.initPopulation(n,m);

		// Evaluate population
		ga.evalPopulation(population);

		Allocation startAllocation = new Allocation(population.getFittest(0));
		System.out.println("Start Cost: " + startAllocation.cost());

		final long startTime = System.nanoTime();
		// Keep track of current generation
		int generation = 1;
		// Start evolution loop
		while (ga.isTerminationConditionMet(generation, maxGenerations) == false) {
			// Print fittest individual from population
			Allocation alloc = new Allocation(population.getFittest(0));
			System.out.println("G"+generation+" Best cost: " + alloc.cost());

			// Apply crossover
			population = ga.crossoverPopulation(population);

			// Apply mutation
			population = ga.mutatePopulation(population);

			// Evaluate population
			ga.evalPopulation(population);

			// Increment the current generation
			generation++;
		}
		long endTime = System.nanoTime();
		double executionTime = (endTime - startTime) / 1000000000.0;
		System.out.println("Execution Time (in seconds): " + executionTime); 
		System.out.println("Stopped after " + maxGenerations + " generations.");
		Allocation bAlloc = new Allocation(population.getFittest(0));
		System.out.println("Best cost: " + bAlloc.cost());
		System.out.println("valid?: " + bAlloc.isValid());
		System.out.println(bAlloc);
		System.out.println("Done.");

	}
}
