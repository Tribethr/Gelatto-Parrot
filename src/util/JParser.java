package util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import library.Ingredient;
import library.Settings;
import lists.List;

import java.io.FileReader;
import java.io.IOException;

public class JParser {
	
	private JSONParser parser = new JSONParser();
	private JSONObject obj;
	
	public Settings parseSettings(String dir){
		Settings parsed = null;
		try {
			obj = (JSONObject) parser.parse(new FileReader(dir));
			parsed = new Settings(
					((Long)obj.get("maxBalls")).intValue(),
					((Long)obj.get("orderTime")).intValue(),
					((Long)obj.get("priorityOrderProbability")).intValue(),
					((Long)obj.get("maxTopping")).intValue(),
					((Long)obj.get("maxOrder")).intValue(),
					((Long)obj.get("minOrder")).intValue());
		} catch (IOException | ParseException e) {
			System.out.println("No pude leer el archivo");
		}
		return parsed;  
	}
	public List<Ingredient> parseIngredients(String dir){
		List<Ingredient> parsed = new List<Ingredient>();
		try {
			obj = (JSONObject) parser.parse(new FileReader(dir));
			JSONObject actual = new JSONObject();
			for(Object ob : obj.values()) {
				actual = (JSONObject)ob;
				parsed.add(new Ingredient(
						(String)actual.get("tipo"),
						(String)actual.get("sabor"),
						(String)actual.get("idImagen"),
						(String)actual.get("id")));
			}
			System.out.println(parsed);
		} catch (IOException | ParseException e) {
			System.out.println("No pude leer el archivo");
		}
		return parsed;
	}
	
}