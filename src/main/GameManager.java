package main;

import library.Cone;
import library.IConstants;
import library.Ingredient;
import library.Order;
import lists.List;
import lists.Queue;
import ui.MainUI;

public class GameManager implements IConstants{
	
	private MainUI ui;
	private Cone actualCone;
	private Queue<Order> orders;
	private Order actualOrder;
	private List<Ingredient> ingredients;
	private boolean orderControl;
	private boolean verified;
	private int totalOrders;
	private int score;
	private int timeSinceLastOrder;
	private int actualTime;
	private int maxBalls;
	
	public GameManager(Queue<Order> pOrders, int pMaxBalls, List<Ingredient> pIngredients) {
		maxBalls = pMaxBalls;
		orders = pOrders;
		actualCone = new Cone();
		actualCone.setMaxBalls(maxBalls);
		ingredients = pIngredients;
	}
	
	public void setUI(MainUI pUI) {
		ui = pUI;
	}
	
	public MainUI getUI() {
		return ui;
	}
	
	public int getMaxballs() {
		return maxBalls;
	}
	public List<Ingredient> getIngredients(){
		return ingredients;
	}
	
	public void setactualTime(int pTime) {
		actualTime = pTime;
	}
	
	public Cone getActualCone() {
		return actualCone;
	}
	
	public Order getActualOrder() {
		return actualOrder;
	}
	
	public void changeActualOrder() {
		ui.cleanActualOrder();
		if(orders.Length() > 0) {
			timeSinceLastOrder = actualTime;
			actualOrder = orders.dequeue();
			ui.changeActualOrder();
		}else {
			orderControl = false;
		}
	}
	
	public void verifyVerificationLabel() {
		if(verified) {
			ui.removeVerificationLabel();
			verified = false;
		}
	}
	
	public int calculateScore() {
		int scoreAux = actualOrder.Length()-(timeSinceLastOrder-actualTime > PENALTY_TIME? timeSinceLastOrder-actualTime-PENALTY_TIME: 0);
		System.out.println(timeSinceLastOrder+" ultimo " +actualTime+" actual");
		if(scoreAux > 0) {
			score += scoreAux; 
		}
		return score;
	}
	
	public void updateNumberOrders(boolean updateTotal) {
		if(orders.Length() == 1 && !orderControl) {
			changeActualOrder();
			orderControl = true;
		}
		ui.updateNumberOrders(updateTotal,++totalOrders, orders.getLength());
	}
	
	public void endGame() {
		score = score-orders.getLength()*2>0?score-orders.getLength()*2:0;
		ui.endGame(score);
	}
	
	public boolean entregar() {
		verified = true;
		if(actualCone.calculateMatch(actualOrder)){
			updateNumberOrders(false);
			changeActualOrder();
			return true;
		}
		return false;
	}
}
