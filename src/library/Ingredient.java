package library;

import java.awt.image.BufferedImage;

public class Ingredient {
	
	private String type;
	private String flavour;
	private String idImagen;
	private String id;
	private BufferedImage image;
	
	public Ingredient(String pType, String pFlavour, String pIdImagen, String pId) {
		type = pType;
		flavour = pFlavour;
		idImagen = pIdImagen;
		id = pId;
	}

	public String getType() {
		return type;
	}

	public String getFlavour() {
		return flavour;
	}

	public String getIdImagen() {
		return idImagen;
	}

	public String getId() {
		return id;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(BufferedImage pImage) {
		image = pImage;
	}
	
	@Override
	public String toString() {
		return "Ingredient [type=" + type + ", flavour=" + flavour + ", idImagen=" + idImagen + ", id=" + id + "]";
	}
	
}
