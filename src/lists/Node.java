package lists;

public class Node<T> {
	private T Value	;
	private Node<T> Next;
	private Node<T> Previous;
	private int priority;
	
	
	public Node(T pValue) {
		Value = pValue;
		priority = 0;
	}
	public Node(T pValue, Node<T> pPrevious, Node<T> pNext) {
		Value = pValue;
		Previous = pPrevious;
		Next = pNext;
		priority = 0;
	}

	public T getValue() {
		return Value;
	}

	public void setValue(T value) {
		Value = value;
	}

	public Node<T> getNext() {
		return Next;
	}

	public void setNext(Node<T> next) {
		Next = next;
	}
	public Node<T> getPrevious() {
		return Previous;
	}

	public void setPrevious(Node<T> previous) {
		Previous = previous;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int pPriority) {
		priority = pPriority;
	}
	
}
