
public class KnapsackSolver {

	public static int[][] buildTable(Order[] orders, int costLimit, int timeLimit){
	// Problem #1
	
		int[][] table = new int[costLimit+1][timeLimit+1];
		
		for(int row = 0; row < costLimit+1; row++){
			
			for(int col = 0; col < timeLimit+1; col++){
				int currentBest = 0; // the highest amount of previous cookies
				int temp = 0;
				
				// for loop to determine the previous cell to add into the current cell
				for(int k = 0; k < orders.length; k++){
					int previousRow = row - orders[k].cost;
					int previousCol = col - orders[k].time;
					
					if(previousRow < 0 || previousCol < 0){
						temp = -1;
					}
					else{
						temp = table[previousRow][previousCol];
						temp += orders[k].numberOfCookies;
					}
					
					if(temp >= currentBest){
						currentBest = temp;	
					}
						
				} // end inner-most for loop
				
				table[row][col] = currentBest;
			
			} // end of outer-inner for loop (for columns)
			
		} // end of outer-most for loop (for rows)
	
		return table;
	}

	public static Multiset solve(Order[] orders, int costLimit, int timeLimit){
	// Problem #2
		
		int[][] table = buildTable(orders, costLimit, timeLimit);
		Multiset set = new Multiset();
		
		set = backtrack(table, costLimit, timeLimit, orders, set);
	
		return set;
	}
	
	public static Multiset backtrack(int[][] table, int row, int col, Order[] orders, Multiset set){
		
		// Base case: when you reach a cell of 0 cookies
		if(table[row][col] == 0){
			return set;
		}
		
		int previousRow = 0;
		int previousCol = 0;
		int temp = 0;
		
		for(int i = 0; i < orders.length; i++){
			previousRow = row - orders[i].cost;
			previousCol = col - orders[i].time;
			
			try{
				temp = table[previousRow][previousCol];
			} catch(ArrayIndexOutOfBoundsException e){
				continue;
			}
			
			// check if temp + orders[i].numberOfCookies = table[row][col]
			int checkEqual = temp + orders[i].numberOfCookies;
			
			if(checkEqual == table[row][col]){
				set.add(orders[i]);
				break;
			}
		
		} // end for loop
		
		return backtrack(table, previousRow, previousCol, orders, set);
		
	}
	
}