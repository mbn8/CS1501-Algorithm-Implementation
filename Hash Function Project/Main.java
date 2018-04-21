
public class Main {
	public static void main(String[] args){
		Datum[] dataArray = DataReader.loadData();
		ChainingHashMap map = new ChainingHashMap(1000);
		
		// Populate the map with words and their corresponding frequencies
		for(int i=0; i<dataArray.length; i++)
			map.put(dataArray[i].word, dataArray[i].frequency);
		
		// Evaluate the effectiveness of the hash function
		int sizeOfLargestList = collisionTest(map);
		int numberOfEmptyLists = sparsityTest(map);
		
		// Print the results
		System.out.println("The size of the largest linked list is: " + sizeOfLargestList);
		System.out.println("The total number of empty linked lists is: " + numberOfEmptyLists);
	}
	
	public static int collisionTest(ChainingHashMap map){
	// Problem #2A
		
		int totalSize = map.getSize();
		int max = 0;
		int temp = 0;
		
		for(int i = 0; i < totalSize; i++){
			temp = map.countCollisions(i);
			
			if(temp > max){
				max = temp;
			}
			
		}
		
		return max;
	}
	
	public static int sparsityTest(ChainingHashMap map){
	// Problem #2B
		
		int totalSize = map.getSize();
		int numEmpty = 0;
		
		for(int i = 0; i < totalSize; i++){
			int check = map.countCollisions(i);
			
			if(check == 0){
				numEmpty++;
			}
			
		}
		
		return numEmpty;
	}
}
