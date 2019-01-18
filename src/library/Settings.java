package library;

public class Settings {
	
	private int maxBalls;
	private int timeToOrder;
	private int priorityOrderProbability;
	private int maxToppings;
	private int maxOrder;
	private int minOrder;
	
	public Settings(int pMaxBalls, int pTimeToOrder, int pPriorityOrderProbability, int pMaxToppings, int pMaxOrder, int pMinOrder) {
		maxBalls = pMaxBalls;
		timeToOrder = pTimeToOrder;
		priorityOrderProbability = pPriorityOrderProbability;
		maxToppings = pMaxToppings;
		maxOrder = pMaxOrder;
		minOrder = pMinOrder;
	}
	public int getMaxBalls() {
		return maxBalls;
	}

	public int getTimeToOrder() {
		return timeToOrder;
	}

	public int getPriorityOrderProbability() {
		return priorityOrderProbability;
	}


	public int getMaxToppings() {
		return maxToppings;
	}

	public int getMaxOrder() {
		return maxOrder;
	}

	public int getMinOrder() {
		return minOrder;
	}
	
	@Override
	public String toString() {
		return "Settings [maxBalls=" + maxBalls + ", timeToOrder=" + timeToOrder + ", priorityOrderProbability="
				+ priorityOrderProbability + ", maxToppings=" + maxToppings + ", maxOrder=" + maxOrder + ", minOrder="
				+ minOrder + "]";
	}
}
