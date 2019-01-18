package main;

import lists.List;
import lists.Queue;
import library.Order;
import library.IceCreamBall;
import library.Ingredient;
import library.Settings;
import javax.swing.JLabel;

import static util.Randoms.randInt;
import static util.Randoms.randTrueInProbability;

public class CronController extends Thread{
	
	private Queue<Order> orders;
	private Settings settings;
	private List<Ingredient> balls;
	private List<Ingredient> toppings;
	private JLabel timing;
	private GameManager manager;
	private int timeToOrder;
	
	public CronController(Queue<Order> pQueue, JLabel pTiming, Settings pSettings, List<Ingredient> pIngredients, GameManager pManager){
		balls = new List<Ingredient>();
		toppings = new List<Ingredient>();
		orders = pQueue;
		settings = pSettings;
		setBallsAndToppings(pIngredients);
		timeToOrder = 120;
		timing = pTiming;
		manager = pManager;
	}
	
	public void setBallsAndToppings(List<Ingredient> ingredients) {
		for(int i = 0; i<ingredients.getLength(); i++) {
			Ingredient actual = ingredients.iterValues(i);
			if(actual.getType().equals("bola")) {
				balls.add(actual);
			}else {
				toppings.add(actual);
			}
		}
	}
	
	public void addRandomOrder(boolean priority) {
		Order order = new Order();
		int nBalls = randInt(1,settings.getMaxBalls());
		int nToppings;
		for(int i = 0; i<nBalls; i++) {
			IceCreamBall ball = new IceCreamBall(balls.getValue(randInt(0, balls.getLength()-1)));
			nToppings = randInt(0,settings.getMaxToppings());
			for(int j = 0; j<nToppings; j++) {
				ball.addTopping(toppings.getValue(randInt(0, toppings.getLength()-1)));
			}
			order.addIceCreamBall(ball);
		}
		if(!priority) {
			orders.enqueue(order);
		}else {
			orders.enqueue(order, 1);
		}
		manager.updateNumberOrders(true);
	}
	
	public void run() {
		for(int i = 120; i>=0; i--) {
			timing.setText(Integer.toString(i/60)+":"+(i%60<10?"0":"")+Integer.toString(i%60));
			manager.setactualTime(i);
			if(i == timeToOrder) {
				timeToOrder -= settings.getTimeToOrder();
				int nOrders = randInt(settings.getMinOrder(),settings.getMaxOrder());
				for(int j = 0; j<nOrders; j++) {
					if(randTrueInProbability(settings.getPriorityOrderProbability())) {
						addRandomOrder(true);
					}else {
						addRandomOrder(false);
					}
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		manager.endGame();
	}
}