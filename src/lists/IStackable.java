package lists;

public interface IStackable<T> {
	void empty();
	void push(T value);
	T pop();
}
