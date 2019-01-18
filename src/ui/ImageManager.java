package ui;

import javax.imageio.ImageIO;

import library.Ingredient;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import lists.List;

public class ImageManager {
	
	private BufferedImage cono;
	private List<BufferedImage> images;
	private List<Ingredient> ingredients;
	
	public ImageManager(List<Ingredient> pIngredients) {
		images = new List<BufferedImage>();
		ingredients = pIngredients;
	}
	public BufferedImage getCono() {
		return cono;
	}
	
	public BufferedImage getImage(String imageName) {
		Ingredient actual = null;
		for(int i = 0; i<ingredients.getLength(); i++) {
			actual = ingredients.iterValues(i);
			if(actual.getIdImagen().equals(imageName)) {
				return images.getValue(i);
			}
		}
		return null;
	}
	
	public void loadImages() {
		try {
			cono = ImageIO.read(new File("src/images/baseCono.PNG"));
			for(int i = 0; i< ingredients.getLength(); i++) {
				images.add(ImageIO.read(new File("src/images/"+ingredients.iterValues(i).getIdImagen()+".PNG")));
			}
		} catch (IOException e) {
			System.out.println(":(");
		}
	}
}