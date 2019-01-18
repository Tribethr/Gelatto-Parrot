package main;

import library.Ingredient;
import library.Order;
import library.Settings;
import lists.List;
import lists.Queue;
import ui.ImageManager;
import ui.MainUI;
import util.JParser;

public class Main {
	public static void main(String[] args) {
		Queue<Order> orders = new Queue<Order>();
		JParser parser = new JParser();
		Settings settings = parser.parseSettings("settings.txt");
		List<Ingredient> ingredients = parser.parseIngredients("ingredients.txt");
		ImageManager images = new ImageManager(ingredients);
		images.loadImages();
		Ingredient actual = null;
		for(int i = 0; i<ingredients.getLength(); i++) {
			actual = ingredients.iterValues(i);
			actual.setImage(images.getImage(actual.getIdImagen()));
		}
		GameManager manager = new GameManager(orders,settings.getMaxBalls(),ingredients);
		MainUI ui = new MainUI(manager, images.getCono());
		CronController cron = new CronController(orders,ui.getTimeLabel(),settings, ingredients, manager);
		cron.start();
	}
}