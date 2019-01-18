package library;

import lists.List;

public class Order {
	private List<IceCreamBall> balls = new List<IceCreamBall>();
	private String id = "";
	
	public void addIceCreamBall(IceCreamBall pBall) {
		balls.add(pBall);	
	}
	public List<IceCreamBall> getIceCreamBalls() {
		return balls;
	}
	
	public int Length() {
		return balls.getLength();
	}
	
	public String getId() {
		if(id.equals("")) {
			for (int i = 0; i< balls.getLength(); i++) {
				id += balls.iterValues(i).getID();
			}
		}	
		return id;
	}
}