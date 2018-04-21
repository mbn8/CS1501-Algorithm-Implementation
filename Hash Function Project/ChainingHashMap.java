public class ChainingHashMap{
	Node[] array;
	int size;
	
	public ChainingHashMap(int size){
		this.size = size;
		array = new Node[size];
	}

	public Integer get(Word key) {
		// Problem #1A
			
			int index = Math.floorMod(key.hashCode(), 1000);
			
			// if there is no linked list at the index, return null
			if(array[index] == null){
				return null;
			} // end if statement
			
			else{
				Node listNode = array[index]; // initialize listNode as the head node in the linked list
				
				// while the list's node isn't null, compare the keys
				while(listNode != null){
					// if the current word equals the argument
					if(listNode.word.equals(key)){
						return listNode.frequency;
					}
					else{
						listNode = listNode.next;
					}
					
				} // end while loop
				
				return null; // if the key was not found, return null
				
			} // end else statement
				
		} // end get() method

	public void put(Word key, Integer value) {
	// Problem #1B
		
		int index = Math.floorMod(key.hashCode(), 1000);
		Node newPair = new Node(key, value, null);
		
		// if there is no linked list at the index, create one
		if(array[index] == null){
			array[index] = newPair;
		} // end if statement
		
		else{
			// check if the linked list contains the key-value pair already
			Node listNode = array[index];
			
			while(listNode != null){
				
				if(listNode.word.equals(newPair.word)){
					listNode.frequency = newPair.frequency;
					return;
				}
				
				listNode = listNode.next;
			}
			
			// if the linked list does not contain the key-value pair, add the pair to the linked list
			newPair.next = array[index];
			array[index] = newPair;
			
		} // end else statement
		
	} // end put() method

	public Integer remove(Word key) {
	// Problem #1C
		
		int index = Math.floorMod(key.hashCode(), 1000);
		
		Node listNode = array[index];
		Node head = array[index];
		Node previousNode = null;
		int listSize = countCollisions(index); // size of the linked list
		
		// if there is no linked list at the index, return null
		if(array[index] == null){
			return null;
		} // end if statement
		
		else{
			
			// while the pointer's node isn't null, compare the key's
			while(listNode != null){
				
				if(listNode.word.equals(key)){
					/* REMOVE KEY-VALUE PAIR FROM MAP */
					
					// if the node to remove is at the front of the list
					if(previousNode == null){
						
						// if the node to remove is the only node in the linked list
						if(listNode.next == null){
							array[index] = null;
							return listNode.frequency;
						}
						
						else{
							array[index] = listNode.next;
							return listNode.frequency;
				
						}
						
					} // end if(previousNode == null)
					
					// if the node to remove is at the end of the linked list
					else if(listNode.next == null && previousNode != null){
						
						int returnFreq = listNode.frequency;
						
						for(int i = 0; i < listSize; i++){
							
							if(i == listSize - 2){
								break;
							}
							
							array[index] = array[index].next;
							
						} // end for loop
						
						array[index].next = null;
						array[index] = head;
						
						return returnFreq;
						
					} // end else if statement
					
					// if the node to remove is somewhere in the middle of the list
					else{
						
						int returnFreq = listNode.frequency;
						previousNode.next = listNode.next;
						
						return returnFreq;
						
					} // end else statement
					
				} // end outer if statement
				
				// if the node to remove is not the current node
				else{
					previousNode = listNode; // shift the previous node's pointer
					listNode = listNode.next; // shift the next node's pointer
				}
				
			} // end while loop
			
			return null;
		} // end else statement
		
	} // end remove() method
	
	// This method returns the total size of the underlying array.
	// In other words, it returns the total number of linked lists.
	public int getSize(){
		return size;
	}
	
	// This method counts how many keys are stored at the given array index.
	// In other words, it computes the size of the linked list at the given index.
	public int countCollisions(int index){
		if(index < 0 || index >= size) return -1;
		
		int count = 0;
		Node temp = array[index];
		while(temp != null){
			temp = temp.next;
			count++;
		}
		
		return count;
	}
	
}
