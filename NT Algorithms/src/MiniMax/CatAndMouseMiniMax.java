package MiniMax;
public class CatAndMouseMiniMax {
	    private static int minimax(int[][] graph, int catPosition, int mousePosition, int turn, Integer[][][] dp, boolean flag) {
		if (catPosition == mousePosition) {
			return -1;
		}
		if (mousePosition == 0) {
			return 1;
		}
		if (turn == graph.length * 2) {
			return 0;
		}

		if (dp[catPosition][mousePosition][turn] != null) {
			return dp[catPosition][mousePosition][turn];
		}
		int result = turn % 2 == 0 ? -1 : 1;
		if (turn % 2 == 0) {
			int maximumValue = Integer.MIN_VALUE;
			for(int i = 0; i < graph[mousePosition].length; i++) {
			    // mousePosition is trying to maximize
				if(flag) {
				System.out.println("catPosition");
				System.out.println(catPosition);
				System.out.println("mousePosition");
				System.out.println(graph[mousePosition][i]);
				System.out.println("\n");
				int evaluatedValue = minimax(graph, catPosition, graph[mousePosition][i], turn + 1, dp, flag);
				maximumValue = Math.max(evaluatedValue, maximumValue);
				return maximumValue;
				}
			}
		} else if(flag) {
			int minimumValue = Integer.MAX_VALUE;
			for(int i = 0; i < graph[catPosition].length; i++) {
				if (graph[catPosition][i] == 0) continue;
				// catPosition is trying to minimize
				System.out.println("mousePosition");
				System.out.println(mousePosition);
				System.out.println("catPosition");
				System.out.println(graph[+catPosition][i]);
				System.out.println("\n");
				int evaluatedValue = minimax(graph, graph[catPosition][i], mousePosition, turn + 1, dp, flag);
				minimumValue = Math.min(evaluatedValue, minimumValue);
				return minimumValue;
			}
		}
		dp[catPosition][mousePosition][turn] = result;
		return result;
	}
    public static void main(String args[]) {
    	int[][] graph = {{5,7,9},{3,4,5,6},{3,4,5,8},{1,2,6,7},{1,2,5,7,9},{0,1,2,4,8},{1,3,7,8},{0,3,4,6,8},{2,5,6,7,9},{0,4,8}};
//    	int[][] graph = {{1,3},{3},{3},{1,2}};
//    	int[][] graph = {{3,4},{3,5},{3,6},{0,1,2},{0,5,6},{1,4},{2,4}};
    	int result = Integer.MIN_VALUE;
    	int graphLength = graph.length;
		boolean flag = true;
    	for(int i = 0; i < graph[1].length; i++) {
    		int maximumValue = minimax(graph, 2, graph[1][i], 0, new Integer[graphLength][graphLength][graphLength * 2], flag);
    		if(maximumValue > result || result == Integer.MIN_VALUE) {
    			result = maximumValue;
    		}
    	System.out.println(result);	
    	}
    }
}
