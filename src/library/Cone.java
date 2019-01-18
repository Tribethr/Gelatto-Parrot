package library;

import lists.Stack;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

import library.IceCreamBall;

import static util.HashCalculator.calulateHash;

public class Cone {
	private Stack<IceCreamBall> order = new Stack<IceCreamBall>();
	private Stack<IceCreamBall> orderAux = new Stack<IceCreamBall>();
	private IceCreamBall actual;
	private int maxBalls;
	
	public void addBall(Ingredient pBall) {
		if(order.getLength() != maxBalls) {
			actual = new IceCreamBall(pBall);
			order.push(actual);
		}
	}
	
	public void setMaxBalls(int pMaxBalls) {
		maxBalls = pMaxBalls;
	}
	
	public void removeBall() {
		order.pop();
		actual = null;
	}
	
	public void addTopping(Ingredient pTopping) {
		try {
			actual.addTopping(pTopping);
		}catch(Exception e) {}
	}
	
	public void removeTopping() {
		actual.removeTopping();
	}
	
	public void clear() {
		order.clear();
		actual = null;
	}

	public void paint(Graphics g, int x , int y,int movingFactor, ImageObserver observer) {
		int pops = order.getLength();
		fillAux(pops);
		IceCreamBall actualBallToPaint = null;
		for(int i = 0; i<pops; i++) {
			actualBallToPaint = orderAux.pop();
			actualBallToPaint.paint(g, x, y-i*movingFactor, observer);
			order.push(actualBallToPaint);
		}
	}
	
	private void fillAux(int pops) {
		for(int i = 0; i<pops; i++) {
			orderAux.push(order.pop());
		}
	}
	
	public boolean calculateMatch(Order toMatch) {
		String id ="";
		int pops = order.getLength();
		fillAux(pops);
		for(int i = 0; i<pops; i++) {
			id += orderAux.pop().getID();
		}
		System.out.println(id+"   " +toMatch.getId());
		StringBuffer comp1 = null;
		StringBuffer comp2 = null;
		try {
			comp1 = calulateHash(id);
			comp2 = calulateHash(toMatch.getId());
			return comp1.toString().equals(comp2.toString());
		} catch (Exception e) {
			return false;
		}
	}
}
