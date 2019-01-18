package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import library.Ingredient;
import main.GameManager;

@SuppressWarnings("serial")
public class IngredientButton extends JButton{
	private Ingredient ingredient;
	private GameManager manager;
	public IngredientButton(String pText, int pX, int pY, int pWidth, int pHeight, int pFontSize, Ingredient pIngredient, GameManager pManager) {
		ingredient = pIngredient;
		manager = pManager;
		setText(pText);
		setBounds(pX, pY, pWidth, pHeight);
		setFont(new Font(null,Font.PLAIN,pFontSize));
		addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				manager.verifyVerificationLabel();
				if(ingredient.getType().equals("bola")) {
					manager.getActualCone().addBall(ingredient);
				}else {
					manager.getActualCone().addTopping(ingredient);
				}
				manager.getUI().repaint();
			} 
		});
		manager.getUI().getButtonsPanel().add(this);
	}
}
