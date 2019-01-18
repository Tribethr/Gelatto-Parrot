package ui;

import library.IConstants;
import library.IceCreamBall;
import library.Ingredient;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import lists.List;
import main.GameManager;

@SuppressWarnings("serial")
public class MainUI extends JFrame implements IConstants{
	
	private JLabel timeLabel;
	private JLabel[] actualOrderLabels;
	private JLabel numberOrdersLabel;
	private JLabel verification;
	private JLabel totalOrdersLabel;
	private JLabel scoreLabel;
	private BufferedImage coneImage;
	private List<IngredientButton> buttons;
	private JPanel Buttonspanel;
	private JScrollPane scrollPane;	
	private GameManager manager;
	
	public MainUI(GameManager pManager, BufferedImage pConeImage) {
		buttons = new List<IngredientButton>();
		manager = pManager;
		manager.setUI(this);
		actualOrderLabels = new JLabel[manager.getMaxballs()];
		coneImage = pConeImage;
		setLayout(null);
		setBounds(200,200,WIND_WIDTH,WIND_HEIGHT);
		setTitle(WIND_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		Buttonspanel = new JPanel();
		initComponents(manager.getIngredients());
		setVisible(true);
		repaint();
	}
	
	public JLabel getTimeLabel() {
		return timeLabel;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		super.paintComponents(g);
		g.drawImage(coneImage, 65, 725, this);
		manager.getActualCone().paint(g, ICECREAM_XPOSITION, ICECREAM_YPOSITION, ICECREAM_MOVING_FACTOR, this);
	}
	
	public void initComponents(List<Ingredient> pIngredients) {
									// Label creation
		//Info label creation
		timeLabel = createLabel("",10,0,100,50,35);
		numberOrdersLabel = createLabel("Ordenes: 0", 1000, 0, 200, 50, 25);
		totalOrdersLabel = createLabel("Total: 0", 1200, 0, 200, 50, 25);
		verification = createLabel("", 300, 300, 300, 50, 30);
		scoreLabel = createLabel("Puntuacion: 0", 150, 0, 400, 50, 25);
		createLabel("Orden Actual: ",600,75,700,50,30);
		createLabel(WIND_TITLE, WIND_WIDTH/2-150, 0, 300, 50, 50);
		// Order label creation
		for(int i = 0; i< actualOrderLabels.length; i++) {
			actualOrderLabels[i] = createLabel("", ORDER_LABEL_XPOSITION, ORDER_LABEL_YPOSITION+i*ORDER_LABEL_MOVING_FACTOR, ORDER_LABEL_WIDTH,
					ORDER_LABEL_HEIGHT, ORDER_LABEL_SIZE);
		}
										//Button creation
		JButton entregarButton = createButton("Entregar", WIND_WIDTH-BUTTON_WIDTH-20, 750, BUTTON_WIDTH,
				BUTTON_HEIGHT, BUTTON_FONT_SIZE);
		entregarButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				entregar();
			} 
		});
		
		JButton removeBallButton = createButton("Remover",WIND_WIDTH-BUTTON_WIDTH*2-30, 750, BUTTON_WIDTH,
				BUTTON_HEIGHT, BUTTON_FONT_SIZE);
		removeBallButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				manager.getActualCone().removeBall();
				repaint();
			} 
		});
		int rows = 0;
		for(int i = 0, columns = 0; i< pIngredients.getLength(); i++, columns++) {
			if(columns == BUTTON_COLUMNS) {
				columns = 0;
				rows++;
			}
			Ingredient actual = pIngredients.iterValues(i);
			buttons.add(new IngredientButton(actual.getFlavour(),BUTTON_FIRST_COLUMN+columns*BUTTON_XMOVING_FACTOR,BUTTON_FIRST_ROW+rows*BUTTON_YMOVING_FACTOR
					,BUTTON_WIDTH,BUTTON_HEIGHT,BUTTON_FONT_SIZE,actual,manager));
		}
		Buttonspanel.setLayout(null);
		add(Buttonspanel);
		JScrollPane scroll = new JScrollPane(Buttonspanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(500,300,870,400);
		add(scroll);
		rows += 1;
		Buttonspanel.setPreferredSize(new Dimension(0, rows*BUTTON_HEIGHT+rows*(BUTTON_YMOVING_FACTOR-BUTTON_HEIGHT)));

	}
	
	
	
	public JPanel getButtonsPanel() {
		return Buttonspanel;
	}
	
	public void entregar() {
		buttons.scramble();
		sortButtons();
		if(manager.entregar()){
			verification.setText("Bien hecho :D");
			scoreLabel.setText("Puntuacion: "+manager.calculateScore());
		}else {
			verification.setText("Fallaste :(");
		}
	}
	
	
	public void sortButtons() {
		for(int i = 0, columns = 0, rows = 0; i< buttons.getLength(); i++, columns++) {
			if(columns == BUTTON_COLUMNS) {
				columns = 0;
				rows++;
			}
			buttons.iterValues(i).setBounds(BUTTON_FIRST_COLUMN+columns*BUTTON_XMOVING_FACTOR, BUTTON_FIRST_ROW+rows*BUTTON_YMOVING_FACTOR, BUTTON_WIDTH, BUTTON_HEIGHT);
		}
		repaint();
	}
	
	public void changeActualOrder() {
		List<IceCreamBall> actual = manager.getActualOrder().getIceCreamBalls();
		for(int i = 0; i<actual.getLength(); i++) {
			actualOrderLabels[i].setText(actual.iterValues(i).toString());
		}
	}
	
	public void cleanActualOrder() {
		for(int i = 0; i<actualOrderLabels.length; i++) {
			actualOrderLabels[i].setText("");
		}
	}
	
	public void updateNumberOrders(boolean updateTotal, int pTotalOrders, int pNumberOrders ) {
		if(updateTotal) {
			totalOrdersLabel.setText("Total: "+Integer.toString(pTotalOrders));
		}
		numberOrdersLabel.setText("Pendientes: "+Integer.toString(pNumberOrders));
	}
	
	public void endGame(int score) {
		scoreLabel.setText("Puntuacion final: "+score);
	}
	
	public JLabel createLabel(String text,int x, int y, int width, int height,int fontSize) {
		JLabel label = new JLabel(text);
		label.setBounds(x,y,width,height);
		label.setFont(new Font(null,Font.PLAIN,fontSize));
		add(label);
		return label;
	}
	
	public JButton createButton(String text, int x, int y, int width, int height, int fontSize) {
		JButton button = new JButton(text);
		button.setBounds(x, y, width, height);
		button.setFont(new Font(null,Font.PLAIN,fontSize));
		add(button);
		return button;
	}
	
	public void removeVerificationLabel() {
		verification.setText("");
	}
}
