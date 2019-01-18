package lists;

import static util.Randoms.randInt;

public class List<T> {
	private Node<T> First;
	private Node<T> Last;
	private Node<T> actualNode;
	private int length;
	
	public void clear() {
		First = null;
		Last = null;
		length = 0;
	}
	
	public boolean isEmpty() {
		return length==0;
	}
	
	public int getLength() {
		return length;
	}
	
	public T getValue(int index) {
		if(index < length && !isEmpty()) {
			return getNodeAtPos(index).getValue();
		}
		return null;
	}
	
	public void setValue(T value,int index) {
		if(index < length && !isEmpty() && index>0) {
			getNodeAtPos(index).setValue(value);
		}
	}
	
	public void add(T value) {
		Node<T> newNode = new Node<T>(value);
		if (!isEmpty()) {
			linkNodes(Last, newNode, First);
			Last = newNode;
		} else {
			First = newNode;
			Last = newNode;
			linkNodes(First, Last);
		}
		length++;
	}
	private void add(T value, int pPriority) {
		Node<T> newNode = new Node<T>(value);
		newNode.setPriority(pPriority);
		if (!isEmpty()) {
			linkNodes(Last, newNode, First);
			Last = newNode;
		} else {
			First = newNode;
			Last = newNode;
			linkNodes(First, Last);
		}
		length++;
	}
	
	public boolean removeByValue(T value) {
		if(!isEmpty()) {
			Node<T> searchPointer = First;
			for(int i = 0; i<length; i++) {
				if(searchPointer.getValue() == value) {
					removeNode(searchPointer);
					return true; 
				}
				searchPointer = searchPointer.getNext();
			}
		}
		return false;
	}
	
	public T removeByIndex(int index) {
		if (!isEmpty() && index< length) {
			Node<T> searchPointer = getNodeAtPos(index);
			removeNode(searchPointer);
			return searchPointer.getValue();
		}
		return null;
	}
	
	public void insert (T value, int index) {
		if(index>=length || isEmpty()) {
			add(value);
			return;
		}
		Node<T> searchPointer = getNodeAtPos(index);
		Node<T> newNode = new Node<T>(value);
		if(searchPointer == First) {
			linkNodes(Last,newNode,First);
			First = newNode;
		}else {
			linkNodes(searchPointer.getPrevious(), newNode, searchPointer);
		}
		length++;
		
	}
	
	public void scramble() {
		for(int i = 0; i<getLength(); i++) {
			add(removeByIndex(randInt(0, getLength()-1)));
		}
	}
	
	public T iterValues(int i) {
		if(i == 0) {
			actualNode = First;
		}
		T value = actualNode.getValue();
		actualNode = actualNode.getNext();
		return value;
	}
	
	public Object[] toArray() {
		Object[] toReturn = new Object[length];
		Node<T> searchPointer = First;
		for (int i = 0; i<length; i++) {
			toReturn[i] = searchPointer.getValue();
			searchPointer = searchPointer.getNext();
		}
		return toReturn;
	}
	
	public void shift() {
		add(removeByIndex(0));
	}

	protected void insertWithPriority(T pValue, int pPriority) {		
		if(pPriority <= 0 || isEmpty()) {
			add(pValue,pPriority);
		}
		Node<T> searchPointer = First;
		for(int i = 0; i<getLength(); i++) {
			if(searchPointer.getPriority() < pPriority) {
				Node<T> newNode = new Node<T>(pValue);
				newNode.setPriority(pPriority);
				if(searchPointer == First) {
					linkNodes(Last,newNode,First);
					First = newNode;
				}else {
					linkNodes(searchPointer.getPrevious(), newNode, searchPointer);
				}
				length++;
				break;
			}
			searchPointer = searchPointer.getNext();
		}
	}
	
	private void linkNodes(Node<T> pPrevious, Node<T> pNext) {
		pPrevious.setNext(pNext);
		pNext.setPrevious(pPrevious);
	}
	
	private void linkNodes(Node<T> pPrevious, Node<T> pActual, Node<T> pNext) {
		linkNodes(pPrevious,pActual);
		linkNodes(pActual,pNext);
	}
	
	private Node<T> getNodeAtPos(int index){
		Node<T> searchPointer = First;
		for (int i = 0; i< index; i++) {
			searchPointer = searchPointer.getNext();
		}
		return searchPointer;
	}
	
	private void removeNode(Node<T> searchPointer) {
		if(searchPointer == First) {
			First = First.getNext();
			linkNodes(Last, First);
		}else if(searchPointer == Last) {
			Last = Last.getPrevious();
			linkNodes(Last, First);
		}else {
			linkNodes(searchPointer.getPrevious(), searchPointer.getNext());
		}
		length--;
	}
	@Override
	public String toString() {
		String list = "[";
		Node<T> searchPointer = First; 
		for(int i = 0; i<length; i++) {
			list+= searchPointer.getValue()+",";
			searchPointer = searchPointer.getNext();
		}
		if(list.length() == 1) {
			return list+"]";
		}
		return list.substring(0, list.length()-1)+"]";
	}
}
