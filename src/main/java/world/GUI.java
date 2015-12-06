package world;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 * 
 * @author JKyte
 *
 *	The GUI of the World
 */
@SuppressWarnings("serial")
public class GUI extends JFrame{

	private JTextArea mapPane;

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
				createAndDisplayFixedGUI(600, 600);
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
		setSize(new Dimension(17*mapWidth, ((20*mapHeight)+120) ));

		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void createAndDisplayFixedGUI(int mapWidth, int mapHeight) {

		setTitle("ALife");
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