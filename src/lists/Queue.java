package lists;

public class Queue<T> extends List<T> implements IQueueable<T>{
	
	public int Length() {
		return getLength();
	}
	
	public void empty() {
		clear();	
	}
	
	public void enqueue(T pValue) {
		add(pValue);
	}
	
	public void enqueue(T pValue, int pPriority) {
		insertWithPriority(pValue, pPriority);
	}
	
	public T dequeue() {
		return removeByIndex(0);
	}
}
