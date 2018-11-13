package screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import graphics.GraphBar;
import main.SortVisualizerGUI;
import sorting.Sorting;

public class VisualizationScreen extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	// set up needed objects
	private Timer timer = new Timer(1, this); // timer for GUI refresh
	int n = 128;							  // size of array(s)
	int[] nums = new int[n];				  // array to be sorted
	int h1;									  // index of red highlight
	int h2;									  // index of yellow highlight
	int max = -1;							  // var to keep track of max value for bar colors
	int sortSpeed;					  		  // var to keep track of the original sort speed
	long elapsedTime;						  // Keep track of actual sorting time
	Random r = new Random();				  // Random object to create the array
	JButton btnBack;						  // Button for going back to the menu 
											  // (needed outside constructor)
	JLabel lblActual;
	
	// BufferedWriter to write the array states into a file
	private BufferedWriter w;
	
	// Scanner to read array states in from a file
	private Scanner in;
	
	/**
	 * Returns a shade of blue which is (t*100)% between a and b
	 * using linear interpolation.
	 * @param a - lower bound (int)
	 * @param b - upper bound (int)
	 * @param t - double representing percentage between a and b
	 * @return Color
	 */
	private Color lerpColor(int a, int b, double t) {
		int x = (int) (a + (b - a) * t);
		return new Color(x/2, x/2, x);
	}
	
	/**
	 * Sets up the initial speed of the demo.
	 * @param speed - Speed to set objects to
	 * @param timer - Timer object to set delay of
	 * @param slider - Slider object to set value of
	 * @param label - Label to set text of
	 */
	private void setInitSpeed(int speed, Timer timer, JSlider slider, JLabel label) {
		timer.setDelay(speed);
		slider.setValue(speed);
		label.setText(speed + " ms/operation");
		sortSpeed = speed;
	}
	
	public VisualizationScreen(Sort sort, SortVisualizerGUI frame) {
		
		try {
			w = new BufferedWriter(new FileWriter("sorting"));
			in = new Scanner(new File("sorting"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// set up the basic window
		this.setPreferredSize(new Dimension(1280, 720));
		this.setBackground(new Color(230, 230, 230));
		this.setFocusable(true);
		setLayout(null);
		
		// label for algorithm's name
		JLabel lblSort = new JLabel("");
		lblSort.setFont(new Font("Roboto", Font.PLAIN, 36));
		lblSort.setForeground(new Color(20, 20, 20));
		lblSort.setBounds(25, 15, 401, 48);
		add(lblSort);
		
		// label to display "Simulation speed" text
		JLabel lblSimulationSpeed = new JLabel("Simulation speed");
		lblSimulationSpeed.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblSimulationSpeed.setForeground(new Color(20, 20, 20));
		lblSimulationSpeed.setBounds(795, 40, 200, 20);
		add(lblSimulationSpeed);
		
		JLabel lblFaster = new JLabel("Faster");
		lblFaster.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblFaster.setForeground(new Color(0, 200, 0));
		lblFaster.setBounds(928, 20, 200, 20);
		add(lblFaster);
		
		JLabel lblSlower = new JLabel("Slower");
		lblSlower.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblSlower.setForeground(new Color(220, 0, 0));
		lblSlower.setBounds(1090, 20, 200, 20);
		add(lblSlower);
		
		// label to display the actual speed
		JLabel lblSpeed = new JLabel("10 ms/operation");
		lblSpeed.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblSpeed.setForeground(new Color(20, 20, 20));
		lblSpeed.setBounds(1140, 40, 130, 20);
		add(lblSpeed);
		
		// slider for adjusting speed
		JSlider slider = new JSlider(1, 500);
		slider.setValue(10);
		slider.setBounds(928, 42, 200, 16);
		slider.addChangeListener(new ChangeListener() {

			// when the slider is changed, the timer and JLabel
			// will be updated to reflect the change
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				timer.setDelay(slider.getValue());
				lblSpeed.setText(slider.getValue() + " ms/operation");
				sortSpeed = slider.getValue();
			}
			
		});
		add(slider);
		
		btnBack = new JButton("Back to menu");
		btnBack.setFont(new Font("Roboto", Font.BOLD, 14));
		btnBack.setForeground(new Color(20, 20, 20));
		btnBack.setBounds(25, 100, 130, 48);
		btnBack.setBackground(new Color(210, 210, 210));
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.loadMenu();
			}
			
		});
		btnBack.setVisible(false);
		add(btnBack);
		
		lblActual = new JLabel("");
		lblActual.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblActual.setForeground(new Color(20, 20, 20));
		lblActual.setBounds(25, 70, 250, 20);
		lblActual.setVisible(false);
		add(lblActual);
		
		// start the GUI timer
		timer.start();
		
		// create the randomized array, keeping track of
		// the maximum value generated
		for (int i = 0; i < n; i++) {
			nums[i] = r.nextInt(254) + 1;
			try {
				w.write(Sorting.array2Str(-1, -1, nums));
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (nums[i] > max) {
				max = nums[i];
			}
		}
		
		// set up GUI based on sorting algorithm and run the sort
		switch(sort) {
		
			case BUBBLE:
				lblSort.setText("Bubble sort");
				setInitSpeed(20, timer, slider, lblSpeed);
				long start = System.currentTimeMillis();
				Sorting.bubbleSort(nums, w);
				elapsedTime = System.currentTimeMillis() - start;
				break;
				
			case INSERTION:
				lblSort.setText("Insertion sort");
				setInitSpeed(20, timer, slider, lblSpeed);
				long start2 = System.currentTimeMillis();
				Sorting.insertionSort(nums, w);
				elapsedTime = System.currentTimeMillis() - start2;
				break;
				
			case SELECTION:
				lblSort.setText("Selection sort");
				setInitSpeed(20, timer, slider, lblSpeed);
				long start3 = System.currentTimeMillis();
				Sorting.selectionSort(nums, w);
				elapsedTime = System.currentTimeMillis() - start3;
				break;
				
			case QUICK:
				lblSort.setText("Quicksort");
				setInitSpeed(100, timer, slider, lblSpeed);
				long start4 = System.currentTimeMillis();
				Sorting.quickSort(nums, w);
				elapsedTime = System.currentTimeMillis() - start4;
				break;
				
			case MERGE:
				lblSort.setText("Merge sort");
				setInitSpeed(100, timer, slider, lblSpeed);
				long start5 = System.currentTimeMillis();
				Sorting.mergesort(nums, w);
				elapsedTime = System.currentTimeMillis() - start5;
				break;
				
			case SHELL:
				lblSort.setText("Shell sort");
				setInitSpeed(100, timer, slider, lblSpeed);
				long start6 = System.currentTimeMillis();
				Sorting.shellSort(nums, nums.length, w);
				elapsedTime = System.currentTimeMillis() - start6;
				break;
				
			case SLOW:
				lblSort.setText("Slow sort");
				setInitSpeed(100, timer, slider, lblSpeed);
				long start7 = System.currentTimeMillis();
				Sorting.slowSort(nums, w);
				elapsedTime = System.currentTimeMillis() - start7;
				break;
				
			case COUNTING:
				lblSort.setText("Counting sort");
				setInitSpeed(100, timer, slider, lblSpeed);
				long start8 = System.currentTimeMillis();
				Sorting.countingSort(nums, w);
				elapsedTime = System.currentTimeMillis() - start8;
				break;
				
			case RADIX:
				lblSort.setText("Radix sort (LSD)");
				setInitSpeed(100, timer, slider, lblSpeed);
				long start9 = System.currentTimeMillis();
				Sorting.radixLSD(nums, w);
				elapsedTime = System.currentTimeMillis() - start9;
				break;
				
			default:
				break;
				
		}
		
		// delete temp array file and close writer
		new File("sorting").delete();
		try {
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		// draw each bar, making it red if the index = h1,
		//                       yellow if the index = h2,
		//                       or the respective shade of blue otherwise
		for (int i = 0; i < n; i++) {
			GraphBar gb = new GraphBar(i, (int) (nums[i]*2.4), (i == h1) ? new Color(255, 0, 0) : (i == h2) ? new Color(255, 180, 0) : lerpColor(40, max, (1. * nums[i])/max));
			gb.draw(g, 9);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		// read, parse, and store each state of the array
		// from the text file
		if (in.hasNextLine()) {
			String line = in.nextLine();
			h1 = Integer.parseInt(line.split(";")[0]);
			h2 = Integer.parseInt(line.split(";")[1]);
			if (h1 == -1) {
				timer.setDelay(8);
			} else {
				timer.setDelay(sortSpeed);
			}
			String list = line.split(";")[2];
			Scanner im = new Scanner(list);
			int[] ints = new int[n];
			for (int i = 0; i < n; i++) {
				ints[i] = im.nextInt();
			}
			nums = ints;
			im.close();
		} else {
			btnBack.setVisible(true);
			lblActual.setText("Actual sorting time: " + (elapsedTime/1000.) + " seconds");
			lblActual.setVisible(true);
			h1 = -1;
			h2 = -1;
		}
		
		// keep the GUI synced to prevent lag
		Toolkit.getDefaultToolkit().sync();
		this.repaint();
	}
}
