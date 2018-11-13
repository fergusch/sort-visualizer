package main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import screens.MenuScreen;

/**
 * @author @fergusch
 */
public class SortVisualizerGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	MenuScreen ms;

	public SortVisualizerGUI() throws IOException {
		
		// Create the MenuScreen object
		ms = new MenuScreen(this);
		
		// Set up GUI
		this.setContentPane(ms);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setTitle("Sort Visualizer");
		this.setFocusable(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
            	// delete the "sorting" file if closed
                new File("sorting").delete();
            }
        });


		
	}
	
	/**
	 * Sets the content pane back to the menu screen.
	 */
	public void loadMenu() {
		this.setContentPane(ms);
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		// Run the GUI
		SortVisualizerGUI svg = new SortVisualizerGUI();
	}
	
}
