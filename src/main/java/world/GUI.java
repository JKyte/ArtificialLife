package world;

import javax.swing.*;
import java.awt.*;

/**
 * 
 * @author JKyte
 *
 *	The GUI of the World
 */
@SuppressWarnings("serial")
public class GUI extends JFrame{

	private JTextArea mapPane;
	
	private final int WIDTH = 600, HEIGHT = 600,
			DYNAMIC_WIDTH = 14, DYNAMIC_HEIGHT = 19;

	public GUI(final int mapWidth, final int mapHeight) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndDisplayDynamicGUI(mapWidth, mapHeight);
			}
		});
	}
	
	public GUI() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndDisplayFixedGUI(WIDTH, HEIGHT);
			}
		});
	}

	private void createAndDisplayDynamicGUI(int mapWidth, int mapHeight) {

		setTitle("ArtificialLife");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mapPane = new JTextArea();
		mapPane.setLineWrap(true);
		mapPane.setWrapStyleWord(true);
		mapPane.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		mapPane.setEditable(false);
		
		add(new JScrollPane(mapPane));
		setSize(new Dimension(DYNAMIC_WIDTH*mapWidth, ((DYNAMIC_HEIGHT*mapHeight)) ));

		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void createAndDisplayFixedGUI(int mapWidth, int mapHeight) {

		setTitle("ArtificialLife");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mapPane = new JTextArea();
		mapPane.setLineWrap(true);
		mapPane.setWrapStyleWord(true);
		mapPane.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		mapPane.setEditable(false);
		
		add(new JScrollPane(mapPane));
		setSize(new Dimension(mapWidth, mapHeight));

		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void clearAndSetText(String text){
		mapPane.setText(text);
	}
}