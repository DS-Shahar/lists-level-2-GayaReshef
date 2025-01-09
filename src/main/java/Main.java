package List;

public class Main {

	public static void main(String[]args) {
		int [] arr1= {9, 2, 8, 3, 8, 5};
		int [] arr2= { 7, 8, 9};
		Node<Integer> list1 = buildList(arr1);
		Node<Integer> list2 = buildList(arr2);

	    // System.out.println(ex1(list1, list2));
	        
	    // System.out.println("ex2A- " + createNewSortList_ex2(list1));
	      
	    // System.out.println("ex2B- " + sortList_ex2B(list1));
	      
	    // System.out.println(countHowFarIntFromEnd_ex3(list1, 8));
	      
	    // System.out.println ( "ex4- " + checkIfAllNodesAreDifferent_ex4(list1));
	      
	    // System.out.println ("ex5- " + deleteSameNodesInNewList_ex5(list1));
	}


	
	public static Node<Integer> buildList (int []arr) {
		Node<Integer> head = new Node<>(arr[0]);
		Node<Integer> prev = head;
		for(int i=1; i<arr.length; i++) {
			Node <Integer> p = new Node <Integer>(arr[i]);
			prev.setNext(p);
			prev=p;
		}
		return head;
	}
	
	
	public static Node<Integer> ex1 (Node<Integer> list1, Node<Integer> list2){
	
	Node<Integer> current3 = new Node <> (0);;
	Node<Integer> list3 = current3;
	Node<Integer> current1 = list1;
	Node<Integer> current2 = list2;

	
	while (current1 != null && current2 != null) {
		if (current1.getValue() < (current2.getValue())){
			current3.setNext(current1);
			current1 = current1.getNext();	
		}
		else if (current2.getValue() < (current1.getValue())){
			current3.setNext(current2);
			current2 = current2.getNext();
		}
		else {
		    current3.setNext(current1); 
            current1 = current1.getNext();  
            current3 = current3.getNext();  
            current3.setNext(current2);  
            current2 = current2.getNext();  
		}
		current3 = current3.getNext();  
	}
		
	if (current1 == null) {
	    current3.setNext(current2);
	} else {
	    current3.setNext(current1);
	}
	return list3.getNext();
	}
	
	
	public static int findMin_ex2 (Node<Integer> list){
		Node<Integer> current = list;
		Node<Integer> minNode = list;
		
		while (current != null) {
			if (current.getValue() < minNode.getValue()) {
				minNode = current;
			}
			current = current.getNext();
		}
		return minNode.getValue();
	}
	
	
	public static Node<Integer> deleteMin_ex2 (Node<Integer> list){
		Node<Integer> dummy = new Node<>(-1, list);
		Node<Integer> head = dummy;
		while (dummy.getNext().getValue() != findMin_ex2(list)) {
			dummy = dummy.getNext();
		}
		dummy.setNext(dummy.getNext().getNext());	
		return head.getNext();
	}


	public static Node<Integer> createNewSortList_ex2 (Node<Integer> list){ 
		Node<Integer> sortedList = new Node <> (null);
		Node<Integer> head = sortedList;
		while (list != null) {
			sortedList.setNext(new Node<> (findMin_ex2(list)));
			sortedList = sortedList.getNext();
			list = deleteMin_ex2 (list);
		}
		return head.getNext();
	}

	
	public static Node<Integer> sortList_ex2B (Node<Integer> list){
		Node <Integer> head = list;
		Node <Integer> current = head.getNext();
		Node <Integer> minNode = head;

		while (head != null) {
			current = head.getNext();
			minNode = head;
			while (current != null) {
				if (current.getValue() < minNode.getValue()) {
					minNode = current;
				}
				current = current.getNext();
			}
			int temp = head.getValue();
			head.setValue(minNode.getValue());
			minNode.setValue(temp);
			
			head = head.getNext();
		}
		return list;

	}
	
	
	public static int countHowFarIntFromEnd_ex3 (Node<Integer> list, int number){
		Node <Integer> current = list;
		boolean found = false;
		while (current != null) {
			if (current.getValue().equals(number)) {
				found = true;
			}
			current = current.getNext();
		}
		if (found == false) {
			return -1;
		}
		current = list;
		int count = 0;
		while (current.getValue() != number) {
			count++;
			current = current.getNext();
		}
		
		if (current.getNext() == null) {
			return count;
		}
	
		 current = current.getNext();
		 while (current.getValue() != number) {
			 current = current.getNext();
		 }
		 
		current = current.getNext();
		while (current != null ) {
			count++;
			current = current.getNext();
		}
		
		return count;
	}
	
	
	
	public static boolean checkIfAllNodesAreDifferent_ex4 (Node<Integer> list) {
		Node <Integer> head = list;
		Node <Integer> next = head.getNext();
		boolean flag = true;
		
		while (head != null) {
			next = head.getNext();
			while (next != null && flag == true) {
				if (head.getValue() == next.getValue()) {
					flag = false;
				}
				else {
					next = next.getNext();
				}
			}
			head = head.getNext();
		}
		return flag;
		
	}
	
	public static Node<Integer> deleteSameNodesInNewList_ex5(Node<Integer> list) {
	    Node<Integer> current = list;
	    Node<Integer> newList = new Node<>(current.getValue());  
	    Node<Integer> currentNewList = newList;  
	    Node<Integer> lastInNewList = newList;  
	    boolean found;

	    current = current.getNext();  

	    while (current != null) {
	        currentNewList = newList;
	        found = false;

	        while (currentNewList != null) {
	            if (current.getValue().equals(currentNewList.getValue())) {
	                found = true;  
	            }
	            currentNewList = currentNewList.getNext();
	        }

	        if (found == false) {
	            lastInNewList.setNext(new Node<>(current.getValue()));  
	            lastInNewList = lastInNewList.getNext();  
	        }

	        current = current.getNext(); 
	    }

	    return newList;  
		
	}

	public static int ex6WeaklyInreasing(IntNode head) {
    int maxLength = 1;

    IntNode current = head;

    while (current != null) {
        int currentLength = 1;
        IntNode p = current;

        while (p.hasNext() && p.getNext().getValue() >= p.getValue()) {
            currentLength++;
            p = p.getNext();
        }

        if (currentLength > maxLength) {
            maxLength = currentLength;
        }

        current = current.getNext();
    }

    return maxLength;
}

}
