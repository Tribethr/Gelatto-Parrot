package lists;

public class Stack<T>extends List<T> implements IStackable<T>{
	public void empty() {
		clear();
	}
	public void push(T value) {
		add(value);
	}
	public T pop() {
		return removeByIndex(getLength()-1);
	}	
}
