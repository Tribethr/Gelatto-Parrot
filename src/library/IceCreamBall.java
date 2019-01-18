package library;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

import lists.List;

public class IceCreamBall {
	
	private Ingredient ball;
	private List<Ingredient> toppings = new List<Ingredient>();
	private String id = "";
	
	public IceCreamBall(Ingredient pBall) {
		ball = pBall;
		id+=pBall.getId();
	}
	
	public String getID() {
		return id;
	}
	
	public Ingredient getBall() {
		return ball;
	}

	public void setBall(Ingredient pBall) {
		ball = pBall;
	}

	public void addTopping(Ingredient pTopping) {
		if(!toppings.removeByValue(pTopping)) {
			id+=pTopping.getId();
		}
		toppings.add(pTopping);
	}
	
	public List<Ingredient> getToppings(){
		return toppings;
	}
	
	public void removeTopping() {
		toppings.removeByIndex(toppings.getLength()-1);
	}
	
	public void paint(Graphics g, int x, int y, ImageObserver observer ) {
		g.drawImage(ball.getImage(),x,y,observer);
		for(int i = 0; i< toppings.getLength(); i++) {
			g.drawImage(toppings.iterValues(i).getImage(), x+50, y+25, observer);
		}
	}
	
	@Override
	public String toString() {
		String ret = "bolita de " +ball.getFlavour();
		if(toppings.getLength() != 0) {
			ret += " con ";
			for(int i = 0; i<toppings.getLength(); i++) {
				if(i != toppings.getLength()-2) {
					ret += toppings.getValue(i).getFlavour()+", ";
				}else {
					ret += toppings.getValue(i).getFlavour()+" y ";
				}
			}
			return ret.substring(0, ret.length()-2);
		}
		return ret;
		
	}
}
