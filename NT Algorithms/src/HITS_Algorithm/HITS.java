package HITS_Algorithm;

import java.util.Scanner;

public class HITS {
public static void main(String args[]) {
	int[][] graph = {{0,1},{1,2},{2,3},{3,4},{2,5},{5,8},{6,7},{7,8},{8,9},{9,10}, {11,10}};
	int graphLength = graph.length;
	System.out.println("Enter the number of iterations:");
	Scanner in = new Scanner(System.in);
	int iterations = in.nextInt();
	int[][] adjacencyMatrix = new int[graphLength + 1][graphLength + 1];
	for(int i = 0; i < graphLength; i++) {
		for(int j = 0; j < graphLength; j++) {
			adjacencyMatrix[i][j] = 0;
		}
	}
	for(int[] nodes : graph) {
		adjacencyMatrix[nodes[0]][nodes[1]] = 1;
	}
	double[] hubArray = new double[graphLength];
	double[] authorityArray = new double[graphLength];
	for(int i = 0; i < graphLength; i++) {
		hubArray[i] = 1;
		authorityArray[i] = 1;
	}
	hitsAlgorithm(graphLength, graph, hubArray, authorityArray, iterations, adjacencyMatrix);
}

private static void hitsAlgorithm(int graphLength, int[][] graph, double[] hubArray,
		double[] authorityArray, int iterations, int[][] adjacencyMatrix) {
	double[] newHubArray = new double[graphLength];
	double[] newAuthorityArray = new double[graphLength];
	double[] prevHubArray = new double[graphLength];
	double[] prevAuthorityArray = new double[graphLength];
	double hubScaleFactor = 0.0;
	double hubSumOfSquare = 0.0;
	double authoritySumOfSquare = 0.0;
	double authorityScaleFactor = 0.0;
	System.out.print("Iterations:    0 :");
	for(int i = 0; i < graphLength; i++) {
		newHubArray[i] = hubArray[i];
		newAuthorityArray[i] = authorityArray[i];
		prevHubArray[i] = newHubArray[i];
		prevAuthorityArray[i] = newAuthorityArray[i];
		System.out.println();
        System.out.printf(" A/H[%d]=%.6f/%.6f",i,Math.round(authorityArray[i]*1000000.0)/1000000.0,Math.round(hubArray[i]*1000000.0)/1000000.0);
	}
	if(iterations != 0) {
		for(int i = 0; i < iterations; i++) {
			doHITS(graphLength, graph, hubArray, authorityArray, iterations, adjacencyMatrix,
					newHubArray, newAuthorityArray, hubScaleFactor, hubSumOfSquare, authoritySumOfSquare, authorityScaleFactor);
			System.out.println();
            System.out.print("Iterations:    " + (i+1) + " :");
            for(int j = 0; j < graphLength; j++) {
            	System.out.println();
                System.out.printf(" A/H[%d]=%.6f/%.6f",j,Math.round(newAuthorityArray[j]*1000000.0)/1000000.0,Math.round(newHubArray[j]*1000000.0)/1000000.0); 
            }
		}
	} else {
		int iteration = 0;
		do {
			for(int i = 0; i < graphLength; i++) {
				prevAuthorityArray[i] = newAuthorityArray[i];
				prevHubArray[i] = newHubArray[i];
			}
			doHITS(graphLength, graph, hubArray, authorityArray, iterations,
					adjacencyMatrix, newHubArray, newAuthorityArray, hubScaleFactor, hubSumOfSquare, authoritySumOfSquare, authorityScaleFactor);
			System.out.println();
            System.out.print("Iterations:    " + (iteration++) + " :");
            for(int j = 0; j < graphLength; j++) {
            	System.out.println();
                System.out.printf(" A/H[%d]=%.6f/%.6f",j,Math.round(newAuthorityArray[j]*1000000.0)/1000000.0,Math.round(newHubArray[j]*1000000.0)/1000000.0);
            }
		} while(false == isConverged(newAuthorityArray, prevAuthorityArray, graphLength) || false == isConverged(newHubArray, prevHubArray, graphLength));
	}
}


private static void doHITS(int graphLength, int[][] graph, double[] hubArray, double[] authorityArray,
		int iterations, int[][] adjacencyMatrix, double[] newHubArray, double[] newAuthorityArray, double hubScaleFactor, double hubSumOfSquare,
		double authoritySumOfSquare, double authorityScaleFactor) {
	for(int i = 0; i < graphLength; i++) {
		for(int j = 0; j < graphLength; j++) {
			if(adjacencyMatrix[j][i] == 1)
				newAuthorityArray[i] += newHubArray[j];
		}
	}
	for(int i = 0; i < graphLength; i++) {
		for(int j = 0; j < graphLength; j++) {
			if(adjacencyMatrix[i][j] == 1)
				newHubArray[i] += newAuthorityArray[j];
		}
	}
	authorityScaleFactor = 0.0;
	authoritySumOfSquare = 0.0;
	for(int i = 0; i < graphLength; i++) {
		authoritySumOfSquare += newAuthorityArray[i] * newAuthorityArray[i];
	}
	authorityScaleFactor = Math.sqrt(authoritySumOfSquare);
	for(int i = 0; i < graphLength; i++) {
		newAuthorityArray[i] += newAuthorityArray[i]/authorityScaleFactor;
	}
	hubScaleFactor = 0.0;
	hubSumOfSquare = 0.0;
	for(int i = 0; i < graphLength; i++) {
		hubSumOfSquare += newHubArray[i] * newHubArray[i];
	}
	hubScaleFactor = Math.sqrt(hubSumOfSquare);
	for(int i = 0; i < graphLength; i++) {
		newHubArray[i] += newHubArray[i]/hubScaleFactor;
	}
	
}

private static boolean isConverged(double[] newAuthorityArray, double[] prevAuthorityArray, int graphLength) {
	for(int i = 0 ; i < graphLength; i++) {
        if (Math.abs(prevAuthorityArray[i] - newAuthorityArray[i]) > 0.000001) 
          return false;
    }
    return true;
}
}
