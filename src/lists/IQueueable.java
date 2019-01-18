package lists;

public interface IQueueable<T>{
	void empty();
	int getLength();
	void enqueue(T pValue);
	void enqueue(T pValue, int pPriority);
	T dequeue();
}
